package tech.mrgreener.application.model

import org.hibernate.SessionFactory
import org.hibernate.cfg.Configuration
import tech.mrgreener.application.model.entities.*

lateinit var sessionFactory: SessionFactory

typealias IdType = Long
typealias MoneyType = Long

fun initDbManager() {
    sessionFactory = Configuration()
        .addAnnotatedClass(Client::class.java)
        .addAnnotatedClass(PromotionVoucher::class.java)
        .addAnnotatedClass(PromotionVoucherActivation::class.java)
        .addAnnotatedClass(RewardVoucher::class.java)
        .addAnnotatedClass(RewardVoucherActivation::class.java)
        .addAnnotatedClass(Organization::class.java)
        .addAnnotatedClass(Reward::class.java)
        .addAnnotatedClass(Promotion::class.java)
        .buildSessionFactory()
}