package com.example.demo.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Category;
import com.example.demo.entity.Post;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.payload.PostDto;
import com.example.demo.payload.PostResponse;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	private PostRepository postRepository;

	private ModelMapper mapper;
	
	private CategoryRepository categoryRepository;
	



	public PostServiceImpl(PostRepository postRepository, ModelMapper mapper, CategoryRepository categoryRepository) {
		super();
		this.postRepository = postRepository;
		this.mapper = mapper;
		this.categoryRepository = categoryRepository;
	}


	@Override
	public PostDto createpost(PostDto dto) {
		
		//for category attached
		Category category = categoryRepository.findById(dto.getCategoryid()).orElseThrow(()->new ResourceNotFoundException("category","id",dto.getCategoryid()));
		// convert dto to entity
		Post p = maptoEntity(dto);
		p.setCategory(category);

		Post newPost = postRepository.save(p);

		// convert dto to entity
		PostDto dt = maptodto(newPost);
		return dt;
	}


	// convert to entity to dto
	
	private PostDto maptodto(Post post) {
		  PostDto postDto = mapper.map(post, PostDto.class);
//		pos1.setId(post.getId());
//		pos1.setTitle(post.getTitle());
//		pos1.setDescription(post.getDescription());
//		pos1.setContent(post.getTitle());
		return postDto;

	}

	private Post maptoEntity(PostDto dto) {
		Post post = mapper.map(dto,Post.class);
		//after adding mapper class no need this type of code
//		Post post = new Post();
//		post.setId(dto.getId());
//		post.setTitle(dto.getTitle());
//		post.setDescription(dto.getDescription());
//		post.setContent(dto.getContent());
		return post;
	}

	@Override
	public PostResponse Getposts(int pageNo, int pageSize, String Sortby,String SortDir) {
		
		Sort sort=SortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(Sortby).ascending():Sort.by(Sortby).descending();
//		create pageable instance
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
//		List<Post> posts = postRepository.findAll();

		Page<Post> posts = postRepository.findAll(pageable);
		List<Post> Listofposts = posts.getContent();

		List<PostDto> content = Listofposts.stream().map(post -> maptodto(post)).collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(content);
		postResponse.setPageNo(posts.getNumber());
		postResponse.setPageSize(posts.getSize());
		postResponse.setTotalElements(posts.getTotalElements());

		postResponse.setTotalPages(posts.getTotalPages());
		postResponse.setLast(posts.isLast());

		return postResponse;

	}

	@Override
	public PostDto getbyid(Long id) {
		// TODO Auto-generated method stub
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id));
		return maptodto(post);
	}

	@Override
	public PostDto updatepost(PostDto dto, Long id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id));
		post.setTitle(dto.getTitle());
		
		//for category attached
		Category category = categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("category", "id",dto.getCategoryid()));
	
		post.setDescription(dto.getDescription());
		post.setContent(dto.getContent());
		post.setCategory(category);
		Post update = postRepository.save(post);

		return maptodto(post);
	}

	@Override
	public void deletepost(Long id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id));
		postRepository.delete(post);

	}


	@Override
	public List<PostDto> getPostsByCategory(Long categoryid) {
		categoryRepository.findById(categoryid).orElseThrow(()->new ResourceNotFoundException("Category", "id", categoryid));
		
		List<Post> posts=postRepository.findByCategoryId(categoryid);
		
		return posts.stream().map((post)->maptodto(post)).collect(Collectors.toList()) ;
	}

}
