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

class NotEnoughMoneyForRewardVoucherException(voucherId: IdType) :
    BadRequestException("Not enough money to buy reward voucher $voucherId was already activated")

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
    // TODO check money

    val voucherId = issueRewardVoucher(reward);
    redeemRewardVoucher(
        user = user,
        voucher = getRewardVoucherById(voucherId)
    )
}