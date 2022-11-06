package tech.mrgreener.application.tests

import tech.mrgreener.application.controller.*
import java.util.*

fun rndStr(): String {
    return UUID.randomUUID().toString()
}

fun basicControllersTests() {
    val userId = addNewUser(
        username = rndStr(),
        authId = rndStr(),
        name = rndStr()
    )

    val orgId = addOrganization(
        name = rndStr(),
        username = rndStr(),
        authId = rndStr(),
        description = rndStr(),
        contactEmail = rndStr()
    )

    val promId = addPromotion(
        organization = getOrganizationById(orgId),
        name = rndStr(),
        description = rndStr(),
        rewardPoints = 666
    )

    val vouPromId = issuePromotionVoucher(
        promotion = getPromotionById(promId),
        rewardPoints = getPromotionById(promId).rewardPoints
    )

    redeemPromotionVoucher(
        user = getUserById(userId),
        code = getPromotionVoucherById(vouPromId).code
    )

    val rewOrgId = addOrganization(
        name = rndStr(),
        username = rndStr(),
        authId = rndStr(),
        description = rndStr(),
        contactEmail = rndStr()
    )

    val rewId = addReward(
        organization = getOrganizationById(rewOrgId),
        name = rndStr(),
        description = rndStr(),
        content = rndStr(),
        price = 566
    )

    buyReward(
        user = getUserById(userId),
        reward = getRewardById(rewId)
    )

    assert(getUserBalance(userId) == 100L)

    editUser(
        userId = userId,
        bio = "abobus BIO"
    )

    editUser(
        userId = userId,
        avatarUrl = "abobus_avatar.jpg"
    )

    assert(getUserById(userId).avatarUrl == "abobus_avatar.jpg")
    assert(getUserById(userId).bio == "abobus BIO")
}