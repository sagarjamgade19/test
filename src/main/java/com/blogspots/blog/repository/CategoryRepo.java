package com.blogspots.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogspots.blog.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
