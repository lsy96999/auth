package seongyun.auth._sample.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Table("tb_sample")
@Data
public class Sample {
	@Id
	@Column("sample_sn")
	private Long sampleSn;
	
	@Column("sample_nm")
	private String sampleNm;
}
