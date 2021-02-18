package org.example.app.service;

import org.example.web.dto.LoginForm;

public interface LoginService<T> {
    boolean authenticate(T loginForm);

    boolean createAccount(T loginForm);
}
