package com.harith.blog.service;

import java.util.List;


import com.harith.blog.payload.PostDto;
import com.harith.blog.payload.PostResponse;

public interface PostService {

	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
	PostDto updatePost(PostDto postDto, Integer postId);
	
	void deletePost(Integer postId);
	
	PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
	
	PostResponse getAllPostByCategory(Integer categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
	
	PostResponse getAllPostByUser(Integer userId, Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
	
	List<PostDto> searchAllPosts(String keyword);

	PostDto getPostById(Integer postId);
}
