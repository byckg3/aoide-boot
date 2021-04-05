package com.aoide.model.member;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class AccountTests
{
    private Account account;

    @BeforeEach
    void setUp()
    {
        account = new Account();
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
