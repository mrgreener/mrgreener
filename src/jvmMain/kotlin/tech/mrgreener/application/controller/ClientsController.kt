package tech.mrgreener.application.controller

import tech.mrgreener.application.model.entities.Client
import tech.mrgreener.application.model.sessionFactory
import java.sql.Timestamp
import java.util.*

fun addNewUser(username: String) {
    sessionFactory.inTransaction {
        it.persist(Client(username = username, registeredOn = Timestamp(Date().time)))
    }
}