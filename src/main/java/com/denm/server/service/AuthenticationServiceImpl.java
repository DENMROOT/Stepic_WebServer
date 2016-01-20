package com.denm.server.service;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by DENM on 10.01.2016.
 */
public class AuthenticationServiceImpl implements AuthenticationService {
    private final Logger logger = Logger.getGlobal();
    private static volatile AuthenticationServiceImpl instance = null;
    Map<String,String> store;

    private AuthenticationServiceImpl() {
        this.store = new HashMap<>();
    }

    public static AuthenticationService getInstance () {
        if (instance == null) {
            synchronized (AuthenticationServiceImpl.class) {
                if (instance == null) {
                    instance = new AuthenticationServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public void storeCredentials(String login, String password) {
        store.put("login", login);
        store.put("password", password);
        logger.info("Credentials stored: login=" +login + ", password=" + password);
    }

    @Override
    public boolean authenticate(String login, String password) {
        return store.containsValue(login) && store.containsValue(password);
    }
}
