package tech.mrgreener.application.model.entities

import jakarta.persistence.*
import java.sql.Timestamp

@Entity
@Table(name = "Client")
class Client(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(nullable = false)
    val username: String,

    @Column(nullable = false)
    val isAdmin: Boolean,

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    val registered_on: Timestamp,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    val promotionVouchersActivations: List<PromotionVoucherActivation>,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    val rewardVouchersActivations: List<RewardVoucherActivation>
)