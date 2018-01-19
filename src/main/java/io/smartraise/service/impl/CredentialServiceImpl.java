package io.smartraise.service.impl;

import io.smartraise.model.accounts.login.LogIn;
import io.smartraise.security.PasswordHashing;
import io.smartraise.dao.CredentialDAO;
import io.smartraise.model.accounts.login.Credential;
import io.smartraise.service.CredentialService;
import io.smartraise.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;

public class CredentialServiceImpl implements CredentialService{

    @Autowired
    private CredentialDAO credentialDAO;

    @Autowired
    private MemberService memberService;

    private void processNewCred(Credential credential) throws Exception {
        if (credential.getTypes().contains(Credential.Type.MEMBER)) {
            memberService.createFromCredential(credential);
        }
    }

    @Override
    public Credential authenticate(LogIn logIn) throws Exception {
        Credential creds;
        if (logIn.getEmail() == "") {
            creds = credentialDAO.findOne(logIn.getUsername());
        } else if (logIn.getUsername() == "") {
            creds = credentialDAO.findByEmail(logIn.getEmail());
        } else {
            throw new Exception("no username or email provided");
        }
        if (creds == null) {
            throw new Exception("No user found");
        }
        if (!PasswordHashing.authenticate(logIn.getPassword(), creds.getSalt(), creds.getHash())){
            throw new Exception("Invalid credentials!");
        }
        return creds;
    }

    @Override
    public Credential create(LogIn logIn) throws Exception {
        if (logIn.getEmail() == "" || logIn.getUsername() == "") {
            throw new Exception("Need email and id!");
        }

        if (credentialDAO.exists(logIn.getEmail())) {
            Credential credential = authenticate(logIn);
            credential.addType(logIn.getType());
            credentialDAO.save(credential);
            return  credential;
        } else {
            byte[] salt = PasswordHashing.generateSalt();
            byte[] hash = PasswordHashing.hash(logIn.getPassword(), salt);
            Credential newCredential =
                    new Credential(logIn.getType(), logIn.getEmail(), hash, salt, logIn.getUsername());

            credentialDAO.save(newCredential);
            processNewCred(newCredential);
            return newCredential;
        }

    }

    @Override
    public boolean verify(String user, String id) throws Exception {
        if (user.contains("@")) {
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

}
