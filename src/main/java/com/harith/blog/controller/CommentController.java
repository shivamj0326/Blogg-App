package com.harith.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harith.blog.payload.ApiResponse;
import com.harith.blog.payload.CommentDto;
import com.harith.blog.service.CommentService;

@RestController
@RequestMapping("/")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("post/{postId}/comment")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment, @PathVariable("postId") Integer postId){
		CommentDto createdComment = this.commentService.createComment(comment, postId);
		return new ResponseEntity<CommentDto>(createdComment, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable("commentId") Integer commentId){
		this.commentService.deleteComment(commentId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment Deleted Successfully", true), HttpStatus.OK);
	}

}
