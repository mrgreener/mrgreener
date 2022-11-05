package tech.mrgreener.application

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.netty.Netty
import io.ktor.server.routing.*
import kotlinx.html.*
import org.apache.log4j.BasicConfigurator
import tech.mrgreener.application.controller.*
import tech.mrgreener.application.model.initDbManager
import java.util.UUID

fun HTML.index() {
    head {
        title("Hello from Ktor!")
    }
    body {
        div {
            +"Hello from Ktor"
        }
        div {
            id = "root"
        }
        script(src = "/static/mrgreener.js") {}
    }
}

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
}

fun initServer() {
    BasicConfigurator.configure()
    initDbManager()

    basicControllersTests()
}


fun main() {
    initServer()

    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        routing {
            get("/") {
                call.respondHtml(HttpStatusCode.OK, HTML::index)
            }
            static("/static") {
                resources()
            }
        }
    }.start(wait = true)
}