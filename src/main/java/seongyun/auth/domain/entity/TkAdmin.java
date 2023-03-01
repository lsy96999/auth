package seongyun.auth.domain.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import seongyun.auth.util.EntitiyUtil;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TkAdmin extends Base implements UserDetails{
	private static final long serialVersionUID = 1L;
	
	private Long adminSn;
	private String adminNm;
	private String adminMail;
	private String adminPw;
	private List<TkAdminRole> tkAdminRole;
	private CommonCode tkAdminSttusCode;
	
	@Builder
	public TkAdmin(
			Object adminSn, Object adminNm, Object adminMail, Object adminPw,
			Object tkAdminRole, Object tkAdminSttusCode,
			Object useYn, Object createAt, Object updateAt, Object createBy, Object updateBy) {
		super(useYn, createAt, updateAt, createBy, updateBy);
		this.adminSn = EntitiyUtil.toLong(adminSn);
		this.adminNm = EntitiyUtil.toString(adminNm);
		this.adminMail = EntitiyUtil.toString(adminMail);
		this.adminPw = EntitiyUtil.toString(adminPw);
		this.tkAdminRole = EntitiyUtil.<TkAdminRole>toList(tkAdminRole);
		this.tkAdminSttusCode = (CommonCode) tkAdminSttusCode;
	}
	
	//---
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> roleArr = new ArrayList<>();
		for (TkAdminRole e: this.tkAdminRole) {
			roleArr.add(new SimpleGrantedAuthority(e.getAdminSttusCode().getCodeValue()));
		}
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
