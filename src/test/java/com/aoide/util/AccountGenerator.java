package com.aoide.util;

import java.util.function.Supplier;

import org.apache.commons.lang3.RandomStringUtils;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import static utils.DataGenerator.generateBase64RandomString;
import static utils.DataGenerator.generateTimestamp;

import com.aoide.user.model.Account;
import com.aoide.user.model.Role;

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
        return AccountGenerator.generate();
    }

    @Override
    public Account get()
    {
        return AccountGenerator.generate();
    }

    public static Account generate()
    {
        String email = "test" + generateTimestamp() + "@" + generateBase64RandomString( 5 ) + ".com";
        String name = RandomStringUtils.randomAlphabetic( 10 );
        String rawPassword = RandomStringUtils.randomAlphanumeric( 10 );

        var account = new Account();
        account.setEmail( email );
        account.setPassword( rawPassword );
        account.setName( name );
        account.setRole( Role.GUEST );

        return account;
    }
}
