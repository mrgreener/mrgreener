package tech.mrgreener.application.utils

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

fun ApplicationCall.uid(): String {
    return (authentication.principal as JWTPrincipal).payload.subject
}