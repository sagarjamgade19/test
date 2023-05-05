package com.blogspots.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogspots.blog.entity.Category;
import com.blogspots.blog.entity.Post;
import com.blogspots.blog.entity.User;
import com.blogspots.blog.exception.ResourceNotFoundException;
import com.blogspots.blog.payloads.PostDto;
import com.blogspots.blog.repository.CategoryRepo;
import com.blogspots.blog.repository.PostRepo;
import com.blogspots.blog.repository.UserRepository;
import com.blogspots.blog.services.PostService;
@Service
public class PostServiceImpl  implements PostService{
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private UserRepository userrepository;
	@Autowired
	private CategoryRepo categoryRepo;

	

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) throws ResourceNotFoundException {
		
		User user=this.userrepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User Not Found With Id"+userId));
		
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category Not found with Id"+categoryId));
		
		
		Post post=this.mapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost=postRepo.save(post);
		return this.mapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto dto, Integer postId) throws ResourceNotFoundException {
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post Not found with given Id"+postId));
		post.setTitle(dto.getTitle());
		post.setContent(dto.getContent());
		Post updatedPost= this.postRepo.save(post);
		
		PostDto updatedDto=this.mapper.map(updatedPost, PostDto.class);
		
		return updatedDto;
	}

	@Override
	public void deletePost(Integer postId) throws ResourceNotFoundException {
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post Not found with given Id"+postId));
		this.postRepo.delete(post);
	}

	@Override
	public List<PostDto> getAllPost() {
	List<Post>posts=	postRepo.findAll();
	List<PostDto>dtoPosts=posts.stream().map((post)->mapper.map(post, PostDto.class)).collect(Collectors.toList());
		return dtoPosts;
	}

	@Override
	public PostDto getPostById(Integer postId) throws ResourceNotFoundException {
	Post post=	this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post Not found with given Id"+postId));
	PostDto dtoPost=this.mapper.map(post, PostDto.class)	;
	return dtoPost;
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) throws ResourceNotFoundException {
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category Not Found with givern Id "+categoryId));
		List<Post>categoryPosts=this.postRepo.findByCategory(category);
		
		List<PostDto>posts= categoryPosts.stream().map((post)->this.mapper.map(post, PostDto.class)).collect(Collectors.toList());
		return posts;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) throws ResourceNotFoundException {
		User user=this.userrepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User Not Found with givern Id "+userId));
		
		List<Post>posts=this.postRepo.findByUser(user);
		
		List<PostDto>poByUser=posts.stream().map((post)->this.mapper.map(post, PostDto.class)).collect(Collectors.toList());
		return poByUser;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts = this.postRepo.findByTitle("%"+keyword+"%");
		List<PostDto> resultPosts = posts.stream().map((post)->this.mapper.map(post, PostDto.class)).collect(Collectors.toList()); 
		return resultPosts;
	}

	
}
