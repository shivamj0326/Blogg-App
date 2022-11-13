package com.harith.blog.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.harith.blog.entity.Category;
import com.harith.blog.entity.Post;
import com.harith.blog.entity.User;

public interface PostRepository extends JpaRepository<Post, Integer> {

	Page<Post> findByUser(User user, Pageable page);
	
	Page<Post> findByCategory(Category category, Pageable page);
	
	List<Post> findByTitleContaining(String title);

}
