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

import tech.mrgreener.application.model.entities.Organization
import tech.mrgreener.application.model.entities.Promotion


/**
 *
 * @param name
 * @param priceString
 * @param descriptionShort
 * @param descriptionLong
 * @param rewardPoints
 * @param companyName
 * @param linkToStore
 * @param verified
 * @param isActive
 * @param promotionId
 * @param pictureUrl
 * @param companyAvatarUrl
 * @param whereToGet
 */
data class Promotion(
    val name: String,
//    val priceString: String,
    val descriptionShort: String,
    val descriptionLong: String,
    val rewardPoints: Long,
    val companyName: String,
    val linkToStore: String,
    val verified: Boolean,
    val isActive: Boolean,
    val promotionId: Long? = null,
    val pictureUrl: String? = null,
    val companyAvatarUrl: String? = null,
    val whereToGet: String? = null
) {
    constructor(promotionObj: Promotion, organizationObj: Organization) : this(
        promotionObj.name,
        promotionObj.shortDescription ?: "",
        promotionObj.description,
        promotionObj.rewardPoints ?: 0,
        organizationObj.name,
        organizationObj.siteUrl ?: "",
        promotionObj.verified,
        promotionObj.isActive,
        promotionObj.id!!,
        promotionObj.pictureUrl,
        organizationObj.avatarUrl,
        organizationObj.location
    )
}

