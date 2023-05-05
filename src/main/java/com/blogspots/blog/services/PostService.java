package com.blogspots.blog.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.blogspots.blog.entity.Post;
import com.blogspots.blog.exception.ResourceNotFoundException;
import com.blogspots.blog.payloads.PostDto;

public interface PostService {
 PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) throws ResourceNotFoundException;
 
 PostDto updatePost(PostDto dto,Integer integer) throws ResourceNotFoundException;
 
  void deletePost(Integer integer) throws ResourceNotFoundException;
  
  List<PostDto>getAllPost();
  
  PostDto getPostById(Integer integer) throws ResourceNotFoundException;
  
  List<PostDto> getPostsByCategory(Integer categoryId) throws ResourceNotFoundException;
  
  List<PostDto> getPostByUser(Integer userId) throws ResourceNotFoundException;
  
  List<PostDto> searchPosts(String keyword);
}
