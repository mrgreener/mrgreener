package tech.mrgreener.application.model.entities

import jakarta.persistence.*
import tech.mrgreener.application.model.IdType
import java.sql.Timestamp

@Entity
@Table(name = "PromotionVoucherActivations")
class PromotionVoucherActivation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: IdType = -1,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_voucher_id", referencedColumnName = "id")
    val voucher: PromotionVoucher,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    val client: Client,

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    val activatedOn: Timestamp
)