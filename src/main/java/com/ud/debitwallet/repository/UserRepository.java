package com.ud.debitwallet.repository;

import com.ud.debitwallet.model.DebitCard;
import com.ud.debitwallet.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import java.util.Objects;

@Repository
public class UserRepository {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Transactional
    public void saveUser(User user){
        hibernateTemplate.save(user);
    }

    public boolean isEmailExist(User user){
        return hibernateTemplate.get(User.class,user.getEmail())!=null;
    }

    public boolean userLoginValidation(User user){
        User user1 = hibernateTemplate.get(User.class,user.getEmail());
        return Objects.equals(user1.getPassword(), user.getPassword());
    }

    public User getUser(User user){
        return hibernateTemplate.get(User.class,user.getEmail());
    }

    @Transactional
    public void addDebitCardToUser(User user, DebitCard debitCard){
        hibernateTemplate.get(User.class,user.getEmail()).setDebitCard(debitCard);
    }
}
