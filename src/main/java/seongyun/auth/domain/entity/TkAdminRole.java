package seongyun.auth.domain.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Table("tb_tk_admin_role")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TkAdminRole /*extends Base*/{
	@Id@Column("admin_role_sn") private Long adminRoleSn;
	@Column("admin_sn") private Long adminSn;
	@Column("tk_admin_role_code") private String tkAdminRoleCode;
	@Column("use_yn")		private String useYn;
	@CreatedDate
	@Column("create_at")	private LocalDateTime createAt;
	@CreatedBy
	@Column("create_by")	private Long createBy;
	@LastModifiedDate
	@Column("update_at")	private LocalDateTime updateAt;
	@LastModifiedBy
	@Column("update_by")	private Long updateBy;
//	private CommonCode adminRoleCode; 
	
	@Builder
	public TkAdminRole(
			Long adminSn, String tkAdminRoleCode, String useYn) {
		this.adminSn = adminSn;
		this.tkAdminRoleCode = tkAdminRoleCode;
		this.useYn = useYn;
	}
}
