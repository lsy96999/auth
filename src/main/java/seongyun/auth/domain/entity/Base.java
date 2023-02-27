package seongyun.auth.domain.entity;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class Base {
	private String useYn;
	private LocalDateTime createAt;
	private LocalDateTime updateAt;
	private Long createBy;
	private Long updateBy;
}
