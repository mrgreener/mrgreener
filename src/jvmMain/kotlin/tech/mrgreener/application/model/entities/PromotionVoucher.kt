package tech.mrgreener.application.model.entities

import jakarta.persistence.*
import java.sql.Timestamp

// Mock
@Entity
class Promotion(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
);



@Entity
class PromotionVoucher(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(nullable = false)
    val code: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_id", referencedColumnName = "id")
    val promotion: Promotion,

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    val issued_on: Timestamp,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "promotionVoucher")
    val activations: List<PromotionVoucherActivation>
)