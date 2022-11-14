package com.harith.blog;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.harith.blog.entity.Role;
import com.harith.blog.repository.RoleRepository;
import com.harith.blog.util.RoleConstants;

@SpringBootApplication
public class BilogApplication implements CommandLineRunner{

	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepo;
	
	
	public static void main(String[] args) {
		SpringApplication.run(BilogApplication.class, args);
	}
	
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.passwordEncoder.encode("password"));
		Role role = new Role();
		role.setRoleId(RoleConstants.NORMAL_ID);
		role.setRoleName(RoleConstants.NORMAL_USER);
		
		this.roleRepo.save(role);
		
		Role role1 = new Role();
		role1.setRoleId(RoleConstants.ADMIN_ID);
		role1.setRoleName(RoleConstants.ADMIN_USER);
		
		this.roleRepo.save(role1);
		
		
		
		
		
		
	}

}
