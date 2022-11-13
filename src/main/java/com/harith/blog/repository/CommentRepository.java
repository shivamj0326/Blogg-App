package com.harith.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harith.blog.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

}
