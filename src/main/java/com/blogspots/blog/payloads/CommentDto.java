package com.blogspots.blog.payloads;

public class CommentDto {
private int id;
private String content;
private PostDto post;
private UserDto user;
private CategoryDto category;
public int getId() {
	return id;
}
public CommentDto(int id, String content, PostDto post, UserDto user, CategoryDto category) {
	super();
	this.id = id;
	this.content = content;
	this.post = post;
	this.user = user;
	this.category = category;
}
public PostDto getPost() {
	return post;
}
public void setPost(PostDto post) {
	this.post = post;
}
public UserDto getUser() {
	return user;
}
public void setUser(UserDto user) {
	this.user = user;
}
public CategoryDto getCategory() {
	return category;
}
public void setCategory(CategoryDto category) {
	this.category = category;
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
public CommentDto() {}
}
