package seongyun.auth.repository;


import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Flux;
import seongyun.auth.domain.entity.TkAdminRole;


public interface TkAdminRoleRepository extends R2dbcRepository<TkAdminRole, Long>{
	Flux<TkAdminRole> findByAdminSn(Long adminSn);
}
