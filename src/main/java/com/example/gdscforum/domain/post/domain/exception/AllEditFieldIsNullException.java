package com.example.gdscforum.domain.post.domain.exception;

public class AllEditFieldIsNullException extends RuntimeException {
	public AllEditFieldIsNullException() {
		super("게시글 수정 값 모두가 null입니다.");
	}
}
