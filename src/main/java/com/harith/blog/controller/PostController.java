package com.harith.blog.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.harith.blog.payload.ApiResponse;
import com.harith.blog.payload.PostDto;
import com.harith.blog.payload.PostResponse;
import com.harith.blog.service.FileService;
import com.harith.blog.service.PostService;

@RestController
@RequestMapping("/")
public class PostController {
	
	@Autowired
	PostService postService;
	
	@Autowired
	FileService fileService;
	
	@Value("${project.image}")
	private String path;

	@PostMapping("user/{userId}/category/{categoryId}/post")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId){
		PostDto savedPost = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
		
	}
	
	@GetMapping("user/{userId}/post")
	public ResponseEntity<PostResponse> getPostsByUser(@PathVariable Integer userId, @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir){
		return new ResponseEntity<>(this.postService.getAllPostByUser(userId, pageNumber, pageSize, sortBy, sortDir), HttpStatus.OK);
	}
	
	@GetMapping("category/{categoryId}/post")
	public ResponseEntity<PostResponse> getPostsByCategory(@PathVariable Integer categoryId, @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir){
		return new ResponseEntity<>(this.postService.getAllPostByCategory(categoryId, pageNumber, pageSize, sortBy, sortDir), HttpStatus.OK);
	}
	
	@GetMapping("post")
	public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir){
		
		return new ResponseEntity(this.postService.getAllPosts(pageNumber, pageSize, sortBy, sortDir), HttpStatus.OK);
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
	
	@GetMapping("post/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPost(@PathVariable String keyword){
		return new ResponseEntity<>(this.postService.searchAllPosts(keyword), HttpStatus.OK);
	}
	
	@PostMapping("post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadImage(@PathVariable("postId") Integer postId, @RequestParam("image") MultipartFile image) throws IOException{
		PostDto post = this.postService.getPostById(postId);
		
		String fileName = this.fileService.uploadImage(path, image);
		
		post.setImageName(fileName);
		PostDto updatedPost = this.postService.updatePost(post, postId);
		
		return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
		
	}
	
	@GetMapping(value = "post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {
		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
	
	
}
