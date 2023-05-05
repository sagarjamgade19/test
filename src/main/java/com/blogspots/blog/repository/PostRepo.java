package com.blogspots.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.blogspots.blog.entity.Category;
import com.blogspots.blog.entity.Post;
import com.blogspots.blog.entity.User;
import com.blogspots.blog.payloads.PostDto;

public interface PostRepo extends JpaRepository<Post, Integer> {
	
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	@Query("select p from Post p where p.title like :key")
	List<Post> findByTitle(@Param("key") String title);

}
