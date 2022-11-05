package tech.mrgreener.application.model.entities

import jakarta.persistence.*
import java.io.File
import java.sql.Timestamp

@Entity
@Table(name = "rewards")
class Reward(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false, columnDefinition = "text")
    val description: String,

    /***
     * id of organization which created current reward
     */
    @Column(nullable = false)
    val organization_id: String,

    @Column(nullable = true)
    val price: Long,

    @Column(nullable = false)
    val is_active: Boolean,

    @Column(nullable = false)
    val picture_url: String,

    @Column(name = "registration_date", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    val registrationDate: Timestamp
)