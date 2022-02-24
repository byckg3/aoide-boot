package com.aoide.user.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;

import com.aoide.user.model.AccountDTO;
import com.aoide.user.model.UserService;
import com.aoide.user.model.AccountDTO.RegisterInput;

@Slf4j
@Controller
@RequestMapping( "/register" )
@SessionAttributes( "account" )
public class RegistrationController
{
    @Autowired
    private UserService userService;

    @GetMapping
    public String showRegistrationForm( Model model, AccountDTO userInputs )
    {
        if ( model.containsAttribute( "account" ) )
        {
            return "redirect:/member";
        }
        log.info( "go to /register.." );
        return "register";
    }

    @PostMapping
    public String processRegistration( @Validated( RegisterInput.class ) AccountDTO userInputs,
                                       Errors errors )
    {
        if ( errors.hasErrors() )
        {
            log.error( "error :\n" + userInputs );
            return "register";
        }
        log.info( userInputs.toString() );

        userService.createMemberAccount( userInputs.toEntity() );

        return "redirect:/index";
    }
}
