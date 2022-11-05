package tech.mrgreener.application.model.entities

import jakarta.persistence.*
import tech.mrgreener.application.model.IdType
import java.sql.Timestamp

@Entity
@Table(name = "Organizations")
class Organization(
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
    val verified: Boolean = false,

    @Column(nullable = true, columnDefinition = "text")
    val description: String,

    @Column(name = "avatar_url", nullable = true)
    val avatarUrl: String? = null,

    @Column(nullable = true)
    val location: String? = null,

    @Column(name = "site_url", nullable = true)
    val siteUrl: String? = null,

    @Column(name = "contact_email", nullable = false)
    val contactEmail: String,

    @Column(name = "registered_on", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    val registeredOn: Timestamp
)