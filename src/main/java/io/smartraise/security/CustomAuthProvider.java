package io.smartraise.security;

import io.smartraise.helper.Parser;
import io.smartraise.model.login.SignUp;
import io.smartraise.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.ArrayList;

@ComponentScan("io.smartraise.smartraise")
public class CustomAuthProvider implements AuthenticationProvider {

    @Autowired
    private CredentialService credentialService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String user = authentication.getName();
        String password = authentication.getCredentials().toString();

        try {
            if (Parser.isEmail(user)){
                credentialService.authenticate(new SignUp(user, "",password));
            } else {
                credentialService.authenticate(new SignUp("", user, password));
            }
            return new UsernamePasswordAuthenticationToken(
                    user, password, new ArrayList<>());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
