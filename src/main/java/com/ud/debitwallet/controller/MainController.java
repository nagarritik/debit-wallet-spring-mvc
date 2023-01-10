package com.ud.debitwallet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {
    @GetMapping("/")
    public String home(){
        return "index";
    }
    @GetMapping("index")
    public String index(){
        return "index";
    }

    @GetMapping("login")
    public String login(){
        return "login";
    }

    @GetMapping("signup")
    public String signup(){
        return "signup";
    }

    @GetMapping("dashboard")
    public String dashboard(
            HttpSession httpSession
    ){
        return httpSession.getAttribute("user")==null?"index":"dashboard";
    }

    @GetMapping("error")
    public String error(){
        return "error";
    }

    @GetMapping("logout")
    public String logout(){
        return "logout";
    }

    @GetMapping("debit")
    public String debit(){
        return "debit";
    }

    @GetMapping("addmoney")
    public String addMoney(){
        return "addmoney";
    }

    @GetMapping("withdrawmoney")
    public String withdrawMoney(){
        return "withdrawmoney";
    }
}
