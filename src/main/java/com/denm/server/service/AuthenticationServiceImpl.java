package com.denm.server.service;

import com.denm.server.exception.DBException;
import com.denm.server.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by DENM on 10.01.2016.
 */
public class AuthenticationServiceImpl implements AuthenticationService {
    private final Logger logger = Logger.getGlobal();
    private static volatile AuthenticationServiceImpl instance = null;
    private DBService dbService = DBService.getInstance();

    private AuthenticationServiceImpl() {
        dbService.printConnectInfo();
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
    public void storeCredentials(String login, String password) throws DBException {
        dbService.addUser(login, password);
        logger.info("Credentials stored: login=" + login + ", password=" + password);
    }

    @Override
    public boolean authenticate(String login, String password) throws DBException {
        logger.info("Authenticating user: login=" + login + ", password=" + password);
        long userId = dbService.getUserId(login);
        User user = dbService.getUser(dbService.getUserId(login));
        return user != null && user.getPassword().equals(password);
    }
}
