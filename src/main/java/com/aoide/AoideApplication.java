package com.aoide;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
@EnableJpaAuditing
@SpringBootApplication
public class AoideApplication
{
	@Autowired
    private DataSource dataSource;
    
	public static void main( String[] args ) {
		SpringApplication.run( AoideApplication.class, args );
	}

	@Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder(); // default strength = 10
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository()
    {
        var tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource( dataSource );
        
        return tokenRepository;
    }
}