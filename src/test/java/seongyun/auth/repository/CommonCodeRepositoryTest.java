package seongyun.auth.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommonCodeRepositoryTest {
	private static final String PW = "?";
	static {
		System.setProperty("jasypt.encryptor.password", PW);
	}
	@Autowired private CommonCodeRepository repo;
	
	@Test
	void test() {
	}
}
