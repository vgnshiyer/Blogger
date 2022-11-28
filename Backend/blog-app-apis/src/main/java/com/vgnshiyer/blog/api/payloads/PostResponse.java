package com.vgnshiyer.blog.api.payloads;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse {
	
	private List<PostDto> content;
	private int pageNum;
	private int pageSize;
	private long numRecords;
	private int totalPages;
	private boolean lastPage;
}
