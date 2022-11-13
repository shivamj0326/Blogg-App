package com.harith.blog.service;

import com.harith.blog.payload.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto comment, Integer postId);
	
	void deleteComment(Integer commentId);
}
