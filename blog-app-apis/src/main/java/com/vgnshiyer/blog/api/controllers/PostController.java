package com.vgnshiyer.blog.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vgnshiyer.blog.api.payloads.ApiResponse;
import com.vgnshiyer.blog.api.payloads.PostDto;
import com.vgnshiyer.blog.api.payloads.PostResponse;
import com.vgnshiyer.blog.api.services.PostService;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	PostService postService;
	
	// POST
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto,
			@PathVariable("userId") Integer userId, 
			@PathVariable("categoryId") Integer categoryId){
		
		return new ResponseEntity<>(this.postService.createPost(postDto, userId, categoryId), HttpStatus.CREATED);
	}
	
	// GET
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNum", defaultValue = "0", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
			){
		
		return ResponseEntity.ok(this.postService.getAllPosts(pageNum, pageSize, sortBy, sortDir));
	}
	
	// GET
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
		
		return ResponseEntity.ok(this.postService.getPostById(postId));
	}
	
	// GET
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){
		List<PostDto> posts = this.postService.getPostsByUser(userId);
		return ResponseEntity.ok(posts);
	}
	
	// GET
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId){
		List<PostDto> posts = this.postService.getPostsByCategory(categoryId);
		return ResponseEntity.ok(posts);
	}
	
	// PUT
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId){
		PostDto post = this.postService.updatePost(postDto, postId);
		return ResponseEntity.ok(post);
	}
	
	// DELETE
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
		this.postService.deletePost(postId);
		return ResponseEntity.ok(new ApiResponse("Post deleted successfully", true));
	}
	
	// GET
	@GetMapping("/posts/search")
	public ResponseEntity<List<PostDto>> searchPostsByTitle(
			@RequestParam(value = "keyword", defaultValue = "", required = false) String keyword
			){
		return ResponseEntity.ok(this.postService.searchPosts(keyword));
	}
}
