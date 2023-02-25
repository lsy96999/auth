package seongyun.auth._sample.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import seongyun.auth._sample.domain.entity.Sample;

public interface SampleRepository extends R2dbcRepository<Sample, Long>{

}
