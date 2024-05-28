package com.example.gdscforum.domain.post.application.exception;

public class PostNotFoundException extends RuntimeException {
	public PostNotFoundException() {
		super("해당하는 게시글을 찾을 수 없습니다.");
	}
}
