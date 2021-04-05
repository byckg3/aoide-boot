package com.aoide;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@Configuration
@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure( AuthenticationManagerBuilder auth ) throws Exception
    {
        auth.inMemoryAuthentication()
                .withUser( "Alex" )
                .password( "1234567890" )
                .authorities( "ROLE_USER" );
    }
}
