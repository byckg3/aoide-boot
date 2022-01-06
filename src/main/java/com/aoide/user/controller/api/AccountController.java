package com.aoide.user.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.aoide.user.model.Account;
import com.aoide.user.model.UserService;

@RestController
@RequestMapping( path = "/account", produces = "application/json" )
class AccountController
{
    @Autowired
    private UserService userService;

    @GetMapping( "/ping" )
    public String ping()
    {
        return "Hello, travler";
    }

    @GetMapping( "/{id}" )
    public Account findAccount( @PathVariable( "id" ) Long id )
    {
        try
        {
            var foundAccount = userService.findAccountById( id );

            return foundAccount.get();
        }
        catch( Exception e )
        {
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, "account not found", e );
        }
    }
}
