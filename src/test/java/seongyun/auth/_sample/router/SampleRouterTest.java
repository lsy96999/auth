package seongyun.auth._sample.router;

import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
//@SpringBootTest
@ExtendWith({RestDocumentationExtension.class})
@ActiveProfiles(profiles = {"test"})
public class SampleRouterTest {
	private static final String PW = "?";
	static {
		System.setProperty("jasypt.encryptor.password", PW);
	}
	private WebTestClient webTestClient;
	
	@BeforeEach
	void setUp(ApplicationContext applicationContext, RestDocumentationContextProvider restDocumentation) {
		this.webTestClient = WebTestClient.bindToApplicationContext(applicationContext)
				.configureClient()
				.filter(documentationConfiguration(restDocumentation))
				.build();
	}
	
	@Test
	public void getSamples() {
		FieldDescriptor[] sample = new FieldDescriptor[] { fieldWithPath("sampleSn").description("샘플SN"),
				fieldWithPath("sampleNm").description("샘플이름") };
		this.webTestClient.get().uri("/sample").exchange().expectStatus().isOk()
		.expectBody().consumeWith(document("sample",
				responseFields(fieldWithPath("[]").description("arr")).andWithPrefix("[].", sample)));
	}
}
