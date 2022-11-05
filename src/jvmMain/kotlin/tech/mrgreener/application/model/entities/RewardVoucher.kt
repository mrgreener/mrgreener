package tech.mrgreener.application.model.entities

import jakarta.persistence.*
import tech.mrgreener.application.model.IdType
import java.sql.Timestamp

@Entity
@Table(name = "RewardVouchers")
class RewardVoucher(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: IdType? = null,

    @Column(nullable = false)
    val content: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reward_id", referencedColumnName = "id")
    val reward: Reward,

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    val issuedOn: Timestamp,

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "voucher", optional = true)
    val activation: RewardVoucherActivation?
)