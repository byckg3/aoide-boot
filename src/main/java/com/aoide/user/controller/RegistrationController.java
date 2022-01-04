package com.aoide.user.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;

import com.aoide.user.model.Account;
import com.aoide.user.model.UserInputs;
import com.aoide.user.model.UserService;
import com.aoide.user.model.UserInputs.RegisterInput;

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
    public String processRegistration( RedirectAttributes model,
                                       @Validated( RegisterInput.class ) UserInputs userInputs,
                                       Errors errors )
    {
        if ( errors.hasErrors() )
        {
            log.error( "error :\n" + userInputs );
            return "register";
        }
        log.info( userInputs.toString() );

        Account memberAccount = userService.createMemberAccount( userInputs.toAccount() );
        model.addFlashAttribute( "account", memberAccount );

        return "redirect:/index";
    }
}
