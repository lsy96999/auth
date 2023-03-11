package seongyun.auth.domain.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import seongyun.auth.util.EntitiyUtil;

@Getter
@Table("tb_tk_admin")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TkAdmin /*extends Base*/ implements UserDetails{
	private static final long serialVersionUID = 1L;
	
	@Id@Column("admin_sn")	private Long adminSn;
	@Column("admin_nm")		private String adminNm;
	@Column("admin_mail")	private String adminMail;
	@Column("admin_pw")		private String adminPw;
	@Column("tk_admin_sttus_code") private String tkAdminSttusCode;
	
	//TODO: removesetter
	@Setter
	@Column("tk_admin_role") private Long tkAdminRole;
	@Column("use_yn")		private String useYn;
	@CreatedDate
	@Column("create_at")	private LocalDateTime createAt;
	@CreatedBy
	@Column("create_by")	private Long createBy;
	@LastModifiedDate
	@Column("update_at")	private LocalDateTime updateAt;
	@LastModifiedBy
	@Column("update_by")	private Long updateBy;
	
	@Transient
	@Setter
	private List<TkAdminRole> roles = new ArrayList<>();
	
	@Builder
	public TkAdmin(Long adminSn, String adminNm, String adminMail, String adminPw,
			Long tkAdminRole, String tkAdminSttusCode,
			String useYn) {
		this.adminSn = adminSn;
		this.adminNm = adminNm;
		this.adminMail = adminMail;
		this.adminPw = adminPw;
		this.tkAdminRole = tkAdminRole;
		this.tkAdminSttusCode = tkAdminSttusCode;
		this.useYn = useYn;
	}
	
	//---
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> roleArr = new ArrayList<>();
		for (TkAdminRole e: this.roles) {
			roleArr.add(new SimpleGrantedAuthority(e.getTkAdminRoleCode()));
		}
//		roleArr.add(new SimpleGrantedAuthority(t));
		
		return roleArr;
	}
	@Override
	public String getPassword() {
		return this.adminPw;
	}
	@Override
	public String getUsername() {
		return this.adminMail;
	}
	@Override
	public boolean isAccountNonExpired() {
		return false;
	}
	@Override
	public boolean isAccountNonLocked() {
		return false;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}
	@Override
	public boolean isEnabled() {
		return true;
	};
}
