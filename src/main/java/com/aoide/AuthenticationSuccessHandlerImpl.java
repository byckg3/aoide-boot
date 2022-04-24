package com.aoide;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import com.aoide.user.model.Account;
import com.aoide.user.model.UserService;


@Slf4j
@Component
public class AuthenticationSuccessHandlerImpl extends SavedRequestAwareAuthenticationSuccessHandler
{

    @Autowired
    HttpSession session; //autowiring session

    @Autowired
    UserService userService; //autowire the user repo

    @Override
    public void onAuthenticationSuccess( HttpServletRequest request,
                                         HttpServletResponse response,
                                         Authentication authentication ) throws IOException, ServletException
    {
        String userName;
        Object principal = authentication.getPrincipal();
        
        if ( principal instanceof UserDetails )
        {
            Account account = new Account();

            userName = ( (UserDetails) principal).getUsername();
            account.setName( userName );

            session.setAttribute( "account", account );

        }
        else {
            userName = principal.toString();
        }
        log.info( "userName: {}", userName );
        super.onAuthenticationSuccess( request, response, authentication );
    }
}