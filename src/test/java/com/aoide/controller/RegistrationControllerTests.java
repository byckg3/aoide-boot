package com.aoide.controller;

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

import com.aoide.model.user.Account;
import com.aoide.model.user.UserService;

@SpringBootTest
@AutoConfigureMockMvc
public class RegistrationControllerTests
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
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
