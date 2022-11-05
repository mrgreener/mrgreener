package tech.mrgreener.application.controller

import tech.mrgreener.application.NotFoundException
import tech.mrgreener.application.PermissionDeniedException
import tech.mrgreener.application.model.IdType
import tech.mrgreener.application.model.entities.Client
import tech.mrgreener.application.model.sessionFactory
import java.sql.Timestamp
import java.util.*

class UserNotFoundException(userId: IdType) : NotFoundException("User $userId not found")
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

    return client.id
}

fun getUserById(userId: IdType): Client {
    var result: Client? = null
    sessionFactory.inTransaction {
        result = it.get(Client::class.java, userId);
    }
    return result ?: throw UserNotFoundException(userId)
}

fun assertExistsAndAdmin(userId: Long) {
    sessionFactory.inTransaction {
        val user = it.get(Client::class.java, userId) ?: throw UserNotFoundException(userId)
        if (!user.isAdmin) {
            throw NotAnAdminException(userId)
        }
    }
}