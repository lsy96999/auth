package seongyun.auth.domain.entity;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import seongyun.auth.util.EntitiyUtil;

@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Base {
	private String useYn;
	private LocalDateTime createAt;
	private LocalDateTime updateAt;
	private Long createBy;
	private Long updateBy;
//	public Base(Object useYn, Object createAt, Object updateAt, Object createBy, Object updateBy) {
//		this.useYn = 	EntitiyUtil.toString(useYn);
//		this.createAt = EntitiyUtil.toLocalDateTime(createAt);
//		this.updateAt = EntitiyUtil.toLocalDateTime(updateAt);
//		this.createBy = EntitiyUtil.toLong(createBy);
//		this.updateBy = EntitiyUtil.toLong(updateBy);
//	}
}
