package com.ud.debitwallet.controller;

import com.ud.debitwallet.helper.DebitCardUtil;
import com.ud.debitwallet.model.DebitCard;
import com.ud.debitwallet.model.Transaction;
import com.ud.debitwallet.model.User;
import com.ud.debitwallet.repository.DebitCardRepository;
import com.ud.debitwallet.repository.TransactionRepository;
import com.ud.debitwallet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class FormController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DebitCardRepository debitCardRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @PostMapping("login_handler")
    public RedirectView loginHandler(
            @ModelAttribute User user,
            HttpSession httpSession,
            RedirectView redirectView,
            HttpServletRequest httpServletRequest
    ){
        if (userRepository.isEmailExist(user)){
            if (userRepository.userLoginValidation(user)){
                httpSession.setAttribute("user",userRepository.getUser(user));
                redirectView.setUrl(httpServletRequest.getContextPath()+"/dashboard");
            }else{
                httpSession.setAttribute("error","Password is incorrect");
                httpSession.setAttribute("url","login");
                redirectView.setUrl(httpServletRequest.getContextPath()+"/error");
            }
        }else{
            httpSession.setAttribute("error","User with this email id does not exist");
            httpSession.setAttribute("url","signup");
            redirectView.setUrl(httpServletRequest.getContextPath()+"/error");
        }
        return redirectView;
    }

    @PostMapping("signup_handler")
    public RedirectView signupHandler(
            @ModelAttribute User user,
            RedirectView redirectView,
            HttpSession httpSession,
            HttpServletRequest httpServletRequest
    ){
        if (!userRepository.isEmailExist(user)){
            userRepository.saveUser(user);
            redirectView.setUrl(httpServletRequest.getContextPath()+"/index");
        }else {
            httpSession.setAttribute("error","Email already registered please login");
            httpSession.setAttribute("url","login");
            redirectView.setUrl(httpServletRequest.getContextPath()+"/error");
        }
        return redirectView;
    }

    @PostMapping("generate_debit_card")
    public RedirectView generateDebitCard(
            @RequestParam int pin,
            HttpSession httpSession,
            RedirectView redirectView,
            HttpServletRequest httpServletRequest
    ){
        User user = (User) httpSession.getAttribute("user");
        DebitCard debitCard = new DebitCard(DebitCardUtil.generateRandomDebitCardNumber(),DebitCardUtil.generateExpirationMonth(),DebitCardUtil.generateExpirationYear(),pin,user.getName());
        debitCardRepository.saveDebitCard(debitCard);
        userRepository.addDebitCardToUser(user,debitCard);
        redirectView.setUrl(httpServletRequest.getContextPath()+"/logout");
        return redirectView;
    }

    @PostMapping("add_money_handler")
    public RedirectView addMoneyHandler(
            @RequestParam long amount,
            RedirectView redirectView,
            HttpServletRequest httpServletRequest,
            HttpSession httpSession
    ){
        if (amount<0){
            httpSession.setAttribute("error","Negative amount can't be added");
            httpSession.setAttribute("url","addmoney");
            redirectView.setUrl(httpServletRequest.getContextPath()+"/error");
        }else {
            User user = (User) httpSession.getAttribute("user");

            debitCardRepository.addBalanceIntoDebitCard(user,amount);
            Transaction transaction = new Transaction("Credit",amount);
            transactionRepository.saveTransaction(transaction);
            transactionRepository.addTransactionToDebitCard(user,transaction);
            redirectView.setUrl(httpServletRequest.getContextPath()+"/logout");
        }
        return redirectView;
    }

    @PostMapping("withdraw_money_handler")
    public RedirectView withdrawMoneyHandler(
            @RequestParam int amount,
            RedirectView redirectView,
            HttpServletRequest httpServletRequest,
            HttpSession httpSession
    ){
        User user = (User) httpSession.getAttribute("user");
        if (amount>user.getDebitCard().getBalance()){
            httpSession.setAttribute("error","insufficient balance");
            httpSession.setAttribute("url","dashboard");
            redirectView.setUrl(httpServletRequest.getContextPath()+"/error");
        } else if (amount<0) {
            httpSession.setAttribute("error","Can't withdraw negative amount");
            httpSession.setAttribute("url","dashboard");
            redirectView.setUrl(httpServletRequest.getContextPath()+"/error");
        }else {
            debitCardRepository.withdrawBalanceIntoDebitCard(user,amount);
            Transaction transaction = new Transaction("Debit",amount);
            transactionRepository.saveTransaction(transaction);
            transactionRepository.addTransactionToDebitCard(user,transaction);
            redirectView.setUrl(httpServletRequest.getContextPath()+"/logout");
        }
        return redirectView;
    }
}
