package com.denm.server.service;

/**
 * Created by DENM on 10.01.2016.
 */
public interface AuthenticationService {
    void storeCredentials(String login, String password);
    boolean authenticate(String login, String password);
}
