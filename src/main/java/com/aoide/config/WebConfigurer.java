package com.aoide.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurer implements WebMvcConfigurer
{
    @Override
    public void addViewControllers( ViewControllerRegistry registry )
    {
        registry.addViewController( "/" ).setViewName( "index" );
        registry.addViewController( "/index" );
        registry.addViewController( "/member" );
    }
}
