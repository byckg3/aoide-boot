package com.aoide.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

import com.aoide.controller.UserInputs.RegisterInput;
import com.aoide.model.user.Account;
import com.aoide.model.user.UserService;

@Slf4j
@Controller
@RequestMapping( "/register" )
public class RegistrationController
{
    @Autowired
    private UserService userService;

    @GetMapping
    public String showRegistrationForm( UserInputs userInputs )
    {
        return "register";
    }

    @PostMapping
    public String register( Model model, @Validated( RegisterInput.class ) UserInputs userInputs, Errors errors )
    {
        if ( errors.hasErrors() )
        {
            log.error( "error");
            return "register";
        }
        log.info( "email: {} password: {} name: {}", userInputs.getEmail(), userInputs.getPassword(), userInputs.getName() );

        Account memberAccount = userService.createMemberAccount( userInputs.toAccount() );
        model.addAttribute( "account", memberAccount );

        return "member";
    }
}
