package com.ross.myrewards;

import com.ross.myrewards.model.User;
import com.ross.myrewards.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyrewardsApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MyrewardsApplication.class, args);
	}

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		// Run this on the first launch
//		userRepository.save(new User("Alex Willians", "walex@yahoo.com"));
	}
}
