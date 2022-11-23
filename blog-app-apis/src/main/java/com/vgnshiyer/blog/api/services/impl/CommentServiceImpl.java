package com.vgnshiyer.blog.api.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vgnshiyer.blog.api.entities.Comment;
import com.vgnshiyer.blog.api.entities.Post;
import com.vgnshiyer.blog.api.entities.User;
import com.vgnshiyer.blog.api.exceptions.ResourceNotFoundException;
import com.vgnshiyer.blog.api.payloads.ApiResponse;
import com.vgnshiyer.blog.api.payloads.CommentDto;
import com.vgnshiyer.blog.api.repositories.CommentRepo;
import com.vgnshiyer.blog.api.repositories.PostRepo;
import com.vgnshiyer.blog.api.repositories.UserRepo;
import com.vgnshiyer.blog.api.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private ApiResponse apiResponse = new ApiResponse(null, false);

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(()-> new ResourceNotFoundException("post", "id", postId));
		User user = this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("user", "id", postId));

		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		comment.setUser(user);
		
		return this.modelMapper.map(this.commentRepo.save(comment), CommentDto.class);
	}

	@Override
	public ApiResponse deleteComment(Integer commentId) {
		Comment comment = this.commentRepo.findById(commentId)
			.orElseThrow(() -> new ResourceNotFoundException("comment", "id", commentId));
		
		this.commentRepo.delete(comment);
		return new ApiResponse("Comment deleted successfully!", true);
	}

}
