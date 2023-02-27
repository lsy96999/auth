package seongyun.auth.repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import seongyun.auth.domain.entity.CommonCode;
import seongyun.auth.domain.entity.TkAdmin;
import seongyun.auth.domain.entity.TkAdminRole;

@Repository
@RequiredArgsConstructor
public class TkAdminRepository {
	private final DatabaseClient databaseClient;
	private final ModelMapper mm;
	
	public Flux<TkAdmin> testr() {

		String sql = """
				select 
					tta.admin_sn as tta_admin_sn,
					tta.admin_nm as tta_admin_nm,
					tta.admin_mail as tta_admin_mail,
					tta.admin_pw as tta_admin_pw,
					tta.use_yn as tta_use_yn,
					tcc.code_id as tcc_code_id,
					tcc.code_value as tcc_code_value,
					tcc2.code_id as tcc2_code_id,
					tcc2.code_value as tcc2_code_value
				from tb_tk_admin tta
				inner join tb_tk_admin_role ttar on ttar.admin_sn = tta.admin_sn and ttar.use_yn = 'Y'
				left join tb_common_code tcc on tcc.code_id='TK_ADMIN_STTUS_CODE' and tcc.code_value = tta.tk_admin_sttus_code and tcc.use_yn = 'Y'
				left join tb_common_code tcc2 on tcc2.code_id='TK_ADMIN_ROLE_CODE'and tcc2.code_value = ttar.tk_admin_role_code and tcc2.use_yn = 'Y'
				where tta.use_yn = 'Y'
				order by tta.admin_sn;
				""";
		
		Flux<TkAdmin> a = databaseClient.sql(sql).fetch().all()
		.bufferUntilChanged(result -> result.get("tta_admin_sn"))
		.map(result->{
			List<TkAdminRole> roles = result.stream().map(r -> TkAdminRole.builder()
												.adminSttusCode(
																CommonCode.builder().codeId(String.valueOf(r.get("tcc2_code_id"))).codeValue(String.valueOf(r.get("tcc2_code_value"))).build()
																)
												.build()
								).collect(Collectors.toList());
			
			Map<String, Object> r  =result.get(0);
			TkAdmin tkadm = TkAdmin.builder()
					.adminMail(String.valueOf(r.get("tta_admin_mail")))
					.tkAdminRole(roles)
					.tkAdminSttusCode(CommonCode.builder().codeId(String.valueOf(r.get("tcc_code_id"))).codeValue(String.valueOf(r.get("tcc_code_value"))).build())
					.build();
			return tkadm;
		});
		return a;
	}
}
