/*
# HISTORY
## 20220225
> test를 위한 sample 테이블 및 시퀀스 생성
- [C, T] tb_sample @ts
- [C, S] seq_tb_sample 2ss

## 20220226
*/

--@ss
CREATE SEQUENCE IF NOT EXISTS SEQ_TB_SAMPLE;

--@ts
CREATE TABLE IF NOT EXISTS TB_SAMPLE(
    SAMPLE_SN int default nextval('SEQ_TB_SAMPLE'),
	SAMPLE_NM varchar(255)
);