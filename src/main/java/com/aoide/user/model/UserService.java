package com.aoide.user.model;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService implements UserDetailsService
{
    private AccountRepository accountRepo;
    private PasswordEncoder encoder;

    public UserService( AccountRepository accountRepo, PasswordEncoder encoder )
    {
        this.accountRepo = accountRepo;
        this.encoder = encoder;
    }

    public Account createMemberAccount( Account userAccount )
    {
        return createAccount( userAccount, Role.MEMBER );
    }
    
    public Optional< Account > findMemberAccountBy( String email )
    {
        Optional< Account > foundAccount = accountRepo.findByEmail( email );
        return foundAccount;
    }

    public void deleteMemberAccount( Account userAccount )
    {
        if ( userAccount.getRole() == Role.MEMBER ) {
            accountRepo.delete( userAccount );
        }
    }

    // email as username
    @Override
    public UserDetails loadUserByUsername( String email ) throws UsernameNotFoundException
    {
        Optional< Account > foundAccount = findMemberAccountBy( email );
        if ( foundAccount.isPresent() )
        {
            Account userAccount = foundAccount.get();
            log.info( "load user {}", userAccount.toString() );
            UserDetails details = User.builder()
                                      .username( email )
                                      .password( userAccount.getPassword() )
                                      .roles( userAccount.getRole().name() )
                                      .accountLocked( !userAccount.isEnabled() )
                                      .accountExpired( !userAccount.isEnabled() )
                                      .credentialsExpired( !userAccount.isEnabled() )
                                      .disabled( !userAccount.isEnabled() )
                                      .build();
            return details;
        }
        throw new UsernameNotFoundException( email + " not found" );
    }

    private Account createAccount( Account userAccount, Role role )
    {
        String encodedPassword = encoder.encode( userAccount.getPassword() );
        userAccount.setPassword( encodedPassword );

        userAccount.setRole( role );
        userAccount.setEnabled( true );
        
        return accountRepo.save( userAccount );
    }
}
