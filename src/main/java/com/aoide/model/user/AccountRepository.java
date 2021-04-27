package com.aoide.model.user;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository< Account, Long >
{
   Optional< Account > findByEmail( String email );
}