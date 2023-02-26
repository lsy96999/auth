package seongyun.auth.config.exception;

import lombok.Getter;

@Getter
public enum ExceptionCode {
	COMMON_ERROR("B-9999","비지니스로직상 에러");
	private final String code;
	private final String desc;
	private ExceptionCode(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
}
