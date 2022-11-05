package tech.mrgreener.application.plugins

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun Application.configureRouting() {

    routing {
        get("/v1/api/health") {
            call.respondText("OK")
        }
        authenticate {
            get("/v1/api/profiles/{username?}") {
                val id = call.parameters["username"] ?: return@get call.respondText(
                    "Missing username",
                    status = HttpStatusCode.BadRequest
                )
                TODO("get user by username")
            }
        }
    }
}
