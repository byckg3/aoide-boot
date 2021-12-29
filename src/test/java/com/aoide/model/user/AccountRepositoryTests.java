package com.aoide.model.user;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import utils.junit.extension.GeneratorExtenion;
import utils.junit.extension.GeneratedBy;

import com.aoide.util.AccountGenerator;

@ActiveProfiles( "dev" )
@SpringBootTest
@ExtendWith( GeneratorExtenion.class) // An extension has a scope of influence, which is either at the class level or the method level
public class AccountRepositoryTests
{
    @Autowired
    AccountRepository accountRepo;

    @GeneratedBy( AccountGenerator.class )
    private Account account;

    String email;
    String password;

    @BeforeEach
    void setUp()
    {
        email = this.account.getEmail();
        password = this.account.getPassword();
    }

    @AfterEach
    void tearDown()
    {
        if ( this.account.getId() != null ) {
            accountRepo.delete( account );
        }
    }

    @Test
    void repoExists()
    {
        assertNotNull( accountRepo );
    }

    @Test
    void save_savedAccountHasAnId( )
    {
        Account savedAccount = accountRepo.save( account );

        assertNotNull( savedAccount.getId() );
    }

    @Test
    void findByEmail_foundAccountIsPresent()
    {
        accountRepo.save( account );

        Optional< Account > foundAccount = accountRepo.findByEmail( email );

        assertTrue( foundAccount.isPresent() );
    }
}
