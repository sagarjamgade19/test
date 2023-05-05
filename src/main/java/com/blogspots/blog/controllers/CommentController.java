package com.blogspots.blog.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogspots.blog.exception.ResourceNotFoundException;
import com.blogspots.blog.payloads.CommentDto;
import com.blogspots.blog.services.CommentService;

@RestController

public class CommentController {
	@Autowired
	private CommentService commentService;
	
	//Add comment
	@PostMapping("/user/{userId}/post/{postId}/category/{categoryId}/comment")
	public ResponseEntity<CommentDto> addComment(@RequestBody CommentDto commentDto,
			@PathVariable Integer userId,
			@PathVariable Integer postId,
			@PathVariable Integer categoryId) throws ResourceNotFoundException{
		
		CommentDto commentAdded = commentService.createComment(commentDto, userId, postId, categoryId);
				return new ResponseEntity<CommentDto>(commentAdded, HttpStatus.CREATED);
		
		}
	
	@DeleteMapping()
	public ResponseEntity<?>deleteComment(@PathVariable Integer commentId) throws ResourceNotFoundException{
		
		commentService.deleteComment(commentId);
		return new ResponseEntity(Map.of("Message","Post Deleted Succesfully"),HttpStatus.OK);
		
	}
}
