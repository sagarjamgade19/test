package com.blogspots.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogspots.blog.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
