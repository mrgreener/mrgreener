package tech.mrgreener.application.model

import org.hibernate.SessionFactory
import org.hibernate.cfg.Configuration
import tech.mrgreener.application.model.entities.Organization
import tech.mrgreener.application.model.entities.Promotion
import tech.mrgreener.application.model.entities.Reward

lateinit var sessionFactory: SessionFactory

fun initDbManager() {
    sessionFactory = Configuration()
        .addAnnotatedClass(Organization::class.java)
        .addAnnotatedClass(Reward::class.java)
        .addAnnotatedClass(Promotion::class.java)
        .buildSessionFactory()
}