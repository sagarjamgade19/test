package com.blogspots.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogspots.blog.entity.Category;
import com.blogspots.blog.entity.Comment;
import com.blogspots.blog.entity.Post;
import com.blogspots.blog.entity.User;
import com.blogspots.blog.exception.ResourceNotFoundException;
import com.blogspots.blog.payloads.CommentDto;
import com.blogspots.blog.repository.CategoryRepo;
import com.blogspots.blog.repository.CommentRepo;
import com.blogspots.blog.repository.PostRepo;
import com.blogspots.blog.repository.UserRepository;
import com.blogspots.blog.services.CommentService;
@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	ModelMapper mapper;
	@Autowired
	private PostRepo postRepo;
	
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private CommentRepo commentRepo;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer userId, Integer postId, Integer categoryId) throws ResourceNotFoundException {
		System.out.println(userId);
		  User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User Not Found with id "+userId));
		  
		  Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post Not Found"));
		    Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("You have entered wrong Cat id"));
		    
		    Comment comment=this.mapper.map(commentDto, Comment.class);
		    comment.setContent(commentDto.getContent());
		    comment.setUser(user);
		    comment.setPost(post);
		    comment.setCategory(category);
		    
		   Comment commentCreated = commentRepo.save(comment);
		   
		   CommentDto commentCreatedDto = this.mapper.map(commentCreated, CommentDto.class);
		return commentCreatedDto;
	}

	@Override
	public CommentDto updateComment(CommentDto commentDto, Integer commentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteComment(Integer commentId) throws ResourceNotFoundException {
		Comment comment=this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("comment not found with given Id"+commentId));
		this.commentRepo.delete(comment);
	}

}
