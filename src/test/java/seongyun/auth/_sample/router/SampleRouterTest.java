package seongyun.auth._sample.router;

import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import seongyun.auth._sample.domain.entity.Sample;

@SpringBootTest
@ExtendWith({RestDocumentationExtension.class})
@ActiveProfiles(profiles = {"test"})
@Disabled
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
				.baseUrl("https://auth.seongyun.i234.me")
				.filter(documentationConfiguration(restDocumentation)
							.operationPreprocessors()
							.withResponseDefaults(prettyPrint()))
				.build();
	}
	
	@Test
	public void getSamples() {
		this.webTestClient.get().uri("/api/sample")//.accept(MediaType.APPLICATION_JSON)
		.exchange()//.expectStatus().isOk()
		.expectBody().consumeWith(
					document("samples",
						responseFields(
								fieldWithPath("code").description("??????"),
								fieldWithPath("message").description("?????????"),
								fieldWithPath("data[]").description("arr"),
								fieldWithPath("data[].sampleSn").description("??????SN"),
								fieldWithPath("data[].sampleNm").description("?????????")
								)
						)
					);
	}
	
	@Test
	public void getSample() {
		this.webTestClient.get().uri("/api/sample/{sn}", 901).exchange()
		.expectBody().consumeWith(
					document("sample",
						pathParameters(parameterWithName("sn").description("????????????SN")),
						responseFields(
								fieldWithPath("code").description("??????"),
								fieldWithPath("message").description("?????????"),
								fieldWithPath("data.sampleNm").description("?????????"),
								fieldWithPath("data.sampleSn").description("??????SN"))
					)
				);
	}
	
	@Test
	public void saveSample() {
		Sample s = new Sample();
		s.setSampleNm("sampleNAME");
		this.webTestClient.post().uri("/api/sample")
		.body(Mono.just(s), Sample.class)
		.exchange()
		.expectBody().consumeWith(
					document("save",
							requestFields(
									fieldWithPath("sampleSn").ignored(),
									fieldWithPath("sampleNm").description("?????????")),
							responseFields(fieldWithPath("code").description("??????"),
									fieldWithPath("message").description("?????????"),
									fieldWithPath("data.sampleNm").description("?????????"),
									fieldWithPath("data.sampleSn").description("??????SN"))
					)
					
				);
	}
	
	@Test
	public void updateSample() {
		Sample s = new Sample();
		s.setSampleSn(900L);
		s.setSampleNm("sampleUpdate");
		this.webTestClient.put().uri("/api/sample")
		.body(Mono.just(s), Sample.class)
		.exchange()
		.expectBody().consumeWith(
					document("update",
							requestFields(
									fieldWithPath("sampleSn").description("??????SN"),
									fieldWithPath("sampleNm").description("?????????")),
							responseFields(fieldWithPath("code").description("??????"),
									fieldWithPath("message").description("?????????"),
									fieldWithPath("data.sampleNm").description("?????????"),
									fieldWithPath("data.sampleSn").description("??????SN"))
					)
					
				);
	}
	
	@Test
	public void deleteSample() {
		this.webTestClient.delete().uri("/api/sample/{sn}", 897).exchange()
		.expectBody().consumeWith(
					document("delete",
						pathParameters(parameterWithName("sn").description("????????????SN")),
						responseFields(
								fieldWithPath("code").description("??????"),
								fieldWithPath("message").description("?????????"),
								fieldWithPath("data").description("_"))
					)
				);
	}
}
