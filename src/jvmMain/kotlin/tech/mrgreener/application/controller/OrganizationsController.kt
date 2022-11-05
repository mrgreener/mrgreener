package tech.mrgreener.application.controller

import tech.mrgreener.application.model.entities.Organization
import tech.mrgreener.application.model.sessionFactory
import java.sql.Timestamp
import java.util.*

fun addOrganization(
    name: String,
    description: String,
    contactEmail: String,
    avatarUrl: String? = null,
    location: String? = null,
    siteUrl: String? = null,
) {
    sessionFactory.inTransaction {
        val organization = Organization(
            name = name,
            description = description,
            contactEmail = contactEmail,
            avatarUrl = avatarUrl,
            location = location,
            siteUrl = siteUrl,
            registeredOn = Timestamp(Date().time)
        )

        it.persist(organization)
    }
}