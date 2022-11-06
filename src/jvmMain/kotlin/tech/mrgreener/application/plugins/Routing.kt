package tech.mrgreener.application.plugins

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import tech.mrgreener.application.MrGreenException
import tech.mrgreener.application.controller.getUserBalance
import tech.mrgreener.application.controller.getUserByUsername
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
                val user = TODO(authId)
                val profile = Profile(user, getUserBalance(user.id!!))
                call.respond(profile)
            }
        }

        //        authenticate {
//        get("/v1/api/getMe") {
//            TODO("GET FROM AUTH")
//            val profile = Profile(user, getUserBalance(user.id!!))
//            call.respond(profile)
//        }
//        }
    }
}
