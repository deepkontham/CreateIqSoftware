package com.example.demo.payload;

import java.util.List;
import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostDtoV2 {
	private Long id;

	@NotEmpty
	@Size(min = 2, message = "post title should have at atleast 2 characters")
	private String title;

	@NotEmpty
	@Size(min = 10, message = "post description should have at atleast 2 characters")

	private String description;

	@NotEmpty

	private String Content;

	private Set<CommentDto> comments;

private List<String> tags;
}
