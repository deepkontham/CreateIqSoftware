package com.example.demo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.entity.Role;
import com.example.demo.repository.RoleRepository;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.media.Content;

@SpringBootApplication
@OpenAPIDefinition(
		info=@Info(	
				title="spring boot blog rest api",
				description="spring boot blog rest api documentation ",
				version="v1.0",
				contact=@Contact(
						
						name="pradeep",
						email="javaguides.net@gmail.com",
						url="https://www.javaguides.net"
						),
						
					license=@License(
							name="Apache 2.0",
							url="http://www.javaguides.net/license"
							
							)
						),
				externalDocs = @ExternalDocumentation(
						description = "spring boot blog rest api documentation",
						url="https://github.com/RameshMF/springboot-blog-rest-api"
						)
				
				)
		
		
public class SpringBootBlogRestApiApplication implements CommandLineRunner {
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
		
	}
	@Autowired
	private RoleRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBlogRestApiApplication.class, args);
	}

	//to insert metadate in tables
	@Override
	public void run(String... args) throws Exception {
		Role admin=new Role();
		admin.setName("ROLE_ADMIN");
		repository.save(admin);
		
		Role user=new Role();
		admin.setName("ROLE_USER");
		repository.save(user);
		
		
		
	}

}
