package com.harith.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.harith.blog.payload.ApiResponse;
import com.harith.blog.payload.PostDto;
import com.harith.blog.service.PostService;

@RestController
@RequestMapping("/")
public class PostController {
	
	@Autowired
	PostService postService;

	@PostMapping("user/{userId}/category/{categoryId}/post")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId){
		PostDto savedPost = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
		
	}
	
	@GetMapping("user/{userId}/post")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){
		return new ResponseEntity<>(this.postService.getAllPostByUser(userId), HttpStatus.OK);
	}
	
	@GetMapping("category/{categoryId}/post")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId){
		return new ResponseEntity<>(this.postService.getAllPostByCategory(categoryId), HttpStatus.OK);
	}
	
	@GetMapping("post")
	public ResponseEntity<List<PostDto>> getAllPosts(@RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize){
		
		return new ResponseEntity(this.postService.getAllPosts(pageNumber, pageSize), HttpStatus.OK);
	}
	
	@GetMapping("post/{postId}")
	public ResponseEntity<List<PostDto>> getPostById(@PathVariable Integer postId){
		return new ResponseEntity(this.postService.getPostById(postId), HttpStatus.OK);
	}
	
	@DeleteMapping("post/{postId}")
	public ResponseEntity<ApiResponse> deletePostById(@PathVariable Integer postId){
		this.postService.deletePost(postId);
		return new ResponseEntity<>(new ApiResponse("Post with postId " + postId + " deleted successfully", true), HttpStatus.OK);
	}
	
	@PutMapping("post/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId){
		return new ResponseEntity<>(this.postService.updatePost(postDto, postId), HttpStatus.OK);
		
	}
}
