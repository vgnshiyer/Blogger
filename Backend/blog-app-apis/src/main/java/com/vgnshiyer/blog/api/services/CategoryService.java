package com.vgnshiyer.blog.api.services;

import java.util.List;

import com.vgnshiyer.blog.api.payloads.CategoryDto;

public interface CategoryService {
	// create
	CategoryDto createCategory(CategoryDto categoryDto);

	// update
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

	// delete
	void deleteCategory(Integer Id);

	// get
	CategoryDto getCategory(Integer categoryId);

	// get all
	List<CategoryDto> getAllCategories();
}
