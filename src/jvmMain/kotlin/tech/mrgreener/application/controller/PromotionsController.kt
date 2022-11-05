package tech.mrgreener.application.controller

import kotlinx.coroutines.selects.select
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


class VoucherCodeNotFoundException(code: String) : NotFoundException("Voucher with code $code not found")
class UsedVoucherException(voucherId: IdType) : BadRequestException("Voucher $voucherId was already activated")

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

    return promotion.id
}

fun generateCode() = (1..PROMOTION_VOUCHER_CODE_LENGTH).map {
    Random.nextInt(0, PROMOTION_VOUCHER_CODE_SYMBOLS.length).let { PROMOTION_VOUCHER_CODE_SYMBOLS[it] }
}.joinToString("")

fun issueVoucher(
    promotion: Promotion,
    rewardPoints: MoneyType
): IdType {
    val voucher = PromotionVoucher(
        promotion = promotion,
        rewardPoints = rewardPoints,
        issuedOn = Timestamp(Date().time),
        code = generateCode()
    )

    sessionFactory.inTransaction {
        it.persist(voucher)
    }

    return voucher.id
}

fun redeemVoucher(
    user: Client,
    code: String
) {
    sessionFactory.inTransaction {
        val voucher = it.createQuery("select v from PromotionVoucher where v.code=:code", PromotionVoucher::class.java)
            .setParameter("code", code)
            .uniqueResult() ?: throw VoucherCodeNotFoundException(code)

        if (voucher.activation != null) {
            throw UsedVoucherException(voucher.id)
        }

        val activation = PromotionVoucherActivation(
            voucher = voucher,
            client = user,
            activatedOn = Timestamp(Date().time)
        )

        it.persist(activation)
    }
}