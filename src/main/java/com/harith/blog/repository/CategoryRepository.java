package com.harith.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harith.blog.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
}
