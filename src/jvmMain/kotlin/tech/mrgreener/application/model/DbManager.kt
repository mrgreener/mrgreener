package tech.mrgreener.application.model

import org.hibernate.SessionFactory
import org.hibernate.cfg.Configuration
import tech.mrgreener.application.model.entities.Client

lateinit var sessionFactory: SessionFactory

fun initDbManager() {
    sessionFactory = Configuration()
        .addAnnotatedClass(Client::class.java)
        .buildSessionFactory()
}