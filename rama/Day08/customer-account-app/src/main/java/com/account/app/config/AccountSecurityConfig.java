package com.account.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class AccountSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/save/account").hasAnyRole("MANAGER")
                .antMatchers(HttpMethod.GET, "/get/account").hasAnyRole("USER")
                .antMatchers(HttpMethod.PUT, "/update/account").hasAnyRole("USER")
                .antMatchers(HttpMethod.DELETE, "/delete/account").hasAnyRole("MANAGER").and().csrf().disable().headers()
                .frameOptions().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user1").password("{noop}password").roles("USER").and()
                .withUser("manager1").password("{noop}password").roles("MANAGER").and().withUser("user2")
                .password("{noop}password").roles("USER").and().withUser("manager2").password("{noop}password")
                .roles("MANAGER");
    }
}
