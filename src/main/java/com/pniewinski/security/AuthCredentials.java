package com.pniewinski.security;

import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Created by niewinskip on 2017-03-23.
 */
public class AuthCredentials {

    private String username;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}



