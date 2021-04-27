package com.aoide.util;

import java.security.SecureRandom;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Base64;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class DataGenerator
{
    public static int MIN_INT = 1;
    public static int MAX_INT = 100;
    public static int DATA_SIZE = 10;

    public static IntStream generateRandomIntStream()
    {
        return DoubleStream.generate( Math::random )
                           .mapToInt( value -> ( int ) ( value * ( MAX_INT - MIN_INT + 1 ) + MIN_INT ) )
                           .distinct()
                           .limit( DATA_SIZE );
    }
    
    public static String generateTimestamp()
    {
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddHHmmss" );
        return sdf.format( new Date() );
    }

    public static String generateBase64RandomString( int length )
    {
        if ( length < 0 ) {
            throw new RuntimeException( "length must be > 0" );
        }

        String randomString = "";
        SecureRandom random = new SecureRandom();
        Base64.Encoder encoder = Base64.getEncoder();

        int arrayLength = 4 * ( ( int ) Math.ceil( length / 4.0 ) ) * 6 / 8;
        byte[] randomBytes = new byte[ arrayLength ];
        random.nextBytes( randomBytes );

        randomString = encoder.encodeToString( randomBytes );

        return randomString.substring( 0, length );
    }
}
