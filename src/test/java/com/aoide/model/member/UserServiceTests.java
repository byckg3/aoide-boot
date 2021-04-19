package com.aoide.model.member;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.aoide.model.member.Role;

import static com.aoide.util.DataGenerator.generateTimestamp;
import static com.aoide.util.DataGenerator.generateBase64RandomString;

@SpringBootTest
public class UserServiceTests
{
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UserService userService;

    Account account;
    String email;
    String rawPassword;

    @BeforeEach
    void setUp()
    {
        email = "test" + generateTimestamp() + "@" + generateBase64RandomString( 5 ) + ".com";
        rawPassword = generateBase64RandomString( 10 );

        account = new Account();
        account.setEmail( email );
        account.setPassword( rawPassword );
        account.setName( generateBase64RandomString( 5 ) );
    }
    
    @AfterEach
    void tearDown()
    {
        if ( account.getId() != null ) {
            userService.deleteMemberAccount( account );
        }
    }

    @Test
    void test_beansExist()
    {
        assertNotNull( encoder );
        assertNotNull( userService );
    }

    @Test
    void test_createMemberAccountSuccess()
    {
        userService.createMemberAccount( account );

        assertNotNull( account.getId() );
        assertEquals( Role.MEMBER, account.getRole() );
    }

    @Test
    void test_passwordEncodedSuccess()
    {
        userService.createMemberAccount( account );
        String encodedPassword = account.getPassword();

        assertTrue( encoder.matches( rawPassword, encodedPassword ) );
    }
}