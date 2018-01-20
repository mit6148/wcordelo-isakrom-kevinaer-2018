package io.smartraise.service;

import io.smartraise.model.login.SignUp;
import io.smartraise.model.login.Credential;

public interface CredentialService {

    Credential authenticate(SignUp signUp) throws Exception;

    Credential create(SignUp signUp) throws Exception;

    boolean verify(String user, String id) throws Exception;

    boolean isAdmin(String user) throws Exception;

    void addType(Credential.UserType type, String id);
}
