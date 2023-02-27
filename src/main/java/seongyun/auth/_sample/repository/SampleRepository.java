package seongyun.auth._sample.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Flux;
import seongyun.auth._sample.domain.entity.Sample;

public interface SampleRepository extends R2dbcRepository<Sample, Long>{
	Flux<Sample> findAllBy(Pageable pageable);
}
