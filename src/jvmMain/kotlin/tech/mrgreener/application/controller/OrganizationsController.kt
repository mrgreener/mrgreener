package tech.mrgreener.application.controller

import tech.mrgreener.application.NotFoundException
import tech.mrgreener.application.model.IdType
import tech.mrgreener.application.model.entities.Client
import tech.mrgreener.application.model.entities.Organization
import tech.mrgreener.application.model.sessionFactory
import java.sql.Timestamp
import java.util.*

class OrganizationNotFoundException(organizationId: IdType) :
    NotFoundException("Organization $organizationId not found")

fun addOrganization(
    name: String,
    username: String,
    authId: String,
    description: String,
    contactEmail: String,
    avatarUrl: String? = null,
    location: String? = null,
    siteUrl: String? = null,
) {
    sessionFactory.inTransaction {
        val organization = Organization(
            username = username,
            authId = authId,
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

fun getOrganizationById(organizationId: IdType): Organization {
    var result: Organization? = null
    sessionFactory.inTransaction {
        result = it.get(Organization::class.java, organizationId);
    }

    return result ?: throw OrganizationNotFoundException(organizationId)
}