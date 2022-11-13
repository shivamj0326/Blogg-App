package com.harith.blog.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harith.blog.entity.Comment;
import com.harith.blog.entity.Post;
import com.harith.blog.exception.ResourceNotFoundException;
import com.harith.blog.payload.CommentDto;
import com.harith.blog.repository.CommentRepository;
import com.harith.blog.repository.PostRepository;
import com.harith.blog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private CommentRepository commentRepo;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public CommentDto createComment(CommentDto comment, Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
		Comment comm = this.mapper.map(comment, Comment.class);
		comm.setPost(post);
		Comment savedComment = this.commentRepo.save(comm);
		return this.mapper.map(savedComment, CommentDto.class);
		
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment = this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "Commentid", commentId));
		this.commentRepo.delete(comment);
		
	}
	
	

}
