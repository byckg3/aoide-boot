package com.aoide.user.controller;

import org.springframework.ui.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

import com.aoide.user.model.Account;
import com.aoide.user.model.UserInputs;
import com.aoide.user.model.UserService;
import com.aoide.user.model.UserInputs.LoginInput;

@Slf4j
@Controller
@RequestMapping( "/login" )
public class LoginController
{
    @Autowired
    private UserService userService;
    
    @GetMapping
    public String showLoginForm( UserInputs inputs )
    {
        log.info( "showLoginForm" );
        return "login";
    }

    @PostMapping
    public String login( Model model, @Validated( LoginInput.class ) UserInputs inputs, Errors errors )
    {
        if ( errors.hasErrors() )
        {
            log.error( "error");
            return "login";
        }
        log.info( "email: {} password: {}", inputs.getEmail(), inputs.getPassword() );

        // Account memberAccount = userService.findMemberAccountBy( inputs.getEmail() ).get();
        // model.addAttribute( "account", memberAccount );

        return "register";
    }
}
