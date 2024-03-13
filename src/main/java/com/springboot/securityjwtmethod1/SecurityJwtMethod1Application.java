package com.springboot.securityjwtmethod1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SecurityJwtMethod1Application implements CommandLineRunner {

	private static final Logger LOG= LoggerFactory.getLogger(SecurityJwtMethod1Application.class);


	public static void main(String[] args) {
		SpringApplication.run(SecurityJwtMethod1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String password="123";
		LOG.info("Username: user1, Password: "+password);
		LOG.info("Username: user2, Password: "+password);
		LOG.info("Username: user3, Password: "+password);
	}

}
