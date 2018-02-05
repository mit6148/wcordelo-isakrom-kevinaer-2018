package io.smartraise.service;

import io.smartraise.model.login.SignUp;
import io.smartraise.model.login.Credential;

/**
 * Service for {@link Credential} model
 */
public interface CredentialService {

    /**
     * Checks if there is a {@link Credential} with the username
     *
     * @param username    the username of a {@link Credential} user
     * @return true iff there exists a {@link Credential} with that username
     */
    boolean exists(String username);

    Credential get(String username);

    /**
     * Makes sure login attempt matches an actual user and contains
     * valid login information.
     *
     * @param signUp    the login attempt information {@link SignUp}
     * @return {@link Credential} matching the login attempt if login information was corret.
     * @throws Exception
     */
    Credential authenticate(SignUp signUp) throws Exception;

    /**
     * Creates a new user and creates the account type as specified in new user
     *
     * @param signUp    the signup attempt information {@link SignUp}
     * @return  User credential {@link Credential} created if successfully created new user.
     * @throws Exception
     */
    Credential create(SignUp signUp) throws Exception;

    /**
     * Verifies that the currently logged in user matches the id
     *
     * @param user  the user name/email logged in with
     * @param id    the id of the account being accessed.
     * @return  true if the user credential matches that of the id
     * @throws Exception
     */
    boolean verify(String user, String id);

    /**
     * Checks if user is an admin
     *
     * @param user  the username/email logged in with
     * @return true if user has admin privileges, false otherwise
     * @throws Exception
     */
    @Deprecated
    boolean isAdmin(String user) throws Exception;

    /**
     * Adds a new type of user type {@link Credential.UserType} to a user's {@link Credential}
     *
     * @param type  the type of user account {@link Credential.UserType}
     * @param id    the id of the user
     */
    @Deprecated
    void addType(Credential.UserType type, String id);

    /**
     * Removes a  type of user type {@link Credential.UserType} from user's {@link Credential}
     *
     * @param type  the type of user account {@link Credential.UserType}
     * @param id    the id of the user
     */
    @Deprecated
    void removeType(Credential.UserType type, String id);

    /**
     * Checks if {@link Credential} contains a {@link Credential.UserType} user account
     * @param type  the type of user account {@link Credential.UserType}
     * @param id    the id of the user
     * @return
     */
    @Deprecated
    boolean containsType(Credential.UserType type, String id);

    void delete(String id);
}
