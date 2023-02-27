/*
# HISTORY
## 20220225
> test를 위한 sample 테이블 및 시퀀스 생성
- [C, T] tb_sample @ts
- [C, S] seq_tb_sample @ss

## 20220227
> 공통 코드, 토큰 어드민, 토큰 어드민 권한 추가
- [C, T] tb_tk_admin @tta
- [C, S] seq_tb_tk_admin @sta
- [C, T] tb_common_code @tcc
- [C, T] tb_tk_admin_role @ttar
*/
----------
--@ss
CREATE SEQUENCE IF NOT EXISTS SEQ_TB_SAMPLE;
--@ts
CREATE TABLE IF NOT EXISTS TB_SAMPLE(
    SAMPLE_SN int default nextval('SEQ_TB_SAMPLE'),
	SAMPLE_NM varchar(255)
);
----------
--@tta
create table IF NOT EXISTS tb_tk_admin(
	admin_sn numeric(10) primary key default nextval('SEQ_TB_TK_ADMIN'),
	admin_nm varchar(100),
	admin_mail varchar(100) unique,
	admin_pw varchar(100),
	tk_admin_sttus_code varchar(100),
	use_yn char(1),
	frst_regist_dt timestamp,
	last_mdfied_dt timestamp,
	frst_regist_user_sn numeric(10),
	last_mdfied_user_sn numeric(10)
);
--@sta
create sequence IF NOT EXISTS seq_tb_tk_admin;
--@tcc
create table tb_common_code(
	code_id varchar(100),
	code_value varchar(100),
	code_nm varchar(100),
	code_desc varchar(200),
	code_group varchar(50),
	sort_ordr numeric(10),
	use_yn char(1),
	frst_regist_dt timestamp,
	last_mdfied_dt timestamp,
	frst_regist_user_sn numeric(10),
	last_mdfied_user_sn numeric(10),
	primary key (code_id, code_value)
);
--@ttar
create table IF NOT EXISTS tb_tk_admin_role(
	admin_sn numeric(10),
	tk_admin_role_code varchar(100),
	use_yn char(1),
	frst_regist_dt timestamp,
	last_mdfied_dt timestamp,
	frst_regist_user_sn numeric(10),
	last_mdfied_user_sn numeric(10),
	primary key (admin_sn, tk_admin_role_code)
);
----------