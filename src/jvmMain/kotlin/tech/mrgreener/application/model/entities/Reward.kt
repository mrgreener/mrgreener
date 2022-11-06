package tech.mrgreener.application.model.entities

import jakarta.persistence.*
import tech.mrgreener.application.model.IdType
import tech.mrgreener.application.model.MoneyType
import java.sql.Timestamp

@Entity
@Table(name = "Rewards")
class Reward(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: IdType? = null,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false, columnDefinition = "text")
    val description: String,

    @Column(nullable = false)
    val content: String, // promo-codes mock

    @Column(name = "short_description", nullable = true)
    val shortDescription: String? = null,

    @Column(nullable = false)
    val verified: Boolean = true,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", referencedColumnName = "id")
    val organization: Organization,

    @Column(nullable = false)
    val price: MoneyType,

    @Column(name = "is_active", nullable = false)
    val isActive: Boolean = true,

    @Column(nullable = true)
    val pictureUrl: String? = null,

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    val createdOn: Timestamp,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reward")
    val vouchers: List<RewardVoucher> = emptyList(),
)