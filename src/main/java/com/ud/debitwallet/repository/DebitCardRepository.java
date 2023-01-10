package com.ud.debitwallet.repository;

import com.ud.debitwallet.model.DebitCard;
import com.ud.debitwallet.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class DebitCardRepository {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Transactional
    public void saveDebitCard(DebitCard debitCard){
        hibernateTemplate.save(debitCard);
    }

    @Transactional
    public void addBalanceIntoDebitCard(User user, long amount){
        DebitCard debitCard = hibernateTemplate.get(DebitCard.class,user.getDebitCard().getCardNumber());
        debitCard.setBalance(debitCard.getBalance()+amount);
    }

    @Transactional
    public void withdrawBalanceIntoDebitCard(User user, long amount){
        DebitCard debitCard = hibernateTemplate.get(DebitCard.class,user.getDebitCard().getCardNumber());
        debitCard.setBalance(debitCard.getBalance()-amount);
    }
}
