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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vgnshiyer.blog.api.payloads.ApiResponse;
import com.vgnshiyer.blog.api.payloads.CategoryDto;
import com.vgnshiyer.blog.api.payloads.UserDto;
import com.vgnshiyer.blog.api.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	// POST
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		
		return new ResponseEntity<>(this.categoryService.createCategory(categoryDto), HttpStatus.CREATED);
	}
	
	// PUT
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable("categoryId") Integer Id){
		
		return new ResponseEntity<>(this.categoryService.updateCategory(categoryDto, Id), HttpStatus.OK);
	}
	
	// DELETE
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") Integer Id){
		this.categoryService.deleteCategory(Id);
		return ResponseEntity.ok(new ApiResponse("Category deleted successfully", true));
	}	
	
	// GET
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getUser(@PathVariable("categoryId") Integer Id){
		return ResponseEntity.ok(this.categoryService.getCategory(Id));
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllUser(){
		return ResponseEntity.ok(this.categoryService.getAllCategories());
	}
}
