package com.vgnshiyer.blog.api.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.vgnshiyer.blog.api.entities.Category;
import com.vgnshiyer.blog.api.entities.Post;
import com.vgnshiyer.blog.api.entities.User;
import com.vgnshiyer.blog.api.exceptions.ResourceNotFoundException;
import com.vgnshiyer.blog.api.payloads.PostDto;
import com.vgnshiyer.blog.api.payloads.PostResponse;
import com.vgnshiyer.blog.api.repositories.CategoryRepo;
import com.vgnshiyer.blog.api.repositories.PostRepo;
import com.vgnshiyer.blog.api.repositories.UserRepo;
import com.vgnshiyer.blog.api.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired 
	private UserRepo userRepo;
	
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setDate(new Date());
		post.setImageName("default.png");
		post.setCategory(this.modelMapper.map(this.categoryRepo.findById(categoryId), Category.class));
		post.setUser(this.modelMapper.map(this.userRepo.findById(userId), User.class));
		
		Post saved_post = this.postRepo.save(post);
		return this.modelMapper.map(saved_post, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		Post updatedPost = this.postRepo.save(post);
		
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
		this.postRepo.delete(post);
	}

	@Override
	public PostResponse getAllPosts(Integer pageNum, Integer pageSize, String sortBy, String sortDir) {
		
		Pageable p = PageRequest.of(pageNum, pageSize, 
				(sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending()));
		
		Page<Post> pages = this.postRepo.findAll(p);
		
		List<Post> allPosts = pages.getContent();
		
		List<PostDto> posts = allPosts.stream()
				.map(post->this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(posts);
		postResponse.setPageNum(pages.getNumber());
		postResponse.setPageSize(pages.getSize());
		postResponse.setNumRecords(pages.getTotalElements());
		postResponse.setTotalPages(pages.getTotalPages());
		postResponse.setLastPage(pages.isLast());
		
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post =  this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "ID", categoryId));
		List<Post> posts = this.postRepo.findByCategory(category);
		
		List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class))
								.collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("User", "ID", userId));
		List<Post> posts = this.postRepo.findByUser(user);
		
		List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts = this.postRepo.findByTitleContaining(keyword);
		List<PostDto> postDtos = posts.stream().map(post -> (this.modelMapper.map(post, PostDto.class))).collect(Collectors.toList());
		return postDtos;
	}

}
