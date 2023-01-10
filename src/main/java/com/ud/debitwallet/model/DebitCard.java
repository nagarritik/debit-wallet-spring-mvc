package com.ud.debitwallet.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "debit_card")
public class DebitCard {
    @Id
    @Column(name = "card_number")
    private String CardNumber;
    private long balance;
    @Column(name = "expiration_month")
    private long expirationMonth;
    @Column(name = "expiration_year")
    private long expirationYear;
    @Column(name = "security_code")
    private int securityCode;
    @Column(name = "card_holder_name")
    private String cardHolderName;
    @OneToMany
    private List<Transaction> transactionList;

    public DebitCard(String cardNumber, long expirationMonth, long expirationYear, int securityCode, String cardHolderName) {
        CardNumber = cardNumber;
        this.balance = 0;
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
        this.securityCode = securityCode;
        this.cardHolderName = cardHolderName;
        this.transactionList = new ArrayList<>();
    }

    public DebitCard() {
        super();
    }

    public String getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(String cardNumber) {
        CardNumber = cardNumber;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public long getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(long expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public long getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(long expirationYear) {
        this.expirationYear = expirationYear;
    }

    public int getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    @Override
    public String toString() {
        return "DebitCard{" +
                "CardNumber='" + CardNumber + '\'' +
                ", balance=" + balance +
                ", expirationMonth=" + expirationMonth +
                ", expirationYear=" + expirationYear +
                ", securityCode=" + securityCode +
                ", cardHolderName='" + cardHolderName + '\'' +
                ", transactionList=" + transactionList +
                '}';
    }
}
