package com.harith.blog.service;

import java.util.List;


import com.harith.blog.payload.PostDto;

public interface PostService {

	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
	PostDto updatePost(PostDto postDto, Integer postId);
	
	void deletePost(Integer postId);
	
	List<PostDto> getAllPosts(Integer pageNumber, Integer pageSize);
	
	List<PostDto> getAllPostByCategory(Integer categoryId);
	
	List<PostDto> getAllPostByUser(Integer userId);
	
	List<PostDto> searchAllPosts(String keyword);

	PostDto getPostById(Integer postId);
}
