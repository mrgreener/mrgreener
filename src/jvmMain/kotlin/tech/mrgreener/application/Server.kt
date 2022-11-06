package tech.mrgreener.application

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.apache.log4j.BasicConfigurator
import tech.mrgreener.application.model.initDbManager
import tech.mrgreener.application.plugins.configureHTTP
import tech.mrgreener.application.plugins.configureRouting
import tech.mrgreener.application.plugins.configureSecurity
import tech.mrgreener.application.plugins.configureSerialization
import tech.mrgreener.application.tests.basicControllersTests

fun initServer() {
    BasicConfigurator.configure()
    initDbManager()

    //basicControllersTests()
}

fun Application.module() {
    configureSecurity()
    configureHTTP()
    configureSerialization()
    configureRouting()
}

fun main() {
    initServer()

    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module).start(wait = true)
}