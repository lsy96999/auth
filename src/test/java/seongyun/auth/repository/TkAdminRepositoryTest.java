package seongyun.auth.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import lombok.extern.slf4j.Slf4j;
import reactor.test.StepVerifier;

@SpringBootTest
@ActiveProfiles(profiles = {"test"})
//@Transactional
@Slf4j
public class TkAdminRepositoryTest {
	private static final String PW = "?";
	static {
		System.setProperty("jasypt.encryptor.password", PW);
	}
	@Autowired private TkAdminRepository repo;
	
	
	@Test
	void testsample() {
		StepVerifier.create(repo.getTkAdminBySn(1L))
		.thenConsumeWhile(a -> a.getAdminMail().equals("ADMIN"))
		.verifyComplete();
	}
}
