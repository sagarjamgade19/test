package com.blogspots.blog.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogspots.blog.exception.ResourceNotFoundException;
import com.blogspots.blog.payloads.PostDto;
import com.blogspots.blog.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {
	@Autowired
	private PostService postService;
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto>createPost(@RequestBody PostDto dto,@PathVariable Integer userId,@PathVariable Integer categoryId) throws ResourceNotFoundException{
		
		
				PostDto postDto=this.postService.createPost(dto, userId, categoryId);
				
	return new ResponseEntity<PostDto>(postDto,HttpStatus.CREATED);}
	
	
	//This Method returs all posts By User
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId) throws ResourceNotFoundException{
		
		List<PostDto>posts=this.postService.getPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}
	
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId) throws ResourceNotFoundException{
		
		List<PostDto>posts=this.postService.getPostsByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}

	//get All Posts
	@GetMapping("/posts")
	public ResponseEntity<List<PostDto>> getAllPosts(){
		List<PostDto>posts=this.postService.getAllPost();
		return new  ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
	//get posts by id
	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDto>getPostById(@PathVariable Integer postId) throws ResourceNotFoundException{
		PostDto postDto=this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
	}
	
	//delete post by id
	@DeleteMapping("posts/{postId}")
	public ResponseEntity<?> deletePost(@PathVariable Integer postId) throws ResourceNotFoundException 
	{
		this.postService.deletePost(postId);
		return new ResponseEntity(Map.of("Message","Post Deleted Succesfully"),HttpStatus.OK);
	}

	//update Post
	@PutMapping("posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto dto,@PathVariable Integer postId) throws ResourceNotFoundException{
		PostDto updatedPost=this.postService.updatePost(dto, postId);
		
		return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
	}
	
	//search Post
	@GetMapping("/posts/search/{title}")
	public ResponseEntity<List<PostDto>>searchPostByTitle(@PathVariable String title){
		List<PostDto> posts = this.postService.searchPosts(title);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}
	
}
