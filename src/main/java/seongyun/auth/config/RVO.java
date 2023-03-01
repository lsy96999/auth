package seongyun.auth.config;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RVO<T> {
	private String code;
	private String message;
	private T data;
	
	@Builder
	public RVO(String code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}
}
