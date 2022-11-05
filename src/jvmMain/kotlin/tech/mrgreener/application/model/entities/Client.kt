package tech.mrgreener.application.model.entities

import jakarta.persistence.*
import tech.mrgreener.application.model.IdType
import java.sql.Timestamp

@Entity
@Table(name = "Client")
class Client(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: IdType = -1,

    @Column(unique = true, nullable = false)
    val authId: String,

    @Column(unique = true, nullable = false)
    val username: String,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val isAdmin: Boolean = false,

    @Column(name = "registered_on", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    val registeredOn: Timestamp,

    @Column
    val bio: String? = "Hello, I am new here!",

    @Column
    val avatarUrl: String? = null,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    val promotionVouchersActivations: List<PromotionVoucherActivation> = emptyList(),

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    val rewardVouchersActivations: List<RewardVoucherActivation> = emptyList()
)