package com.blogspots.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogspots.blog.entity.Comment;
import com.blogspots.blog.entity.User;
import com.blogspots.blog.payloads.CommentDto;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
	
	List<CommentDto> findByContent(String content);
	List<CommentDto> findByUser(User user);

}
