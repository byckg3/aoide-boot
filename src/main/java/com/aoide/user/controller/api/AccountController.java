package com.aoide.user.controller.api;


import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aoide.user.model.Account;
import com.aoide.user.model.UserService;

//@RestController
@RepositoryRestController
public class AccountController
{
    @GetMapping( path = "/accounts/ping" )
    @ResponseBody
    public String ping()
    {
        System.out.println( "pong");
        return "pong";
        //return new ResponseEntity<>( "pong", HttpStatus.OK );
    }

    
}
