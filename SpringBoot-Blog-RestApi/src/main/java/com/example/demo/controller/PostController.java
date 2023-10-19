package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.payload.PostDto;
import com.example.demo.payload.PostDtoV2;
import com.example.demo.payload.PostResponse;
import com.example.demo.service.PostService;
import com.example.demo.utils.AppConstants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping
@Tag(name = "CRUD REST API's FOR RESOURCE")
public class PostController {

	private PostService postService;

	public PostController(PostService postService) {
		super();
		this.postService = postService;
	}

	@Operation(summary = "Create Post Rest Api", description = "Create Post Rest Api is used to save post into database"

	)
	@ApiResponse(responseCode = "201", description = "Http Status 201 Created"

	)

	@SecurityRequirement(name = "  Authentication")

	@PreAuthorize("hasRole('ADMIN')") // to allow admin user only to createpost
	@PostMapping("/api/v1/posts")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto dto) {
		return new ResponseEntity<>(postService.createpost(dto), HttpStatus.CREATED);

	}

	@Operation(summary = "Get Post by id Rest Api", description = "Get Post by id  Rest Api is used get from database"

	)
	@ApiResponse(responseCode = "200 ", description = "Http Status 200 Success"

	)

	@GetMapping("/api/v1/posts")
	public PostResponse getpost(
			@RequestParam(value = AppConstants.DEFAULE_PAGE_NUNMBER, defaultValue = "0", required = false) int pageNo,
			@RequestParam(value = AppConstants.DEFAULE_PAGE_SIZE, defaultValue = "10", required = false) int PageSize,
			@RequestParam(value = AppConstants.DEFAULE_SORTBY, defaultValue = "id", required = false) String Sortby,
			@RequestParam(value = AppConstants.DEFAULE_SORT_DIRECTION, defaultValue = "asc", required = false) String SortDir) {

		return postService.Getposts(pageNo, PageSize, Sortby, SortDir);

	}

	@Operation(summary = "Get Post by id Rest Api", description = "Get Post by id  Rest Api is used get from database"

	)
	@ApiResponse(responseCode = "200 ", description = "Http Status 200 Success"

	)

	// this is versioning rest api with URI path
	@GetMapping("/api/v1/posts/{id}")

	// this is versioning with Query param
//	@GetMapping(value="/api/posts/{id}" ,params="version=1")
	// this is versioning with customs headers
//	@GetMapping(value = "/api/posts/{id}", headers = "X-API-VERSION=1")
	// this is versioning with Content Negotiation
//	@GetMapping(value = "/api/posts/{id}", produces = "application/vnd.pradeep.v1+json")

	

	public ResponseEntity<PostDto> getPostbyidV1(@PathVariable(name = "id") Long id) {
		return new ResponseEntity<PostDto>((PostDto) postService.getbyid(id), HttpStatus.OK);

	}

	// this is versioning rest api with URI path
//	@GetMapping("/api/v2/posts/{id}")
	// this is versioning with Query param
//	@GetMapping(value="/api/posts/{id}" ,params="version=2")
	// this is versioning with customs headers
	
//	@GetMapping(value = "/api/posts/{id}", headers = "X-API-VERSION=2")
	// this is versioning with Content Negotiation
//		@GetMapping(value = "/api/posts/{id}", produces = "application/vnd.pradeep.v2+json")

	
//	
//	public ResponseEntity<PostDtoV2> getPostbyidV2(@PathVariable(name = "id") Long id) {
//		PostDto postDto = postService.getbyid(id);
//		PostDtoV2 postDtoV2 = new PostDtoV2();
//		postDtoV2.setId(postDto.getId());
//		postDtoV2.setTitle(postDto.getTitle());
//		postDtoV2.setDescription(postDto.getDescription());
//		postDtoV2.setContent(postDto.getContent());
//		List<String> tags = new ArrayList<>();
//		tags.add("java");
//		tags.add("Spring");
//		tags.add("Aws");
//		postDtoV2.setTags(tags);
//
//		return ResponseEntity.ok(postDtoV2);
//
//	}

	@Operation(summary = "update Post by id Rest Api", description = "update Post by id  Rest Api is used update to database"

	)
	@ApiResponse(responseCode = "200 ", description = "Http Status 200 Success"

	)

	@SecurityRequirement(name = "Bear Authentication")

	@PreAuthorize("hasRole('ADMIN')") // to allow admin user only to updatepost
	@PutMapping("/api/v1/posts/{id}")
	public ResponseEntity<PostDto> updatepost(@Valid @RequestBody PostDto dto, @PathVariable(name = "id") Long id) {
		PostDto updatepost = postService.updatepost(dto, id);
		return new ResponseEntity<PostDto>(updatepost, HttpStatus.OK);

	}

	@Operation(summary = "Delete Post by id Rest Api", description = "Delete Post by id  Rest Api is delete  from database"

	)
	@ApiResponse(responseCode = "200 ", description = "Http Status 200 Success"

	)

	@SecurityRequirement(name = "Bear Authentication")
	@PreAuthorize("hasRole('ADMIN')") // to allow admin user only to deletepost
	@DeleteMapping("/api/v1/posts/{id}")
	public ResponseEntity<String> deletepost(@PathVariable(name = "id") Long id) {
		postService.deletepost(id);
		return new ResponseEntity<>("delete succes", HttpStatus.OK);

	}
}
