package com.vgnshiyer.blog.api.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CommentDto {
	private String Id;
	
	private String content;
	
	private Integer userId;
}
