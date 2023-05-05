package com.blogspots.blog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private int id;
	private String content;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	 public Category getCategory() { return category; } public void
	  setCategory(Category category) { this.category = category; }
	 
	@ManyToOne
	private Post post;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	 @ManyToOne 
	 @JoinColumn(name = "category_id")
	 private Category category;
	 public Comment() {}

}
