package seongyun.auth.domain.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public class TkAdminRole extends Base{
	private Long adminSn;
	private CommonCode adminSttusCode; 
}
