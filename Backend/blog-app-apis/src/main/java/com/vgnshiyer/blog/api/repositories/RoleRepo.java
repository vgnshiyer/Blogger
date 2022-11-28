package com.vgnshiyer.blog.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vgnshiyer.blog.api.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {
	
}
