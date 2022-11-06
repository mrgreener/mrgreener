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

import tech.mrgreener.application.model.entities.Reward


/**
 *
 * @param name
 * @param descriptionShort
 * @param descriptionLong
 * @param pricePoints
 * @param companyName
 * @param verified
 * @param isActive
 * @param rewardId Internal reward identifier
 * @param pictureUrl
 * @param companyAvatarUrl
 */
data class Reward(
    val name: String,
    val descriptionShort: String,
    val descriptionLong: String,
    val pricePoints: Long,
    val companyName: String,
    val verified: Boolean,
    val isActive: Boolean,
    /* Internal reward identifier */
    val rewardId: Long? = null,
    val pictureUrl: String? = null,
    val companyAvatarUrl: String? = null
) {
    constructor(rewardObj: Reward) : this(
        rewardObj.name,
        rewardObj.shortDescription ?: "",
        rewardObj.description,
        rewardObj.price,
        rewardObj.organization.name,
        rewardObj.verified,
        rewardObj.isActive,
        rewardObj.id,
        rewardObj.pictureUrl,
        rewardObj.organization.avatarUrl
    )
}

