package com.aoide;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import javax.sql.DataSource;

import com.aoide.model.user.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter
{
    @Autowired
    private PersistentTokenRepository tokenRepository;

    @Autowired
    private UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder(); // default strength = 10
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository( DataSource dataSource )
    {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource( dataSource );
        
        return tokenRepository;
    }

    @Override
    protected void configure( AuthenticationManagerBuilder auth ) throws Exception
    {
        auth.userDetailsService( userService )
            .passwordEncoder( passwordEncoder() );
    }

    @Override
    protected void configure( HttpSecurity http ) throws Exception
    {
        http.authorizeRequests()
                .antMatchers( "/", "/index", "/login", "/register" )
                    .permitAll()
                .antMatchers( "/member" )
                    .hasRole( "MEMBER" )
            .and()
                .formLogin()
                    .loginPage( "/login" )
                    .usernameParameter( "email" )
                    .passwordParameter( "password" )
            .and()
                .rememberMe()
                .key( "uniqueAndSecret" )
                .tokenValiditySeconds( 60 * 1 )
                .tokenRepository( tokenRepository )
            .and()
                .logout()
                    .deleteCookies( "JSESSIONID" )
                    .logoutSuccessUrl( "/" );
    }

    @Override
    public void configure( WebSecurity web ) throws Exception
    {
        web.ignoring()
                .antMatchers( "/h2/**" );
    }
}
