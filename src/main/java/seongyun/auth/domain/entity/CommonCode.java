package seongyun.auth.domain.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import seongyun.auth.util.EntitiyUtil;

@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table("tb_common_code")
public class CommonCode /*extends Base*/{
	@Id@Column("code_sn")	private Long codeSn;
	@Column("code_id")		private String codeId;
	@Column("code_value")	private String codeValue;
	@Column("code_nm")		private String codeNm;
	@Column("code_desc")	private String codeDesc;
	@Column("code_group")	private String codeGroup;
	@Column("sort_ordr")	private Integer sortOrdr;
	@Column("use_yn")		private String useYn;
	@CreatedDate
	@Column("create_at")	private LocalDateTime createAt;
	@Column("create_by")	private Long createBy;
	@LastModifiedDate
	@Column("update_at")	private LocalDateTime updateAt;
	@Column("update_by")	private Long updateBy;
	
	@Builder
	public CommonCode(Long codeSn, String codeId, String codeValue, String codeNm, String codeDesc, String codeGroup) {
		this.codeSn = codeSn;
		this.codeId = codeId;
		this.codeValue = codeValue;
		this.codeNm = codeNm;
		this.codeDesc = codeDesc;
		this.codeGroup = codeGroup;
	}
	
//	@Builder
//	public CommonCode(
//			Object codeId, Object codeValue, Object codeNm,
//			Object codeDesc, Object codeGroup, Object sortOrdr,
//			Object useYn, Object createAt, Object updateAt, Object createBy, Object updateBy) {
//		super(useYn, updateAt, updateAt, createBy, updateBy);
//		this.codeId = EntitiyUtil.toString(codeId);
//		this.codeValue = EntitiyUtil.toString(codeValue);
//		this.codeNm = EntitiyUtil.toString(codeNm);
//		this.codeDesc = EntitiyUtil.toString(codeDesc);
//		this.codeGroup = EntitiyUtil.toString(codeGroup);
//		this.sortOrdr =  EntitiyUtil.toIntger(sortOrdr);
//	}
}
