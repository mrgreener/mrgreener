package tech.mrgreener.application.controller

import tech.mrgreener.application.NotFoundException
import tech.mrgreener.application.PermissionDeniedException
import tech.mrgreener.application.model.IdType
import tech.mrgreener.application.model.MoneyType
import tech.mrgreener.application.model.entities.Client
import tech.mrgreener.application.model.sessionFactory
import java.sql.Timestamp
import java.util.*

class UserNotFoundException(userId: IdType) : NotFoundException("User $userId not found")
class UsernameNotFoundException(username: String) : NotFoundException("Username $username not found")
class UserAuthIdNotFoundException(authId: String) : NotFoundException("User with authId $authId not found")
class NotAnAdminException(userId: IdType) : PermissionDeniedException("User $userId is not an admin")

fun addNewUser(
    username: String,
    authId: String,
    name: String,
): IdType {
    val client = Client(
        username = username,
        authId = authId,
        name = name,
        registeredOn = Timestamp(Date().time)
    )

    sessionFactory.inTransaction {
        it.persist(client)
    }

    return client.id!!
}

fun getUserById(userId: IdType): Client {
    var result: Client? = null
    sessionFactory.inTransaction {
        result = it.get(Client::class.java, userId);
    }
    return result ?: throw UserNotFoundException(userId)
}

fun getUserByUsername(username: String): Client {
    var result: Client? = null
    sessionFactory.inTransaction {
        result = it.createQuery("select u from Client u where u.username=:username", Client::class.java)
            .setParameter("username", username).singleResultOrNull
    }
    return result ?: throw UsernameNotFoundException(username)
}

fun getUserByAuthId(authId: String): Client {
    var result: Client? = null
    sessionFactory.inTransaction {
        result = it.createQuery("select u from Client u where u.authId=:authId", Client::class.java)
            .setParameter("authId", authId).singleResultOrNull
    }
    return result ?: throw UserAuthIdNotFoundException(authId)
}


fun getOrCreateByAuthId(authId: String): Client {
    return try {
        getUserByAuthId(authId)
    } catch (exception: UserAuthIdNotFoundException) {
        addNewUser(authId, authId, authId)  // FIXME("Stupid")
        getUserByAuthId(authId)
    }
}


fun assertExistsAndAdmin(userId: Long) {
    sessionFactory.inTransaction {
        val user = it.get(Client::class.java, userId) ?: throw UserNotFoundException(userId)
        if (!user.isAdmin) {
            throw NotAnAdminException(userId)
        }
    }
}

fun getUserTotalIncome(userId: IdType): MoneyType {
    val user = getUserById(userId)
    var result: MoneyType? = null

    sessionFactory.inTransaction {
        result = it.createQuery(
            "select sum(a.voucher.rewardPoints) " +
                    "from PromotionVoucherActivation a where a.client.id=:clientId",
            MoneyType::class.java
        ).setParameter("clientId", user.id!!).singleResult ?: 0
    }

    return result!!
}

fun getUserTotalSpending(userId: IdType): MoneyType {
    val user = getUserById(userId)
    var result: MoneyType? = null

    sessionFactory.inTransaction {
        result = it.createQuery(
            "select sum(a.voucher.reward.price) " +
                    "from RewardVoucherActivation a where a.client.id=:clientId",
            MoneyType::class.java
        ).setParameter("clientId", user.id!!).singleResult ?: 0
    }

    return result!!
}

fun getUserBalance(userId: IdType): MoneyType {
    return getUserTotalIncome(userId) - getUserTotalSpending(userId)
}

fun editUser(
    userId: IdType,
    name: String? = null,
    bio: String? = null,
    avatarUrl: String? = null
) {
    val user = getUserById(userId)
    name?.let { user.name = it }
    bio?.let { user.bio = it }
    avatarUrl?.let { user.avatarUrl = it }

    sessionFactory.inTransaction {
        it.merge(user)
    }
}