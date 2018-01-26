package io.smartraise.security;

import io.smartraise.util.Parser;
import io.smartraise.model.login.Credential;
import io.smartraise.model.login.SignUp;
import io.smartraise.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@ComponentScan("io.smartraise.smartraise")
public class CustomAuthProvider implements AuthenticationProvider {

    @Autowired
    private CredentialService credentialService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String user = authentication.getName();
        String password = authentication.getCredentials().toString();

        try {
            Credential credential;
            if (Parser.isEmail(user)){
                credential = credentialService.authenticate(new SignUp(user, "",password));
            } else {
                credential = credentialService.authenticate(new SignUp("", user, password));
            }
            List<GrantedAuthority> authorities = new ArrayList<>();
            if (credential.getTypes().contains(Credential.UserType.ADMINISTRATOR)) {
                authorities.add(new SimpleGrantedAuthority("ADMIN"));
            }
            return new UsernamePasswordAuthenticationToken(
                    credential.getUsername(), password, authorities);
        } catch (Exception e) {
            throw (AuthenticationException) e;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
