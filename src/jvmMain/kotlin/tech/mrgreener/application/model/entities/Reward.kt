package tech.mrgreener.application.model.entities

import jakarta.persistence.*
import tech.mrgreener.application.model.IdType
import tech.mrgreener.application.model.MoneyType
import java.io.File
import java.sql.Timestamp

@Entity
@Table(name = "Rewards")
class Reward(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: IdType = -1,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false, columnDefinition = "text")
    val description: String,

    @Column(name = "short_description")
    val shortDescription: String,

    @Column(nullable = false)
    val verified: Boolean = false,

    /***
     * id of organization which created current reward
     */
    @Column(nullable = false)
    val organizationId: String,

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
    val vouchers: List<RewardVoucher>,
)