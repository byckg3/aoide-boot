package com.aoide.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
public class AoideConfig
{
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder(); // default strength = 10
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository( DataSource dataSource )
    {
        var tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource( dataSource );
        
        return tokenRepository;
    }
}
