package io.gatarrays.useservice;

import io.gatarrays.useservice.entites.Role;
import io.gatarrays.useservice.entites.User;
import io.gatarrays.useservice.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class UseserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UseserviceApplication.class, args);
	}
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
   @Bean
	CommandLineRunner run(UserService userService){
		return args->{
			userService.saveRole(new Role(null,"ROLE_USER"));
			userService.saveRole(new Role(null,"ROLE_MANAGER"));
			userService.saveRole(new Role(null,"ROLE_ADMIN"));
			userService.saveRole(new Role(null,"ROLE_SUPER_ADMIN"));

			userService.saveUser(new User(null,"Marcos Andrade","marcos","12345", new ArrayList<>()));
			userService.saveUser(new User(null,"lucas Andrade","lucas","12345", new ArrayList<>()));
			userService.saveUser(new User(null,"joelma Andrade","joelma","12345", new ArrayList<>()));
			userService.saveUser(new User(null,"gisele Andrade","gisele","12345", new ArrayList<>()));

            userService.addRoleToUser("marcos","ROLE_ADMIN");
            userService.addRoleToUser("marcos","ROLE_MANAGER");
            userService.addRoleToUser("marcos","ROLE_SUPER_ADMIN");
            userService.addRoleToUser("lucas","ROLE_USER");
            userService.addRoleToUser("joelma","ROLE_ADMIN");
            userService.addRoleToUser("joelma","ROLE_USER");
            userService.addRoleToUser("gisele","ROLE_MANAGER");

		};
  }
}
