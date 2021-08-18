package com.aoide.model.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willDoNothing;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.aoide.util.AccountGenerator;

@ExtendWith( MockitoExtension.class )
public class UserServiceTests
{
    @Spy
    PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    UserService userService;

    Account account;
    String email;
    String rawPassword;

    @BeforeEach
    void setUp()
    {
        this.account = AccountGenerator.generateAccount();

        email = this.account.getEmail();
        rawPassword = this.account.getPassword();
    }

    @Test
    void beansShouldExist()
    {
        assertNotNull( encoder );
        assertNotNull( accountRepository );
        assertNotNull( userService );
    }

    @Test
    void createMemberAccount_saveAccount_success()
    {
        when( accountRepository.save( any( Account.class ) ) ).then( returnsFirstArg() );

        userService.createMemberAccount( account );
      
        assertEquals( Role.MEMBER, account.getRole() );
        assertTrue( account.isEnabled() );
        verify( accountRepository, times( 1 ) ).save( account );
    }

    @Test
    void createMemberAccount_passwordEncoded_success()
    {
        given( accountRepository.save( any( Account.class ) ) ).will( returnsFirstArg() );

        userService.createMemberAccount( account );
        var encodedPassword = account.getPassword();

        then( encoder ).should( times( 1 ) ).encode( rawPassword );
        assertTrue( encoder.matches( rawPassword, encodedPassword ) );
    }

    @Test
    void findMemberAccountBy_findByEmailIsCalled()
    {
        given( accountRepository.findByEmail( email ) ).willReturn( Optional.of( account ) );

        userService.findMemberAccountBy( email );

        then( accountRepository ).should( times( 1 ) ).findByEmail( email );
    }

    @Test
    void deleteMemberAccount_deleteIsCalled()
    {
        account.setRole( Role.MEMBER);
        willDoNothing().given( accountRepository ).delete( account );

        userService.deleteMemberAccount( account );

        then( accountRepository ).should( times( 1 ) ).delete( account );
    }
}