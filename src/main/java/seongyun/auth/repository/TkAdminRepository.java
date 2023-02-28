package seongyun.auth.repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import seongyun.auth.domain.entity.CommonCode;
import seongyun.auth.domain.entity.TkAdmin;
import seongyun.auth.domain.entity.TkAdminRole;

@Repository
@RequiredArgsConstructor
public class TkAdminRepository {
	private final DatabaseClient databaseClient;
	public Mono<TkAdmin> getTkAdminBySn(Long adminSn) {
		String sql = """
					SELECT 
					--
					tta.admin_sn AS tta_admin_sn,
					tta.admin_nm AS tta_admin_nm,
					tta.admin_mail AS tta_admin_mail,
					tta.admin_pw AS tta_admin_pw,
					tta.use_yn AS tta_use_yn,
					tta.create_at AS tta_create_at,
					tta.update_at AS tta_update_at,
					tta.create_by AS tta_create_by,
					tta.update_by AS tta_update_by,
					--
					ttar.admin_sn AS ttar_admin_sn,
					ttar.use_yn AS ttar_use_yn,
					ttar.create_at AS ttar_create_at,
					ttar.update_at AS ttar_update_at,
					ttar.create_by AS ttar_create_by,
					ttar.update_by AS ttar_update_by,
					--
					tcc.code_id AS tcc_code_id,
					tcc.code_value AS tcc_code_value,
					tcc.code_nm AS tcc_code_nm,
					tcc.code_desc AS tcc_code_desc,
					tcc.code_group AS tcc_code_group,
					tcc.sort_ordr AS tcc_sort_ordr,
					tcc.use_yn AS tcc_use_yn,
					tcc.create_at AS tcc_create_at,
					tcc.update_at AS tcc_update_at,
					tcc.create_by AS tcc_create_by,
					tcc.update_by AS tcc_update_by,
					--
					tcc2.code_id AS tcc2_code_id,
					tcc2.code_value AS tcc2_code_value,
					tcc2.code_nm AS tcc2_code_nm,
					tcc2.code_desc AS tcc2_code_desc,
					tcc2.code_group AS tcc2_code_group,
					tcc2.sort_ordr AS tcc2_sort_ordr,
					tcc2.use_yn AS tcc2_use_yn,
					tcc2.create_at AS tcc2_create_at,
					tcc2.update_at AS tcc2_update_at,
					tcc2.create_by AS tcc2_create_by,
					tcc2.update_by AS tcc2_update_by
				FROM tb_tk_admin tta
					INNER JOIN tb_tk_admin_role ttar
						ON ttar.admin_sn = tta.admin_sn
					LEFT JOIN tb_common_code tcc
						ON tcc.code_id = 'TK_ADMIN_STTUS_CODE'
						AND tcc.code_value = tta.tk_admin_sttus_code
					LEFT JOIN tb_common_code tcc2
						ON tcc2.code_id = 'TK_ADMIN_ROLE_CODE'
						AND tcc2.code_value = ttar.tk_admin_role_code
				WHERE 1=1
					AND tta.admin_sn = :adminSn
				""";
		
		Flux<TkAdmin> a = databaseClient.sql(sql).bind("adminSn", adminSn).fetch().all()
		.bufferUntilChanged(result -> result.get("tta_admin_sn"))
		.map(result->{
			List<TkAdminRole> roles = result.stream().map(r -> TkAdminRole.builder()
																	.useYn(String.valueOf(r.get("ttar_use_yn")))
																	.updateBy(Long.parseLong(String.valueOf(r.get("ttar_update_by"))))
																	.createBy(Long.parseLong(String.valueOf(r.get("ttar_create_by"))))
																	.updateAt(toLocalDateTime(String.valueOf(r.get("ttar_update_at"))))
																	.createAt(toLocalDateTime(String.valueOf(r.get("ttar_create_at"))))
																	.adminSn(Long.parseLong(String.valueOf(r.get("ttar_admin_sn"))))
																	.adminSttusCode(
																					CommonCode.builder()
																						.sortOrdr(Integer.parseInt(String.valueOf(r.get("tcc2_sort_ordr"))))
																						.updateBy(Long.parseLong(String.valueOf(r.get("tcc2_update_by"))))
																						.createBy(Long.parseLong(String.valueOf(r.get("tcc2_create_by"))))
																						.updateAt(toLocalDateTime(String.valueOf(r.get("tcc2_update_at"))))
																						.createAt(toLocalDateTime(String.valueOf(r.get("tcc2_create_at"))))
																						.useYn(String.valueOf(r.get("tcc2_use_yn")))
																						.codeNm(String.valueOf(r.get("tcc2_code_nm")))
																						.codeGroup(String.valueOf(r.get("tcc2_code_group")))
																						.codeDesc(String.valueOf(r.get("tcc2_code_desc")))
																						.codeId(String.valueOf(r.get("tcc2_code_id")))
																						.codeValue(String.valueOf(r.get("tcc2_code_value")))
																					.build()
																	)
																	.build()
														).collect(Collectors.toList());
			Map<String, Object> r  =result.get(0);
			TkAdmin tkadm = TkAdmin.builder()
									.updateBy(Long.parseLong(String.valueOf(r.get("tta_update_by"))))
									.createBy(Long.parseLong(String.valueOf(r.get("tta_create_by"))))
									.updateAt(toLocalDateTime(String.valueOf(r.get("tta_update_at"))))
									.createAt(toLocalDateTime(String.valueOf(r.get("tta_create_at"))))
									.useYn(String.valueOf(r.get("tta_use_yn")))
									.adminSn(Long.parseLong(String.valueOf(r.get("tta_admin_sn"))))
									.adminNm(String.valueOf(r.get("tta_admin_nm")))
									.adminPw(String.valueOf(r.get("tta_admin_pw")))
									.adminMail(String.valueOf(r.get("tta_admin_mail")))
									.tkAdminRole(roles)
									.tkAdminSttusCode(
													CommonCode.builder()
														.sortOrdr(Integer.parseInt(String.valueOf(r.get("tcc_sort_ordr"))))
														.updateBy(Long.parseLong(String.valueOf(r.get("tcc_update_by"))))
														.createBy(Long.parseLong(String.valueOf(r.get("tcc_create_by"))))
														.updateAt(toLocalDateTime(String.valueOf(r.get("tcc_update_at"))))
														.createAt(toLocalDateTime(String.valueOf(r.get("tcc_create_at"))))
														.useYn(String.valueOf(r.get("tcc_use_yn")))
														.codeNm(String.valueOf(r.get("tcc_code_nm")))
														.codeGroup(String.valueOf(r.get("tcc_code_group")))
														.codeDesc(String.valueOf(r.get("tcc_code_desc")))
														.codeId(String.valueOf(r.get("tcc_code_id")))
														.codeValue(String.valueOf(r.get("tcc_code_value")))
													.build()
													)
									.build();
			return tkadm;
		});
		
		return a.next();
	}
	
	private LocalDateTime toLocalDateTime(String str) {
		String newstr = str.replace("T", " ");
		return Timestamp.valueOf(newstr).toLocalDateTime();
	}
}
