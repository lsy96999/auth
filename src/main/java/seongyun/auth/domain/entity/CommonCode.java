package seongyun.auth.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class CommonCode extends Base{
	private String codeId;
	private String codeValue;
	private String codeNm;
	private String codeDesc;
	private String codeGroup;
	private Integer sortOrdr;
}
