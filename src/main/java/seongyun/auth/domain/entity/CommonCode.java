package seongyun.auth.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import seongyun.auth.util.EntitiyUtil;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommonCode extends Base{
	private String codeId;
	private String codeValue;
	private String codeNm;
	private String codeDesc;
	private String codeGroup;
	private Integer sortOrdr;
	
	@Builder
	public CommonCode(
			Object codeId, Object codeValue, Object codeNm,
			Object codeDesc, Object codeGroup, Object sortOrdr,
			Object useYn, Object createAt, Object updateAt, Object createBy, Object updateBy) {
		super(useYn, updateAt, updateAt, createBy, updateBy);
		this.codeId = EntitiyUtil.toString(codeId);
		this.codeValue = EntitiyUtil.toString(codeValue);
		this.codeNm = EntitiyUtil.toString(codeNm);
		this.codeDesc = EntitiyUtil.toString(codeDesc);
		this.codeGroup = EntitiyUtil.toString(codeGroup);
		this.sortOrdr =  EntitiyUtil.toIntger(sortOrdr);
	}
}
