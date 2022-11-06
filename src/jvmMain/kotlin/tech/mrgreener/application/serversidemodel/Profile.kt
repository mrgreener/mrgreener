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

import tech.mrgreener.application.model.entities.Client


/**
 *
 * @param registeredOn
 * @param name
 * @param description
 * @param points
 * @param username
 * @param avatarUrl
 */
data class Profile(
    val registeredOn: String,
    val name: String,
    val description: String,
    val points: Long,
    val username: String? = null,
    val avatarUrl: String? = null
) {
    constructor(clientObj: Client, balance: Long) : this(
        clientObj.registeredOn.toString(),
        clientObj.name,
        clientObj.bio ?: "",
        balance,
        clientObj.username,
        clientObj.avatarUrl
    )
}
