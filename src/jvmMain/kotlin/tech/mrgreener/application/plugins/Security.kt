package tech.mrgreener.application.plugins

import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.application.*
import tech.mrgreener.application.utils.MyRSAKeyProvider

fun Application.configureSecurity() {

    authentication {
        jwt {
            val jwtAudience = "waleko-personal"
            verifier(
                JWT
                    .require(Algorithm.RSA256(MyRSAKeyProvider()))
                    .withIssuer("https://securetoken.google.com/${"waleko-personal"}")
                    .withAudience("waleko-personal")
                    .build()
            )
            validate { credential ->
                if (credential.payload.audience.contains(jwtAudience)) JWTPrincipal(credential.payload) else null
            }
        }
    }

}
