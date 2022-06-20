package com.aoide.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.web.csrf.CsrfToken;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CsrfTokenLogger implements Filter
{
    @Override
    public void doFilter( ServletRequest request, ServletResponse response, FilterChain filterChain )
                                                                            throws IOException, ServletException
    {
        CsrfToken token = ( CsrfToken ) request.getAttribute( "_csrf" );

        log.info( "CSRF token: " + token.getToken() );
        // log.info( "CSRF header name: " + token.getHeaderName() );
        // log.info( "CSRF parameter name: " + token.getParameterName() );

        filterChain.doFilter( request, response );
    }
    
}
