package com.aoide.user.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.aoide.user.model.Account;
import com.aoide.user.model.UserService;

@RepositoryRestController
class AccountController
{
    @Autowired
    private UserService userService;

    @GetMapping( "/ping/a" )
    @ResponseBody
    public String ping()
    {
        System.out.println( "aaaaaaa");
        return "Hello, travler";
    }

    
}
