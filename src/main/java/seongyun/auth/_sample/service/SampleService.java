package seongyun.auth._sample.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import seongyun.auth._sample.domain.entity.Sample;
import seongyun.auth._sample.repository.SampleRepository;

@Service
@RequiredArgsConstructor
public class SampleService {
	private final SampleRepository sampleRepository;
	
	public Flux<Sample> getAllSamples(){
		return sampleRepository.findAll();
	}
}
