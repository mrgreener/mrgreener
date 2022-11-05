package tech.mrgreener.application.model

import org.hibernate.SessionFactory
import org.hibernate.cfg.Configuration
import tech.mrgreener.application.model.entities.Client
import tech.mrgreener.application.model.entities.PromotionVoucher
import tech.mrgreener.application.model.entities.PromotionVoucherActivation

lateinit var sessionFactory: SessionFactory

fun initDbManager() {
    sessionFactory = Configuration()
        .addAnnotatedClass(Client::class.java)
        .addAnnotatedClass(PromotionVoucher::class.java)
        .addAnnotatedClass(PromotionVoucherActivation::class.java)
        .buildSessionFactory()
}