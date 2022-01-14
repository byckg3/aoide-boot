package com.aoide.user.model;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource( path = "accounts", collectionResourceRel = "accounts" ) // optional and used to customize the REST endpoint.
public interface AccountRepository extends PagingAndSortingRepository< Account, Long >
{
    @RestResource( path = "byEmail", rel = "search_email" )
    Optional< Account > findByEmail( @Param( "email" ) String email );

    @Override
    @RestResource( exported = false )
    < S extends Account > S save( S entity );

    @Override
    @RestResource( exported = false )
    < S extends Account > Iterable< S > saveAll( Iterable< S > entities );
}