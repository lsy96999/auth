package seongyun.auth.config;

import lombok.Data;

@Data
public class RVO<T> {
	private String code;
	private String message;
	private T data;
}
