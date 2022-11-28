package com.vgnshiyer.blog.api.services;

import com.vgnshiyer.blog.api.payloads.ApiResponse;
import com.vgnshiyer.blog.api.payloads.CommentDto;

public interface CommentService {
	CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId);
	ApiResponse deleteComment(Integer commentId);
}
