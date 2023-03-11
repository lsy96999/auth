package seongyun.auth.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Mono;
import seongyun.auth.domain.entity.TkAdmin;

public interface TkAdminRepository extends R2dbcRepository<TkAdmin, Long>{
	Mono<TkAdmin> findByAdminMail(String adminMail);
	
	@Query("SELECT nextval('seq_tb_tk_admin') ")
	Mono<Long> nextSequence();
}
