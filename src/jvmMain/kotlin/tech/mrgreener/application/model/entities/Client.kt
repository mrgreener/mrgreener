package tech.mrgreener.application.model.entities

import jakarta.persistence.*

@Entity
class Client(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    @Column(nullable = false)
    val username: String,

    )