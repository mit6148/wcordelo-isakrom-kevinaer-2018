package io.smartraise.service;

import io.smartraise.model.accounts.login.LogIn;
import io.smartraise.model.accounts.login.Credential;

public interface CredentialService {

    Credential authenticate(LogIn logIn) throws Exception;

    Credential create(LogIn logIn) throws Exception;

    boolean verify(String user, String id) throws Exception;
}
