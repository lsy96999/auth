package seongyun.auth.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TkAdminRole extends Base{
	private Long adminSn;
	private CommonCode adminSttusCode; 
	
	@Builder
	public TkAdminRole(
			Object adminSn, Object adminSttusCode,
			Object useYn, Object createAt, Object updateAt, Object createBy, Object updateBy) {
		super(useYn, createAt, updateAt, createBy, updateBy);
		this.adminSn = Long.parseLong(String.valueOf(adminSn));
		this.adminSttusCode = (CommonCode) adminSttusCode;
	}
}
