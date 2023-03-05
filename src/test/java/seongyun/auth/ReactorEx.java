package seongyun.auth;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class ReactorEx {
	
	class PublisherImpl implements Publisher<String> {

		@Override
		public void subscribe(Subscriber<? super String> s) {
			Queue<String> q = new LinkedList<>();
			String[] atr = new String[] {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k"};
			Arrays.asList(atr).stream().forEach(q::add);
			s.onSubscribe(new Subscription() {
				
				@Override
				public void request(long n) {
					log.debug("request: {}", n);
					for (int i = 0; i < n; i ++) {
						if(q.isEmpty()) {
							s.onComplete();
							return;
						}
						if(q.size() == 2) {
							s.onError(new RuntimeException("hi"));
							return;
						}
						s.onNext(q.poll());
					}
				}
				
				@Override
				public void cancel() {
					log.debug("publish cancel");
				}
			});
		}
		
	}
	
	class SubscriberImpl implements Subscriber<String> {
		private Subscription subscription;
		private long requestSize = 2;
		private List<String> buffer = new ArrayList<String>();
		@Override
		public void onSubscribe(Subscription s) {
			subscription = s;
			subscription.request(requestSize);
		}
		@Override
		public void onNext(String t) {
			log.debug("onNext - {}", t);
			buffer.add(t);
			if(buffer.size() == requestSize) {
				buffer.clear();
				subscription.request(requestSize);
			}
		}
		@Override
		public void onError(Throwable t) {
			log.debug("error: {}", t.getMessage());
		}
		@Override
		public void onComplete() {
			log.debug("subsript complete");
		}
		
	}
	
	@Test
	public void reactive() {
		Publisher<String> pub = new PublisherImpl();
		pub.subscribe(new SubscriberImpl());
	}
	
	
//	@Test
	public void test1() {
		List<Integer> elements = new ArrayList<>();
		Flux.just(1,2,3,4)
		.log()
		.map(a -> a*2)
		.subscribe( i -> {
			System.out.println(i);
		});
		
		Flux.just(Arrays.asList(1,2,3), Arrays.asList(4,5,6), Arrays.asList(7,8,9))
		.log()
		.flatMap(a->{
			
			return Flux.just(a);
		})
		.map(a->{
			return Flux.just(a);
		})
//		.map(a ->{
//			return a * 2;
//		})
		.subscribe( i -> {
			System.out.println(i);
		});
	}
}
