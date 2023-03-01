package seongyun.auth.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TkAdminJoinDto {
	private String adminNm;
	private String adminMail;
	private String adminPw;
}
