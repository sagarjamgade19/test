package com.blogspots.blog.payloads;

import jakarta.validation.constraints.NotEmpty;

public class CategoryDto {
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
	public String getCategoryDescription() {
		return categoryDescription;
	}
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
	@NotEmpty(message = "Title can't be empty")
	private String categoryTitle;
	@NotEmpty(message = "categoryDescription can't be empty")
	private String categoryDescription;
	public CategoryDto() {}
	
}
