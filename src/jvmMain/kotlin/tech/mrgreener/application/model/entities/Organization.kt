package tech.mrgreener.application.model.entities

import jakarta.persistence.*
import java.sql.Timestamp

@Entity
@Table(name = "Organizations")
class Organization(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val verified: Boolean,

    @Column(nullable = true, columnDefinition = "text")
    val description: String,

    @Column(name = "registration_date", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    val registrationDate: Timestamp
)