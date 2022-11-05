package tech.mrgreener.application.model.entities

import jakarta.persistence.*
import tech.mrgreener.application.model.IdType
import tech.mrgreener.application.model.MoneyType
import java.sql.Timestamp

@Entity
@Table(name = "Promotions")
class Promotion(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: IdType = -1,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false, columnDefinition = "text")
    val description: String,

    @Column(name = "short_description", nullable = true)
    val shortDescription: String? = null,

    @Column(nullable = false)
    val verified: Boolean = false,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", referencedColumnName = "id")
    val organization: Organization,

    @Column(name = "reward_points", nullable = false)
    val rewardPoints: MoneyType,

    @Column(nullable = false)
    val isActive: Boolean = true,

    @Column(nullable = true)
    val pictureUrl: String? = null,

    @Column(name = "created_on", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    val createdOn: Timestamp,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "promotion")
    val vouchers: List<PromotionVoucher> = emptyList(),
)