package tech.mrgreener.application.model

import org.hibernate.SessionFactory
import org.hibernate.cfg.Configuration

lateinit var sessionFactory: SessionFactory

fun initDbManager() {
    sessionFactory = Configuration()
//        .addAnnotatedClass(Person::class.java)
        .buildSessionFactory()
}