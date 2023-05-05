package com.blogspots.blog.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name ="category_tbl")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryTitle() {
		return categoryTitle;
	}
	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}
	public List<Post> getPosts() {
		return posts;
	}
	public Category(Integer categoryId, String categoryTitle, String categoryDescription, List<Post> posts) {
		super();
		this.categoryId = categoryId;
		this.categoryTitle = categoryTitle;
		this.categoryDescription = categoryDescription;
		this.posts = posts;
	}

	/*
	 * public Set<Comment> getComments() { return comments; } public void
	 * setComments(Set<Comment> comments) { this.comments = comments; }
	 */
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	public String getCategoryDescription() {
		return categoryDescription;
	}
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
	private String categoryTitle;
	private String categoryDescription;
	
	@OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
	  private List<Post>posts=new ArrayList<>();
		 @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
		 private Set<Comment>comments=new HashSet<>();
	
	public Category() {}
	
	
}
