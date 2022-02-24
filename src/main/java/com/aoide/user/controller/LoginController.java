package com.aoide.user.controller;

import org.springframework.ui.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;

import com.aoide.user.model.AccountDTO;
import com.aoide.user.model.UserService;
import com.aoide.user.model.AccountDTO.LoginInput;

@Slf4j
@Controller
@RequestMapping( "/login" )
@SessionAttributes( "account" )
public class LoginController
{
    @Autowired
    private UserService userService;
    
    @GetMapping
    public String showLoginForm( Model model, AccountDTO inputs )
    {
        if ( model.containsAttribute( "account" ) )
        {
            log.info( "go to /member" );
            return "redirect:/member";
        }
        log.info( "display /login" );
        return "login";
    }

    @PostMapping
    public String login( Model model, @Validated( LoginInput.class ) AccountDTO inputs, Errors errors )
    {
        if ( !errors.hasErrors() )
        {
            var foundAccount = userService.findAccountBy( inputs.getEmail() );
            if ( foundAccount.isPresent() )
            {
                //model.addFlashAttribute( "account", foundAccount.get() );
                model.addAttribute( "account", foundAccount.get() );
                log.info( "\nemail: {} password: {}", inputs.getEmail(), inputs.getPassword() );

                return "redirect:/member";
            }
        }
        else
        {
            log.error( "error occurs");
        }
        return "login";
    }
}
