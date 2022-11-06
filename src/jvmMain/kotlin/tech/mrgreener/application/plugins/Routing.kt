package tech.mrgreener.application.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.long
import tech.mrgreener.application.MrGreenerException
import tech.mrgreener.application.controller.*
import tech.mrgreener.application.serversidemodel.*
import tech.mrgreener.application.utils.uid

fun Application.configureRouting() {

    routing {
        get("/v1/api/health") {
            call.respondText("OK")
        }

        // CUSTOMER API
        get("/v1/api/profiles/{username}/") {
            val username = call.parameters["username"] ?: return@get call.respondText(
                "Missing username",
                status = HttpStatusCode.BadRequest
            )

            try {
                val user = getUserByUsername(username)
                val profile = Profile(user, getUserBalance(user.id!!))
                call.respond(profile)
            } catch (e: MrGreenerException) {
                call.respondText(
                    e.error_message,
                    status = HttpStatusCode.fromValue(e.error_code)
                )
            }
        }


        authenticate {
            get("/v1/api/getMe") {
                try {
                    val authId = call.uid()
                    val user = getOrCreateByAuthId(authId)
                    val profile = Profile(user, getUserBalance(user.id!!))
                    call.respond(profile)
                } catch (e: MrGreenerException) {
                    call.respondText(
                        e.error_message,
                        status = HttpStatusCode.fromValue(e.error_code)
                    )
                }
            }
        }

        get("/v1/api/promotions/all") {
            val organizationId = call.request.queryParameters["org_id"]?.toLong() ?: return@get call.respondText(
                "Missing username",
                status = HttpStatusCode.BadRequest
            )

            try {
                val organization = getOrganizationById(organizationId)
                call.respond(
                    getPromotionsForOrganization(organization).map {
                        Promotion(it, organization)
                    }
                )
            } catch (e: MrGreenerException) {
                call.respondText(
                    e.error_message,
                    status = HttpStatusCode.fromValue(e.error_code)
                )
            }
        }


        get("/v1/api/rewards/all") {
            val organizationId = call.request.queryParameters["org_id"]?.toLong() ?: return@get call.respondText(
                "Missing username",
                status = HttpStatusCode.BadRequest
            )


            try {
                call.respond(
                    getRewardsForOrganization(getOrganizationById(organizationId)).map {
                        Reward(it)
                    }
                )
            } catch (e: MrGreenerException) {
                call.respondText(
                    e.error_message,
                    status = HttpStatusCode.fromValue(e.error_code)
                )
            }
        }


        authenticate {
            post("/v1/api/buyReward/{reward_id}") {
                val rewardId = call.parameters["reward_id"]?.toLong() ?: return@post call.respondText(
                    "Missing reward_id",
                    status = HttpStatusCode.BadRequest
                )

                try {
                    val authId = call.uid()
                    val user = getUserByAuthId(authId)
                    buyReward(user, getRewardById(rewardId))
                    call.respondText("OK")
                } catch (e: MrGreenerException) {
                    call.respondText(
                        e.error_message,
                        status = HttpStatusCode.fromValue(e.error_code)
                    )
                }
            }
        }

        authenticate {
            get("/v1/api/redeemVoucher") {
                val code: String = call.request.queryParameters["code"] ?: return@get call.respondText(
                    "Missing voucher code",
                    status = HttpStatusCode.BadRequest
                )

                try {
                    val authId = call.uid()
                    val client = getOrCreateByAuthId(authId)
                    redeemPromotionVoucher(client, code)
                    call.respondText("OK")
                } catch (e: MrGreenerException) {
                    call.respondText(
                        e.error_message,
                        status = HttpStatusCode.fromValue(e.error_code)
                    )
                }
            }
        }

        get("/v1/api/organizations/all") {
            try {
                call.respond(getOrganizations().map {
                    Organization(it)
                })
            } catch (e: MrGreenerException) {
                call.respondText(
                    e.error_message,
                    status = HttpStatusCode.fromValue(e.error_code)
                )
            }
        }


        get("/v1/api/rewards/get") {
            val rewardId = call.parameters["reward_id"]?.toLong() ?: return@get call.respondText(
                "Missing reward_id",
                status = HttpStatusCode.BadRequest
            )

            try {
                val reward = getRewardById(rewardId)
                call.respond(Reward(reward))
            } catch (e: MrGreenerException) {
                call.respondText(
                    e.error_message,
                    status = HttpStatusCode.fromValue(e.error_code)
                )
            }
        }


        get("/v1/api/promotions/get") {
            val promotionId = call.parameters["promotion_id"]?.toLong() ?: return@get call.respondText(
                "Missing promotion_id",
                status = HttpStatusCode.BadRequest
            )

            try {
                val promotion = getPromotionById(promotionId)
                val organisation = getOrganizationById(promotion.organization.id!!)
                call.respond(Promotion(promotion, organisation))
            } catch (e: MrGreenerException) {
                call.respondText(
                    e.error_message,
                    status = HttpStatusCode.fromValue(e.error_code)
                )
            }
        }

        authenticate {
            get("/v1/api/rewardVouchers/my") {
                try {
                    val authId = call.uid()
                    val user = getUserByAuthId(authId)

                    call.respond(
                        getRedeemedRewardVouchersForUser(user).map {
                            RewardVoucher(it)
                        }
                    )
                } catch (e: MrGreenerException) {
                    call.respondText(
                        e.error_message,
                        status = HttpStatusCode.fromValue(e.error_code)
                    )
                }
            }
        }


        // ORGANIZATION API

        authenticate {
            get("/v1/api/rewards/my") {
                try {
                    val authId = call.uid()
                    val organization = getOrganizationByAuthId(authId)
                    call.respond(
                        getRewardsForOrganization(organization).map {
                            Reward(it)
                        }
                    )
                } catch (e: MrGreenerException) {
                    call.respondText(
                        e.error_message,
                        status = HttpStatusCode.fromValue(e.error_code)
                    )
                }
            }
        }

        authenticate {
            get("/v1/api/promotions/my") {
                try {
                    val authId = call.uid()
                    val organization = getOrganizationByAuthId(authId)
                    call.respond(
                        getPromotionsForOrganization(organization).map {
                            Promotion(it, organization)
                        }
                    )
                } catch (e: MrGreenerException) {
                    call.respondText(
                        e.error_message,
                        status = HttpStatusCode.fromValue(e.error_code)
                    )
                }
            }
        }

        authenticate {
            post("/v1/api/rewards/add") {
                try {
                    val authId = call.uid()
                    val organization = getOrganizationByAuthId(authId)
                    val jsonData = call.receive<JsonObject>()

                    addReward(
                        organization = organization,
                        name = jsonData["name"]!!.jsonPrimitive.toString(),
                        pictureUrl = jsonData["picture_url"]?.jsonPrimitive?.toString(),
                        shortDescription = jsonData["short_description"]?.jsonPrimitive?.toString(),
                        description = jsonData["description_long"]!!.jsonPrimitive.toString(),
                        price = jsonData["price_points"]!!.jsonPrimitive.long,
                        content = jsonData["content"]!!.jsonPrimitive.toString()
                    )

                    call.respondText("OK")
                } catch (e: MrGreenerException) {
                    call.respondText(
                        e.error_message,
                        status = HttpStatusCode.fromValue(e.error_code)
                    )
                }
            }
        }

        authenticate {
            post("/v1/api/promotions/add") {
                try {
                    val authId = call.uid()
                    val organization = getOrganizationByAuthId(authId)
                    val jsonData = call.receive<JsonObject>()

                    addPromotion(
                        organization = organization,
                        name = jsonData["name"]!!.jsonPrimitive.toString(),
                        pictureUrl = jsonData["picture_url"]?.jsonPrimitive?.toString(),
                        shortDescription = jsonData["short_description"]?.jsonPrimitive?.toString(),
                        description = jsonData["description_long"]!!.jsonPrimitive.toString(),
                        rewardPoints = jsonData["reward_points"]!!.jsonPrimitive.long
                    )

                    call.respondText("OK")
                } catch (e: MrGreenerException) {
                    call.respondText(
                        e.error_message,
                        status = HttpStatusCode.fromValue(e.error_code)
                    )
                }
            }
        }

        // PURCHASE API

        get("/v1/api/buyVoucher") {
            try {
                val organizationKey = call.request.queryParameters["api_key"] ?: return@get call.respondText(
                    "Missing api_key",
                    status = HttpStatusCode.Unauthorized
                )
                val promotionId =
                    call.request.queryParameters["promotion_id"]?.toLong() ?: return@get call.respondText(
                        "promotion_id is missing",
                        status = HttpStatusCode.BadRequest
                    )

                val rewardsPoints = call.request.queryParameters["reward_points"]?.toLong()

                val organization = getOrganizationByApiKey(organizationKey)
                val promotion = getPromotionById(promotionId)

                if (promotion.organization.id != organization.id) {
                    return@get call.respondText(
                        "Access denied",
                        status = HttpStatusCode.Forbidden
                    )
                }

                val voucherId = issuePromotionVoucher(
                    promotion = promotion,
                    rewardPoints = rewardsPoints ?: promotion.rewardPoints
                )

                call.respond(PromotionVoucher(getPromotionVoucherById(voucherId)))
            } catch (e: MrGreenerException) {
                call.respondText(
                    e.error_message,
                    status = HttpStatusCode.fromValue(e.error_code)
                )
            }

        }
    }
}
