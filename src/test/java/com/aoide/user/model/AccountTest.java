package com.aoide.user.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.aoide.util.AccountGenerator;

public class AccountTest
{
    private Account account;

    @BeforeEach
    void setUp()
    {
        account = AccountGenerator.generate();
        //System.out.println( account );
    }

    @Test
    void test_setId()
    {
        Long id = 123L;

        account.setId( id );

        assertEquals( id, account.getId() );
    }

    @Test
    void test_enabled()
    {
        boolean enabled = false;

        account.setEnabled( enabled );

        assertEquals( enabled, account.isEnabled() );
    }
}
