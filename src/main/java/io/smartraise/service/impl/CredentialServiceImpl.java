package io.smartraise.service.impl;

import io.smartraise.helper.Parser;
import io.smartraise.model.login.SignUp;
import io.smartraise.security.PasswordHashing;
import io.smartraise.dao.CredentialDAO;
import io.smartraise.model.login.Credential;
import io.smartraise.service.CredentialService;
import io.smartraise.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;

public class CredentialServiceImpl implements CredentialService{

    @Autowired
    private CredentialDAO credentialDAO;

    @Autowired
    private MemberService memberService;

    private void processNewCred(Credential credential) throws Exception {
        if (credential.getTypes().contains(Credential.UserType.MEMBER)) {
            memberService.createFromCredential(credential);
        } else if (credential.getTypes().contains(Credential.UserType.CHARITY)) {

        }
    }

    @Override
    public Credential authenticate(SignUp signUp) throws Exception {
        Credential creds;
        if (signUp.getEmail() == "") {
            creds = credentialDAO.findOne(signUp.getUsername());
        } else if (signUp.getUsername() == "") {
            creds = credentialDAO.findByEmail(signUp.getEmail());
        } else {
            throw new Exception("no username or email provided");
        }
        if (creds == null) {
            throw new Exception("No user found");
        }
        if (!PasswordHashing.authenticate(signUp.getPassword(), creds.getSalt(), creds.getHash())){
            throw new Exception("Invalid credentials!");
        }
        return creds;
    }

    @Override
    public Credential create(SignUp signUp) throws Exception {
        if (signUp.getEmail() == "" || signUp.getUsername() == "") {
            throw new Exception("Need email and id!");
        }

        if (credentialDAO.exists(signUp.getUsername())) {
            throw new Exception("User already exists");
        } else {
            byte[] salt = PasswordHashing.generateSalt();
            byte[] hash = PasswordHashing.hash(signUp.getPassword(), salt);
            Credential newCredential =
                    new Credential(
                            signUp.getType(),
                            signUp.getEmail(),
                            hash,
                            salt,
                            signUp.getUsername());

            credentialDAO.save(newCredential);
            processNewCred(newCredential);
            return newCredential;
        }
    }

    @Override
    public boolean verify(String user, String id) throws Exception {
        if (Parser.isEmail(user)) {
            Credential credential = credentialDAO.findByEmail(user);
            if (credential == null){
                return false;
            } else {
                return credential.getUsername().equals(id);
            }
        } else {
            Credential credential = credentialDAO.findOne(user);
            if (!credentialDAO.exists(user)) {
                return false;
            } else {
                return credential.getUsername().equals(id);
            }
        }
    }

    @Override
    public boolean isAdmin(String user) throws Exception {
        if (Parser.isEmail(user)) {
            Credential credential = credentialDAO.findByEmail(user);
            if (credential == null){
                return false;
            } else {
                return credential.getTypes().contains(Credential.UserType.ADMINISTRATOR);
            }
        } else {
            Credential credential = credentialDAO.findOne(user);
            if (!credentialDAO.exists(user)) {
                return false;
            } else {
                return credential.getTypes().contains(Credential.UserType.ADMINISTRATOR);
            }
        }
    }

    @Override
    public void addType(Credential.UserType type, String id) {
        Credential credential = credentialDAO.findOne(id);
        credential.addType(type);
        credentialDAO.save(credential);
    }

}
