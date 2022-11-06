package tech.mrgreener.application.controller

import tech.mrgreener.application.NotFoundException
import tech.mrgreener.application.model.IdType
import tech.mrgreener.application.model.entities.Organization
import tech.mrgreener.application.model.sessionFactory
import java.sql.Timestamp
import java.util.*

class OrganizationNotFoundException(organizationId: IdType) :
    NotFoundException("Organization $organizationId not found")

class OrganizationAuthIdNotFoundException(authId: String) :
    NotFoundException("Organization with authId $authId not found")

fun addOrganization(
    name: String,
    username: String,
    authId: String,
    description: String,
    contactEmail: String,
    avatarUrl: String? = null,
    location: String? = null,
    siteUrl: String? = null,
): IdType {
    val organization = Organization(
        username = username,
        authId = authId,
        name = name,
        description = description,
        contactEmail = contactEmail,
        avatarUrl = avatarUrl,
        location = location,
        siteUrl = siteUrl,
        registeredOn = Timestamp(Date().time),
        apiKey = UUID.randomUUID().toString()
    )

    sessionFactory.inTransaction {
        it.persist(organization)
    }

    return organization.id!!
}

fun getOrganizationById(organizationId: IdType): Organization {
    var result: Organization? = null
    sessionFactory.inTransaction {
        result = it.get(Organization::class.java, organizationId);
    }

    return result ?: throw OrganizationNotFoundException(organizationId)
}

fun getOrganizationByAuthId(authId: String): Organization {
    var result: Organization? = null
    sessionFactory.inTransaction {
        result = it.createQuery("select org from Organization org where org.authId=:authId", Organization::class.java)
            .setParameter("authId", authId).singleResultOrNull
    }
    return result ?: throw OrganizationAuthIdNotFoundException(authId)
}

fun getOrganizations(
    verified: Boolean = true,
    pageSize: Int? = null,
    pageId: Int = 0,
): List<Organization> {
    var result = emptyList<Organization>()

    sessionFactory.inTransaction {
        val query = it.createQuery(
            "select org from Organization org where org.verified=:verified",
            Organization::class.java
        ).setParameter("verified", verified)

        if (pageSize != null) {
            val firstResult = pageId * pageSize;
            query.firstResult = firstResult
            query.maxResults = pageSize
        }
        result = query.resultList
    }

    return result
}

fun editOrganization(
    organizationId: IdType,
    name: String? = null,
    avatarUrl: String? = null,
    location: String? = null,
    siteUrl: String? = null,
    contactEmail: String? = null
) {
    val organization = getOrganizationById(organizationId)
    name?.let { organization.name = it }
    avatarUrl?.let { organization.avatarUrl = it }
    location?.let { organization.location = it }
    siteUrl?.let { organization.siteUrl = it }
    contactEmail?.let { organization.contactEmail = it }

    sessionFactory.inTransaction {
        it.merge(organization)
    }
}
