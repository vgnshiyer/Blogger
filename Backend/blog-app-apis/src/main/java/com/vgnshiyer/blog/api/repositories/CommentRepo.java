package com.vgnshiyer.blog.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vgnshiyer.blog.api.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{
	
}
