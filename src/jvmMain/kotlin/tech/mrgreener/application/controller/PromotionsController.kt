package tech.mrgreener.application.controller

import tech.mrgreener.application.BadRequestException
import tech.mrgreener.application.NotFoundException
import tech.mrgreener.application.PROMOTION_VOUCHER_CODE_LENGTH
import tech.mrgreener.application.PROMOTION_VOUCHER_CODE_SYMBOLS
import tech.mrgreener.application.model.IdType
import tech.mrgreener.application.model.MoneyType
import tech.mrgreener.application.model.entities.*
import tech.mrgreener.application.model.sessionFactory
import java.sql.Timestamp
import java.util.*
import kotlin.random.Random


class PromotionNotFoundException(promotionId: IdType) : NotFoundException("Promotion $promotionId not found")
class PromotionVoucherCodeNotFoundException(code: String) :
    NotFoundException("Promotion voucher with code $code not found")

class PromotionVoucherNotFoundException(voucherId: IdType) : NotFoundException("Promotion voucher $voucherId not found")
class PromotionUsedVoucherException(voucherId: IdType) :
    BadRequestException("Promotion voucher $voucherId was already activated")

fun addPromotion(
    organization: Organization,
    name: String,
    description: String,
    rewardPoints: MoneyType,
    shortDescription: String? = null,
    pictureUrl: String? = null
): IdType {
    val promotion = Promotion(
        name = name,
        description = description,
        organization = organization,
        shortDescription = shortDescription,
        createdOn = Timestamp(Date().time),
        rewardPoints = rewardPoints,
        pictureUrl = pictureUrl,
    )

    sessionFactory.inTransaction {
        it.persist(promotion)
    }

    return promotion.id!!
}

fun getPromotionById(promotionId: IdType): Promotion {
    var result: Promotion? = null
    sessionFactory.inTransaction {
        result = it.get(Promotion::class.java, promotionId);
    }
    return result ?: throw PromotionNotFoundException(promotionId)
}

fun generatePromotionVoucherCode() = (1..PROMOTION_VOUCHER_CODE_LENGTH).map {
    Random.nextInt(0, PROMOTION_VOUCHER_CODE_SYMBOLS.length).let { PROMOTION_VOUCHER_CODE_SYMBOLS[it] }
}.joinToString("")

fun issuePromotionVoucher(
    promotion: Promotion,
    rewardPoints: MoneyType
): IdType {
    val voucher = PromotionVoucher(
        promotion = promotion,
        rewardPoints = rewardPoints,
        issuedOn = Timestamp(Date().time),
        code = generatePromotionVoucherCode()
    )

    sessionFactory.inTransaction {
        it.persist(voucher)
    }

    return voucher.id!!
}

fun getPromotionVoucherById(voucherId: IdType): PromotionVoucher {
    var result: PromotionVoucher? = null
    sessionFactory.inTransaction {
        result = it.get(PromotionVoucher::class.java, voucherId);
    }
    return result ?: throw PromotionVoucherNotFoundException(voucherId)
}

fun redeemPromotionVoucher(
    user: Client,
    code: String
) {
    sessionFactory.inTransaction {
        val voucher =
            it.createQuery("select v from PromotionVoucher v where v.code=:code", PromotionVoucher::class.java)
                .setParameter("code", code)
                .uniqueResult() ?: throw PromotionVoucherCodeNotFoundException(code)

        if (voucher.activation != null) {
            throw PromotionUsedVoucherException(voucher.id!!)
        }

        val activation = PromotionVoucherActivation(
            voucher = voucher,
            client = user,
            activatedOn = Timestamp(Date().time)
        )

        it.persist(activation)
    }
}