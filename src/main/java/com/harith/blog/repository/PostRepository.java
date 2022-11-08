package com.harith.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harith.blog.entity.Category;
import com.harith.blog.entity.Post;
import com.harith.blog.entity.User;

public interface PostRepository extends JpaRepository<Post, Integer> {

	List<Post> findByUser(Integer userId);
	
	List<Post> findByCategory(Category category);

	List<Post> findByUser(User user);
}
