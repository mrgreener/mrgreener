package tech.mrgreener.application.model.entities

import jakarta.persistence.*
import java.sql.Timestamp

@Entity
@Table(name = "Promotions")
class Promotion(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false, columnDefinition = "text")
    val description: String,

    @Column(name = "short_description", nullable = true)
    val shortDescription: String,

    @Column(nullable = false)
    val verified: Boolean,

    /***
     * id of organization which created current promotion
     */
    @Column(nullable = false)
    val organization_id: String,

    @Column(name = "reward_points", nullable = false)
    val rewardPoints: Long,

    @Column(nullable = false)
    val is_active: Boolean,

    @Column(nullable = false)
    val picture_url: String,

    @Column(name = "registration_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    val registrationDate: Timestamp,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "promotion")
    val vouchers: List<PromotionVoucher>,
)