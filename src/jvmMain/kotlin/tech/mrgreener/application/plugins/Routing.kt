package tech.mrgreener.application.plugins

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.util.*
import tech.mrgreener.application.controller.*
import tech.mrgreener.application.serversidemodel.Profile
import tech.mrgreener.application.utils.uid

fun Application.configureRouting() {

    routing {
        get("/v1/api/health") {
            call.respondText("OK")
        }
        authenticate {
            get("/v1/api/profiles/{username?}") {
                val username = call.parameters["username"] ?: return@get call.respondText(
                    "Missing username",
                    status = HttpStatusCode.BadRequest
                )
                val user = getUserByUsername(username)
                val profile = Profile(user, getUserBalance(user.id!!))
                call.respond(profile)
            }
        }

        authenticate {
            get("/v1/api/getMe") {
                val authId = call.uid()
                val user = getOrCreateByAuthId(authId)
                val profile = Profile(user, getUserBalance(user.id!!))
                call.respond(profile)
            }
        }

        authenticate {
            get("/v1/api/promotions/all") {
                TODO("Get all promotions")
            }
        }

        authenticate {
            get("/v1/api/rewards/all") {
                TODO("Get all rewards")
            }
        }

        authenticate {
            post("/v1/api/buyReward/{reward_id}") {
                val rewardId = call.parameters["reward_id"] ?: return@post call.respondText(
                    "Missing reward_id",
                    status = HttpStatusCode.BadRequest
                )
                TODO("Try to buy")
            }
        }

        authenticate {
            get("/v1/api/redeemVoucher") {
                val code: String = call.attributes.getOrNull(AttributeKey("code")) ?: return@get call.respondText(
                    "Missing voucher code",
                    status = HttpStatusCode.BadRequest
                )
                val authId = call.uid()
                val client = getOrCreateByAuthId(authId)
                redeemPromotionVoucher(client, code)
                call.respondText("OK")
            }
        }
    }
}
