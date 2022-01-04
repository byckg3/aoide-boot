package com.aoide.user.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.aoide.user.model.Account;
import com.aoide.user.model.UserService;

// @WebMvcTest: to avoid creating the whole context and test only MVC controllers
@SpringBootTest
@AutoConfigureMockMvc // to inject a MockMvc instance and send HTTP requests
public class RegistrationControllerTests
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean // create and inject a mock
	private UserService userService;

    Account account;

    @BeforeEach
    void setUp()
    {
        account = new Account();
    }

    @Test
    void test_showRegistrationForm() throws Exception
    {
        mockMvc.perform( get( "/register" ) )
               .andExpect( status().isOk() )
               .andExpect( view().name( "register" ) );
        
    }

    @Test
    void test_register() throws Exception
    {
        when( userService.createMemberAccount( account ) ).thenReturn( account );
        
        // mockMvc.perform( get( "/register" ) )
        //        .andExpect( status().isOk() )
        //        .andExpect( view().name( "register" ) );
        
    }
}
