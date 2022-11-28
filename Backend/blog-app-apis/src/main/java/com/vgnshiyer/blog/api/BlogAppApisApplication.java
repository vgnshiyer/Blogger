package com.vgnshiyer.blog.api;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.vgnshiyer.blog.api.config.AppConstants;
import com.vgnshiyer.blog.api.entities.Role;
import com.vgnshiyer.blog.api.repositories.RoleRepo;

import java.util.List;

@SpringBootApplication
public class BlogAppApisApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(BlogAppApisApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		
		try {
			Role role1 = new Role();
			role1.setId(AppConstants.ADMIN_USER);  // admin
			role1.setName("ROLE_ADMIN");
			
			Role role2 = new Role();
			role2.setId(AppConstants.GENERAL_USER);  // admin
			role2.setName("ROLE_GENERAL");
			
			List<Role> roles = List.of(role1, role2);
			
			List<Role> res = this.roleRepo.saveAll(roles);
			
			res.forEach(r->{
				System.out.println(r.getName());
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
