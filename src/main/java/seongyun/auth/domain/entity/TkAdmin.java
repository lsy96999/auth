package seongyun.auth.domain.entity;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
@Getter
@Setter
@SuperBuilder
public class TkAdmin extends Base{
	private Long adminSn;
	private String adminNm;
	private String adminMail;
	private String adminPw;
	private List<TkAdminRole> tkAdminRole;
	private CommonCode tkAdminSttusCode;;
}
