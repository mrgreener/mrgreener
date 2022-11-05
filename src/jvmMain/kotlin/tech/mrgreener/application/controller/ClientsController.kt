package tech.mrgreener.application.controller

import tech.mrgreener.application.NotFoundException
import tech.mrgreener.application.model.entities.Client
import tech.mrgreener.application.model.sessionFactory
import java.sql.Timestamp
import java.util.*

class UserNotFoundException(userId: Long) : NotFoundException("User $userId not found")
class NotAnAdminException(userId: Long) : NotFoundException("User $userId is not an admin")

fun addNewUser(
    username: String,
    name: String,
) {
    sessionFactory.inTransaction {
        it.persist(Client(username = username, name = name, registeredOn = Timestamp(Date().time)))
    }
}

fun assertExistsAndAdmin(userId: Long) {
    sessionFactory.inTransaction {
        val user = it.get(Client::class.java, userId) ?: throw UserNotFoundException(userId)
        if (!user.isAdmin) {
            throw NotAnAdminException(userId)
        }
    }
}