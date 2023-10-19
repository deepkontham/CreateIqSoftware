package com.example.demo.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentDto {
	
	
	private long id;
	@NotEmpty
	@Size(min =2,message = "Name should have more than two characters")
	private String name;
	@NotEmpty(message = "Email should not be null or empty")
	@Email
	private String email;
	@NotEmpty
	@Size(min=10,message = "body  should have more than ten characters")
	private String body;
	
}
