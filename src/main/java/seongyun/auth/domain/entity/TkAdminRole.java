package seongyun.auth.domain.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public class TkAdminRole {
	private Long adminSn;
	private TkAdmin admin;
	private CommonCode adminSttusCode; 
}
