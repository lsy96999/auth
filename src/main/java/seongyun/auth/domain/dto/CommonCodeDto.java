package seongyun.auth.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommonCodeDto {
	private Long codeSn;
	private String codeId;
	private String codeValue;
	private String codeNm;
	private String codeDesc;
	private String codeGroup;
}
