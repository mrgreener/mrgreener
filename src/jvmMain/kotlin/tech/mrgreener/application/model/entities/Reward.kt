package tech.mrgreener.application.model.entities

import jakarta.persistence.*
import java.io.File
import java.sql.Timestamp

@Entity
@Table(name = "Rewards")
class Reward(
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
     * id of organization which created current reward
     */
    @Column(nullable = false)
    val organizationId: String,

    @Column(nullable = false)
    val price: Long,

    @Column(name = "is_active", nullable = false)
    val isActive: Boolean,

    @Column(nullable = false)
    val pictureUrl: String,

    @Column(name = "registration_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    val registrationDate: Timestamp,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reward")
    val vouchers: List<RewardVoucher>,
)