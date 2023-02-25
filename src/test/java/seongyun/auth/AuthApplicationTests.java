package seongyun.auth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
//import lombok.extern.slf4j.Slf4j;

import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;

@SpringBootTest
//@Slf4j
@ExtendWith({RestDocumentationExtension.class})
class AuthApplicationTests {
	private WebTestClient webTestClient;
	
	@BeforeEach
	void setUp(ApplicationContext applicationContext, RestDocumentationContextProvider restDocumentation) {
		this.webTestClient = WebTestClient.bindToApplicationContext(applicationContext)
				.configureClient()
				.filter(documentationConfiguration(restDocumentation))
				.build();
	}
	
	@Test
	void sampleTest() throws Exception {
		this.webTestClient.get().uri("/sample").exchange().expectStatus().isOk()
				.expectBody().consumeWith(document("sample"));
	}
}
