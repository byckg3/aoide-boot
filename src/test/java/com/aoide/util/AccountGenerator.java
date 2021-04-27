package com.aoide.util;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import static com.aoide.util.DataGenerator.generateBase64RandomString;
import static com.aoide.util.DataGenerator.generateTimestamp;

import java.util.function.Supplier;

import com.aoide.model.user.Account;
import com.aoide.model.user.Role;

public class AccountGenerator implements ParameterResolver, Supplier< Account >
{
    @Override
    public boolean supportsParameter( ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException
    {
        return parameterContext.getParameter().getType() == Account.class;
    }

    @Override
    public Object resolveParameter( ParameterContext parameterContext, ExtensionContext extensionContext ) throws ParameterResolutionException
    {
        return AccountGenerator.generateAccount();
    }

    @Override
    public Account get()
    {
        return AccountGenerator.generateAccount();
    }

    public static Account generateAccount()
    {
        Account account = new Account();
        String email = "test" + generateTimestamp() + "@" + generateBase64RandomString( 5 ) + ".com";
        String rawPassword = generateBase64RandomString( 10 );

        account = new Account();
        account.setEmail( email );
        account.setPassword( rawPassword );
        account.setName( generateBase64RandomString( 5 ) );
        account.setRole( Role.GUEST );

        return account;
    }
}
