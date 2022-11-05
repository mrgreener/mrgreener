package tech.mrgreener.application.controller

import tech.mrgreener.application.model.MoneyType
import tech.mrgreener.application.model.entities.Organization
import tech.mrgreener.application.model.entities.Promotion
import tech.mrgreener.application.model.sessionFactory
import java.sql.Timestamp
import java.util.*

fun addPromotion(
    organization: Organization,
    name: String,
    description: String,
    rewardPoints: MoneyType,
    shortDescription: String? = null,
    pictureUrl: String? = null
) {
    sessionFactory.inTransaction {
        val promotion = Promotion(
            name = name,
            description = description,
            organization = organization,
            shortDescription = shortDescription,
            createdOn = Timestamp(Date().time),
            rewardPoints = rewardPoints,
            pictureUrl = pictureUrl,
        )

        it.persist(promotion)
    }
}