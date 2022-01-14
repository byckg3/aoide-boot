package com.aoide.user.model;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import utils.junit.extension.GeneratorExtenion;
import utils.junit.extension.GeneratedBy;

import com.aoide.util.AccountGenerator;

@Tag( "db" )
@ActiveProfiles( "dev" )
@SpringBootTest
@ExtendWith( GeneratorExtenion.class ) // An extension has a scope of influence, which is either at the class level or the method level
public class AccountRepositoryTest
{
    @Autowired
    private AccountRepository accountRepo;

    @GeneratedBy( AccountGenerator.class )
    private Account account;

    private String email;
    private String password;

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
    void whenSaveEntity_thenCommonPropertiesExist( )
    {
        assumeAccuntRepositoryExists();

        Account savedAccount = accountRepo.save( account );

        assertNotNull( savedAccount.getId() );
        assertNotNull( savedAccount.getCreatedDate() );
        assertNotNull( savedAccount.getLastModified() );
        assertNotNull( savedAccount.getVersion() );
    }

    @Test
    void whenFindAccountByEmail_thenFoundAccountIsPresent()
    {
        assumeAccuntRepositoryExists();

        accountRepo.save( account );
        Optional< Account > foundAccount = accountRepo.findByEmail( email );

        assertTrue( foundAccount.isPresent() );
    }

    private void assumeAccuntRepositoryExists()
    {
        assumeTrue( accountRepo != null, "repository is not injected" );
    }
}