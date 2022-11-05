package tech.mrgreener.application.model.entities

import jakarta.persistence.*
import java.sql.Timestamp

@Entity
class PromotionVoucherActivation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_voucher_id", referencedColumnName = "id")
    val promotionVoucher: PromotionVoucher,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    val client: Client,

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    val activatedOn: Timestamp
)