package com.blogspots.blog.services;



import com.blogspots.blog.exception.ResourceNotFoundException;
import com.blogspots.blog.payloads.CommentDto;

public interface CommentService {
//create comment
	CommentDto createComment(CommentDto commentDto, Integer userId, Integer postId , Integer categoryId) throws ResourceNotFoundException;

//update Comment
	CommentDto updateComment(CommentDto commentDto,Integer commentId);
	
//delete comment 
	 void  deleteComment(Integer commentId) throws ResourceNotFoundException;
	
}
