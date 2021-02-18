package org.example.web.controller;

import org.example.app.service.LoginService;
import org.example.web.dto.LoginForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.apache.log4j.Logger;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    private final Logger logger = Logger.getLogger(this.getClass());

    LoginService<LoginForm> loginService;

    @Autowired
    public LoginController(LoginService<LoginForm> loginService){
        this.loginService = loginService;
    }

    @GetMapping
    public String login(Model model){
        logger.info("GET /login return login_page.html");
        model.addAttribute("loginForm", new LoginForm());
        return "login_page";
    }

    @PostMapping("/auth")
    public String login(LoginForm loginForm){
        logger.info("POST /login return login_page.html");

        if (loginService.authenticate(loginForm)){
            logger.info("login OK redirect to book shelf");
            return "redirect:/books/shelf";
        }else{
            logger.info("login FAIL redirect to login");
            return "redirect:/login";
        }
    }

    @GetMapping("/new_user")
    public String newUser(Model model){
        logger.info("GET /new_user return register_new_user_page.html");
        model.addAttribute("newUserForm", new LoginForm());
        return "register_new_user_page";
    }

    @PostMapping("/new_user")
    public String newUser(LoginForm newUserForm){
        if (loginService.createAccount(newUserForm)){
            logger.info("new account success created redirect to login");
        }else{
            logger.info("new account created FAIL redirect to login");
        }
        return "redirect:/login";
    }
}
