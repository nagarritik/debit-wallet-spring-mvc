package com.ud.debitwallet.repository;

import com.ud.debitwallet.model.DebitCard;
import com.ud.debitwallet.model.Transaction;
import com.ud.debitwallet.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TransactionRepository {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Transactional
    public void saveTransaction(Transaction transaction){
        hibernateTemplate.save(transaction);
    }

    @Transactional
    public void addTransactionToDebitCard(User user,Transaction transaction){
        hibernateTemplate.get(User.class,user.getEmail()).getDebitCard().getTransactionList().add(hibernateTemplate.get(Transaction.class,transaction.getId()));
    }
}
