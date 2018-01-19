package io.smartraise.service;

import io.smartraise.model.login.LogIn;
import io.smartraise.model.login.Credential;

public interface CredentialService {

    Credential authenticate(LogIn logIn) throws Exception;

    void create(LogIn logIn) throws Exception;

    boolean verify(String user, String id) throws Exception;
}
