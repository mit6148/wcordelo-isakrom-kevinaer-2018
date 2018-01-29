package io.smartraise.config;

import io.smartraise.security.CustomAuthProvider;
import io.smartraise.security.AuthenticationListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthProvider authProvider;

    @Autowired
    private AuthenticationListener authenticationListener;

//    @Autowired
//    private CustomAuthenticationFailureHandler failureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
//                .and()
            .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .antMatchers("/signup").permitAll()
                .antMatchers("/member/*").authenticated()
                .antMatchers("/member/*/edit").authenticated()
                .anyRequest().permitAll()
                .and()
            .formLogin()
                .loginPage("/login")
                .successHandler(authenticationListener)
                .failureHandler(authenticationListener)
                .permitAll()
                .and()
            .logout()
                .invalidateHttpSession(true)
                .logoutUrl("/")
                .permitAll();
    }

    @Autowired
    protected void configureGlobal (AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }

}
