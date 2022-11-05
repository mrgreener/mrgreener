package tech.mrgreener.application.model.entities

import jakarta.persistence.*
import java.sql.Timestamp

@Entity
@Table(name = "PromotionVouchers")
class PromotionVoucher(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(nullable = false)
    val code: String,

    @Column(nullable = false)
    val rewardPoints: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_id", referencedColumnName = "id")
    val promotion: Promotion,

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    val issuedOn: Timestamp,

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "voucher", optional = true)
    val activation: PromotionVoucherActivation?
)