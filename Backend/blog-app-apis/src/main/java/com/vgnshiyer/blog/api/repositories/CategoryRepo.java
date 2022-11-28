package com.vgnshiyer.blog.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vgnshiyer.blog.api.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{
	
}
