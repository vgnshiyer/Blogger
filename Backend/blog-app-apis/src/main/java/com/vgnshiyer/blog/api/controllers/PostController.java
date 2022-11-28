package com.vgnshiyer.blog.api.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.multipart.MultipartFile;

import com.vgnshiyer.blog.api.config.AppConstants;
import com.vgnshiyer.blog.api.entities.Comment;
import com.vgnshiyer.blog.api.payloads.ApiResponse;
import com.vgnshiyer.blog.api.payloads.CommentDto;
import com.vgnshiyer.blog.api.payloads.PostDto;
import com.vgnshiyer.blog.api.payloads.PostResponse;
import com.vgnshiyer.blog.api.services.CommentService;
import com.vgnshiyer.blog.api.services.FileService;
import com.vgnshiyer.blog.api.services.PostService;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private CommentService commentService;
	
	private String path = "images/";
	
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
			@RequestParam(value = "pageNum", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir
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
	
	// POST image
	@PostMapping("/posts/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadImage(@RequestParam("image") MultipartFile file, @PathVariable Integer postId) throws IOException{
		PostDto postDto = this.postService.getPostById(postId);
		
		String fileName = this.fileService.uploadImage(path, file);
		
		postDto.setImageName(fileName);
		PostDto updatedPost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
	}
	
	// GET image
	@GetMapping(value = "/posts/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void getImage(@PathVariable String imageName, HttpServletResponse response) throws IOException{
		
		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		
		StreamUtils.copy(resource, response.getOutputStream());
		
	}
	
	// POST comment
	@PostMapping(value = "/posts/{postId}/comment/create/user/{userId}")
	public ResponseEntity<CommentDto> postComment(@PathVariable Integer postId, @PathVariable Integer userId, @RequestBody CommentDto commentDto) {
		return new ResponseEntity<CommentDto>(this.commentService.createComment(commentDto, postId, userId), HttpStatus.OK);
	}
	
	// DELETE comment
	@DeleteMapping(value = "/posts/comment/{commentId}/delete")
	public ResponseEntity<ApiResponse> postComment(@PathVariable Integer commentId) {
		return new ResponseEntity<ApiResponse>(this.commentService.deleteComment(commentId), HttpStatus.OK);
	}
}
