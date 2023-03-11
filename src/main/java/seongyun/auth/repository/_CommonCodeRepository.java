package seongyun.auth.repository;

import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import seongyun.auth.domain.entity.CommonCode;

@Repository
@RequiredArgsConstructor
public class _CommonCodeRepository {
	private final DatabaseClient databaseClient;
	
	public void getCommonCode(String codeId, String codeValue) {
		
	}
}
