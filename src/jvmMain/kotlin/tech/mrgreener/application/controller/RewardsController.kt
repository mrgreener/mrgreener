package tech.mrgreener.application.controller

import tech.mrgreener.application.BadRequestException
import tech.mrgreener.application.NotFoundException
import tech.mrgreener.application.model.IdType
import tech.mrgreener.application.model.MoneyType
import tech.mrgreener.application.model.entities.*
import tech.mrgreener.application.model.sessionFactory
import java.sql.Timestamp
import java.util.*


class RewardNotFoundException(rewardId: IdType) : NotFoundException("Reward $rewardId not found")
class RewardVoucherNotFoundException(voucherId: IdType) : NotFoundException("Reward voucher $voucherId not found")
class RewardUsedVoucherException(voucherId: IdType) :
    BadRequestException("Reward voucher $voucherId was already activated")

class NotEnoughMoneyForRewardException(rewardId: IdType) :
    BadRequestException("Not enough money to buy reward  $rewardId")

fun addReward(
    organization: Organization,
    name: String,
    description: String,
    price: MoneyType,
    content: String,
    shortDescription: String? = null,
    pictureUrl: String? = null
): IdType {
    val reward = Reward(
        name = name,
        description = description,
        organization = organization,
        shortDescription = shortDescription,
        price = price,
        createdOn = Timestamp(Date().time),
        pictureUrl = pictureUrl,
        content = content
    )

    sessionFactory.inTransaction {
        it.persist(reward)
    }

    return reward.id!!
}

fun getRewardById(rewardId: IdType): Reward {
    var result: Reward? = null
    sessionFactory.inTransaction {
        result = it.get(Reward::class.java, rewardId);
    }
    return result ?: throw RewardNotFoundException(rewardId)
}

fun issueRewardVoucher(
    reward: Reward
): IdType {
    val voucher = RewardVoucher(
        reward = reward,
        content = reward.content,
        issuedOn = Timestamp(Date().time)
    )

    sessionFactory.inTransaction {
        it.persist(voucher)
    }

    return voucher.id!!
}

fun getRewardVoucherById(voucherId: IdType): RewardVoucher {
    var result: RewardVoucher? = null
    sessionFactory.inTransaction {
        result = it.get(RewardVoucher::class.java, voucherId);
    }
    return result ?: throw RewardVoucherNotFoundException(voucherId)
}

fun redeemRewardVoucher(
    user: Client,
    voucher: RewardVoucher
) {
    sessionFactory.inTransaction {
        if (voucher.activation != null) {
            throw RewardUsedVoucherException(voucher.id!!)
        }

        val activation = RewardVoucherActivation(
            voucher = voucher,
            client = user,
            activatedOn = Timestamp(Date().time)
        )

        it.persist(activation)
    }
}

fun buyReward(
    user: Client,
    reward: Reward
) {
    val balance = getUserBalance(userId = user.id!!)

    if (balance < reward.price) {
        throw NotEnoughMoneyForRewardException(reward.id!!)
    }

    val voucherId = issueRewardVoucher(reward);
    redeemRewardVoucher(
        user = user,
        voucher = getRewardVoucherById(voucherId)
    )
}


fun getRewardsForOrganization(
    organization: Organization,
    pageSize: Int? = null,
    pageId: Int = 0,
): List<Reward> {
    var result = emptyList<Reward>()
    sessionFactory.inTransaction {
        val query = it.createQuery(
            "select r from Reward r where r.organization.id=:organizationId",
            Reward::class.java
        ).setParameter("organizationId", organization.id!!)

        if (pageSize != null) {
            val firstResult = pageId * pageSize;
            query.firstResult = firstResult
            query.maxResults = pageSize
        }
        result = query.resultList
    }

    return result
}

fun getRedeemedRewardVouchersForUser(
    user: Client,
    pageSize: Int? = null,
    pageId: Int = 0,
): List<RewardVoucher> {
    var result = emptyList<RewardVoucher>()
    sessionFactory.inTransaction {
        val query = it.createQuery(
            "select pva.voucher from PromotionVoucherActivation pva where pva.client.id=:userId",
            RewardVoucher::class.java
        ).setParameter("userId", user.id!!)

        if (pageSize != null) {
            val firstResult = pageId * pageSize;
            query.firstResult = firstResult
            query.maxResults = pageSize
        }
        result = query.resultList
    }

    return result
}