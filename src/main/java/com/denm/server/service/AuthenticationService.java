package com.denm.server.service;

import com.denm.server.exception.DBException;

/**
 * Created by DENM on 10.01.2016.
 */
public interface AuthenticationService {
    void storeCredentials(String login, String password) throws DBException;
    boolean authenticate(String login, String password) throws DBException;
}
