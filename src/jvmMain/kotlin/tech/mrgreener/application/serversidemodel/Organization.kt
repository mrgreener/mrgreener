/**
 * MrGreener
 * Loyalty program for green initiatives
 *
 * The version of the OpenAPI document: 1.0.0
 *
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package tech.mrgreener.application.serversidemodel


/**
 *
 * @param name
 * @param username
 * @param description
 * @param contactEmail
 * @param registeredOn
 * @param avatarUrl
 * @param location
 * @param siteUrl
 */
data class Organization(
    val organization_id: Long,
    val name: String,
    val username: String,
    val description: String,
    val contact_email: String,
    val registered_on: String? = null,
    val avatar_url: String? = null,
    val location: String? = null,
    val site_url: String? = null
) {
    constructor (organizationObj: tech.mrgreener.application.model.entities.Organization) : this(
        organizationObj.id!!,
        organizationObj.name,
        organizationObj.username,
        organizationObj.description,
        organizationObj.contactEmail,
        organizationObj.registeredOn.toString(),
        organizationObj.avatarUrl,
        organizationObj.location,
        organizationObj.siteUrl
    )
}
