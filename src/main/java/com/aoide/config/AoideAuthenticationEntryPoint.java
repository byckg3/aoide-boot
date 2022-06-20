package com.aoide.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class AoideAuthenticationEntryPoint implements AuthenticationEntryPoint
{

    @Override
    public void commence( HttpServletRequest request, HttpServletResponse response,
                                                      AuthenticationException authException ) throws IOException, ServletException
    {   
        response.sendError( HttpStatus.UNAUTHORIZED.value(), "Authentication exception occurs" );
    }
}