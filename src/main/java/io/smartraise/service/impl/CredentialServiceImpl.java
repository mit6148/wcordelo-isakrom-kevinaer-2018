package io.smartraise.service.impl;

import io.smartraise.util.Parser;
import io.smartraise.model.login.SignUp;
import io.smartraise.security.PasswordHashing;
import io.smartraise.dao.CredentialDAO;
import io.smartraise.model.login.Credential;
import io.smartraise.service.deprecated.AdminService;
import io.smartraise.service.CredentialService;
import io.smartraise.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;

public class CredentialServiceImpl implements CredentialService{

    @Autowired
    private CredentialDAO credentialDAO;

    @Autowired
    private MemberService memberService;

    @Autowired
    private AdminService adminService;

    @Override
    public boolean exists(String username) {
        return credentialDAO.exists(username);
    }

    private void newForm(SignUp signUp) throws Exception {
        memberService.create(signUp.getAccount());
    }

    @Override
    public Credential authenticate(SignUp signUp) throws Exception {
        Credential creds;
        if (signUp.getEmail().isEmpty()) {
            creds = credentialDAO.findOne(signUp.getUsername());
        } else if (signUp.getUsername().isEmpty()) {
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
        if (signUp.getUsername().isEmpty()
                && signUp.getEmail().isEmpty()
                && signUp.getPassword().isEmpty()){
            throw new Exception("Needs a valid user name and email");
        }
        if (credentialDAO.exists(signUp.getUsername())
                && credentialDAO.findOne(signUp.getUsername()).getTypes().contains(signUp.getType())) {
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
            signUp.getAccount().setUsername(signUp.getUsername());
            signUp.getAccount().getContactInformation().setUsername(signUp.getUsername());
            signUp.getAccount().getContactInformation().setEmail(signUp.getEmail());
            newForm(signUp);
            credentialDAO.save(newCredential);
            return newCredential;
        }
    }

    @Override
    public boolean verify(String user, String id) {
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

    @Override
    public void removeType(Credential.UserType type, String id) {
        Credential credential = credentialDAO.findOne(id);
        credential.removeType(type);
        credentialDAO.save(credential);
    }

    @Override
    public boolean containsType(Credential.UserType type, String id) {
        Credential credential = credentialDAO.findOne(id);
        return credential.getTypes().contains(type);
    }

    @Override
    public void delete(String id) {
        credentialDAO.delete(id);
    }
}
