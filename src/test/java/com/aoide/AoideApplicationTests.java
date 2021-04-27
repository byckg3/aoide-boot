package com.aoide;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import com.aoide.model.user.UserService;

@SpringBootTest
class AoideApplicationTests
{
	@Autowired
    private UserService userService;
	
	@Test
	void contextLoads()
	{
		assertThat( userService ).isNotNull();
	}
}