package seongyun.auth._sample.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import seongyun.auth._sample.domain.entity.Sample;
import seongyun.auth._sample.repository.SampleRepository;

@Service
@RequiredArgsConstructor
public class SampleService {
	private final SampleRepository sampleRepository;
	
	public Flux<Sample> getSamples(){
		return sampleRepository.findAll();
	}
	
	public Mono<Sample> getSample(Long sn){
		return sampleRepository.findById(sn);
	}
	
	public Mono<Sample> saveSample(Sample sample) {
		return sampleRepository.save(sample);
	}
	
	public Mono<Void> deleteSample(Long sn) {
		return sampleRepository.deleteById(sn);
	}
}
