package tech.mrgreener.application.model.entities

import jakarta.persistence.*
import java.sql.Timestamp

@Entity
@Table(name = "RewardVoucherActivations")
class RewardVoucherActivation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reward_voucher_id", referencedColumnName = "id")
    val voucher: RewardVoucher,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    val client: Client,

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    val activatedOn: Timestamp
)