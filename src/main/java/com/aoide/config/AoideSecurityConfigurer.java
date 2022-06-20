package com.aoide.config;

import com.aoide.filter.CsrfTokenLogger;
import com.aoide.user.model.Role;
import com.aoide.user.model.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;

@Configuration
@EnableWebSecurity
public class AoideSecurityConfigurer extends WebSecurityConfigurerAdapter
{
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PersistentTokenRepository persistentTokenRepository;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Override
    protected void configure( AuthenticationManagerBuilder auth ) throws Exception
    {
        auth.userDetailsService( userService )
            .passwordEncoder( passwordEncoder );
    }

    @Override
    protected void configure( HttpSecurity http ) throws Exception
    {
        http.authorizeRequests()
                .mvcMatchers( "/", "/index", "/login", "/register" )
                    .permitAll()
                .mvcMatchers( "/member" )
                    .hasRole( Role.MEMBER.name() )
                .mvcMatchers( "/actuator/**" )
                    .hasRole( Role.ADMIN.name() )
                .mvcMatchers( "/api/**" )
                    .hasRole( Role.MEMBER.name() )
            .and()
                .httpBasic()
                    .realmName( "API" )
                    .authenticationEntryPoint( new AoideAuthenticationEntryPoint() )
            .and()
                .formLogin()
                    .loginPage( "/login" )
                    .usernameParameter( "email" )
                    .passwordParameter( "password" )
                    .defaultSuccessUrl( "/member", true )
                    .successHandler( authenticationSuccessHandler )
            .and()
                .rememberMe()
                .key( "uniqueAndSecret" )
                .tokenValiditySeconds( 60 * 1 )
                .tokenRepository( persistentTokenRepository )
            .and()
                .logout()
                    .deleteCookies( "JSESSIONID" )
                    .logoutSuccessUrl( "/" )
            .and()
                .addFilterAfter( new CsrfTokenLogger(), CsrfFilter.class );
    }

    @Override
    public void configure( WebSecurity web ) throws Exception
    {
        web.ignoring()
                .antMatchers( "/h2/**" );
    }
}
