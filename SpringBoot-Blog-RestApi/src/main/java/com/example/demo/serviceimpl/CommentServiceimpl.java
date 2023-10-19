package com.example.demo.serviceimpl;




import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Comment;
import com.example.demo.entity.Post;
import com.example.demo.exception.BlogApiException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.payload.CommentDto;
import com.example.demo.repository.CommentReository;
import com.example.demo.repository.PostRepository;
import com.example.demo.service.CommentServie;

@Service
public class CommentServiceimpl implements CommentServie{

	
	private CommentReository commentReository;
	private PostRepository postRepository;
	
	private ModelMapper mapper;
	
	
    


	public CommentServiceimpl(CommentReository commentReository, PostRepository postRepository, ModelMapper mapper) {
		super();
		this.commentReository = commentReository;
		this.postRepository = postRepository;
		this.mapper = mapper;
	}
	@Override
	public CommentDto createcomment(Long postid, CommentDto commentDto) {
		Comment comment=mapToEnity(commentDto);
		
		Post post=postRepository.findById(postid).orElseThrow(()->new ResourceNotFoundException("post", "id", postid));
		comment.setPost(post);
		Comment newcomment = commentReository.save(comment);
		return maptoDto(newcomment);
		
		
		
	}
	public CommentDto maptoDto(Comment comment) {
		CommentDto commentDto = mapper.map(comment,CommentDto.class);
//		CommentDto commentDto=new CommentDto();
//		commentDto.setId(comment.getId());
//		commentDto.setName(comment.getName());
//		commentDto.setEmail(comment.getEmail());
//		commentDto.setBody(comment.getBody());
		return commentDto;
	}

	public Comment mapToEnity(CommentDto commentDto) {
		Comment comment = mapper.map(commentDto,Comment.class);
//		Comment comment=new Comment();
//		comment.setId(commentDto.getId());
//		comment.setName(commentDto.getName());
//		comment.setEmail(commentDto.getEmail());
//		comment.setBody(commentDto.getBody());
		return comment;
		

		
	}

	@Override
	public CommentDto getcommentbyid(Long postid, Long commentid) {
		//retrive post by id
	Post post=postRepository.findById(postid).orElseThrow(()->new ResourceNotFoundException("post", "id", postid));
		//retrive comment by id
	Comment comment=commentReository.findById(commentid).orElseThrow(()->new ResourceNotFoundException("comment", "id", commentid));
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belongs to post");
		}
		return maptoDto(comment);
	}

	@Override
	public CommentDto updatecommentbyid(Long postid, Long commentid, CommentDto commentDto) {
		Post post=postRepository.findById(postid).orElseThrow(()->new ResourceNotFoundException("post", "id", postid));
		Comment comment=commentReository.findById(commentid).orElseThrow(()->new ResourceNotFoundException("comment", "id", commentid));
		if(!comment.getPost().getId().equals(post.getId())) {
          throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belongs to post");
          
          
	}
		comment.setName(commentDto.getName());
		comment.setEmail(commentDto.getEmail());
		comment.setBody(commentDto.getBody());
		Comment update = commentReository.save(comment);

		return maptoDto(update) ;

//	@Override
//	public List<CommentDto> getcommentsbypostid(long postid) {
//		//retrive comments by postid;
//		List<Comment> comments=commentReository.findbypostid(postid);
//		
//		//convert list of comment entities to comment dto's
//		return comments.stream().map(comment->maptoDto(comment)).collect(Collectors.toList()) ;
//	}
}

	@Override
	public void Deletecommentbyid(Long postid, Long commentid) {
		Post post=postRepository.findById(postid).orElseThrow(()->new ResourceNotFoundException("post", "id", postid));
		Comment comment=commentReository.findById(commentid).orElseThrow(()->new ResourceNotFoundException("comment", "id", commentid));
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belongs to post");
		}
		commentReository.delete(comment);
		
		
	}
}
