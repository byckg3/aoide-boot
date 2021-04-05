package com.aoide.model.member;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.aoide.util.DataGenerator.generateBase64RandomString;

@SpringBootTest
public class AccountRepositoryTests
{
    @Autowired
    AccountRepository userRepo;

    Account account;
    String email = generateBase64RandomString( 7 ) + "@email.com";
    String password = generateBase64RandomString( 10 );

    @BeforeEach
    void setUp()
    {
        assumeTrue( userRepo != null, "UserRepository doesn't exist" );

        account = new Account();
        account.setName( generateBase64RandomString( 5 ) );
        account.setPassword( password );
		account.setEmail( email );
        account.setRole( "ROLE_MEMBER" );
    }

    @Test
    void test_repoExists()
    {
        assertNotNull( userRepo );
    }

    @Test
    void test_saveSuccessfully()
    {
        userRepo.save( account );

        assertNotNull( account.getId() );
    }

    @Test
    void test_findByEmail()
    {
        userRepo.save( account );

        Optional< Account > foundAccount = userRepo.findByEmail( email );

        assertTrue( foundAccount.isPresent() );
    }

}
