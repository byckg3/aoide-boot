package com.aoide.controller;

import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

import com.aoide.model.member.Account;

@Slf4j
@Controller
@RequestMapping( "/login" )
public class LoginController
{
    @GetMapping
    public String showLoginForm( LoginForm loginForm )
    {
        return "login";
    }

    @PostMapping
    public String login( Model model, @Valid LoginForm loginForm, Errors errors )
    {
        if ( errors.hasErrors() )
        {
            return "login";
        }
        log.info( "email: {} password: {}", loginForm.getEmail(), loginForm.getPassword() );

        //model.addAttribute( "name", member.getName() );
        return "welcome";
    }
}
