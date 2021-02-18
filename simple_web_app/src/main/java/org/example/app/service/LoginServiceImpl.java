package org.example.app.service;

import org.example.app.repository.UserRepository;
import org.example.web.dto.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;

@Service
public class LoginServiceImpl implements LoginService<LoginForm> {
    private final Logger logger = Logger.getLogger(this.getClass());

    private final UserRepository<LoginForm> userRepo;

    @Autowired
    public LoginServiceImpl(UserRepository repo) {
        this.userRepo = repo;
        /* add root user */
        LoginForm user = new LoginForm();
        user.setUsername("root");
        user.setPassword("123");
        userRepo.store(user);
    }

    @Override
    public boolean authenticate(LoginForm loginForm){
        logger.info("try login:" + loginForm);
        return userRepo.existsItem(loginForm);
    }

    @Override
    public boolean createAccount(LoginForm loginForm){
        logger.info("try create new account:" + loginForm);
        if (!userRepo.checkByName(loginForm)) {
                if (loginForm.getUsername().equals("")||loginForm.getPassword().equals("")){
                    logger.info("user don't store because it has empty fields:" + loginForm);
                }else{
                    userRepo.store(loginForm);
                    return true;
                }
        }
        return false;
    }
}
