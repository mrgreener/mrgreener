package tech.mrgreener.application.model.entities

import jakarta.persistence.*
import tech.mrgreener.application.model.IdType
import java.sql.Timestamp

@Entity
@Table(name = "Organizations")
class Organization(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: IdType? = null,

    @Column(unique = true, nullable = false)
    val authId: String,

    @Column(unique = true, nullable = false)
    val username: String,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    val apiKey: String,

    @Column(nullable = false)
    val verified: Boolean = true,

    @Column(nullable = true, columnDefinition = "text")
    val description: String,

    @Column(name = "avatar_url", nullable = true)
    var avatarUrl: String? = null,

    @Column(nullable = true)
    var location: String? = null,

    @Column(name = "site_url", nullable = true)
    var siteUrl: String? = null,

    @Column(name = "contact_email", nullable = false)
    var contactEmail: String,

    @Column(name = "registered_on", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    val registeredOn: Timestamp
)