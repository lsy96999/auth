package seongyun.auth.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import seongyun.auth.domain.entity.CommonCode;

public interface CommonCodeRepository extends R2dbcRepository<CommonCode, Long>{
	Flux<CommonCode> findByCodeId(String codeId);
	Mono<CommonCode> findByCodeIdAndCodeValue(String codeId, String codeValue);
}