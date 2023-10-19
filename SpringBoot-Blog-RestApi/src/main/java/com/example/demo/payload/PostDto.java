package com.example.demo.payload;


import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
		description = "PostDto Model Information"
		)

public class PostDto {
	
	private Long id;
	
	@Schema(
			description = "Blog Post Title"
			)
	@NotEmpty
	@Size(min = 2,message = "post title should have at atleast 2 characters")
	private String title;
	
	
	@Schema(
			description = "Blog Post Description"
			)
	@NotEmpty
	@Size(min = 10,message = "post description should have at atleast 2 characters")

	private String description;
	
	@Schema(
			description = "Blog Post Content"
			)
	@NotEmpty

	private String Content;
	
	private Set<CommentDto> comments; 
	@Schema(
			description = "Blog Post Category"
			)
	
	private Long categoryid;
}
