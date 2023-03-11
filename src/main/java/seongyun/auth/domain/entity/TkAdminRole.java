package seongyun.auth.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TkAdminRole extends Base{
	private Long adminSn;
	private CommonCode adminRoleCode; 
	
//	@Builder
//	public TkAdminRole(
//			Object adminSn, Object adminRoleCode,
//			Object useYn, Object createAt, Object updateAt, Object createBy, Object updateBy) {
//		super(useYn, createAt, updateAt, createBy, updateBy);
//		this.adminSn = Long.parseLong(String.valueOf(adminSn));
//		this.adminRoleCode = (CommonCode) adminRoleCode;
//	}
}
