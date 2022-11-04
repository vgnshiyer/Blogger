package com.vgnshiyer.blog.api.payloads;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
	
	private Integer categoryId;
	
	@NotEmpty(message = "Category cannot be empty")
	@Size(min = 4, max = 25, message = "Category should be atleast 4 characters and at max 25 characters!!")
	private String categoryTitle;
	
	@NotEmpty(message = "Description cannot be empty")
	private String categoryDescription;
}
