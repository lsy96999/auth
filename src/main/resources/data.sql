/*
# HISTORY
## 20220225
> test를 위한 sample 데이터 인서트, 먼저 지우고 진행한다.
- delete @1
- insert @2

## 20220226
> 공통코드 입력, 어드민 추가
- delete @3 공통코드
- insert @4 공통코드
- delete @5 어드민
- insert @6 어드민
- delete @7 어드민 권한
- insert @8 어드민 권한
*/
---------
--@1
delete from tb_sample;
--@2
insert into tb_sample(sample_nm) values ('hi');
insert into tb_sample(sample_nm) values ('hi2');
insert into tb_sample(sample_nm) values ('hi3');
----------
--@3
delete from tb_common_code;
--@4
insert into tb_common_code(code_id, code_value, code_nm, use_yn, frst_regist_dt, last_mdfied_dt, frst_regist_user_sn, last_mdfied_user_sn) values ('TK_ADMIN_STTUS_CODE', 'LIVE', '가입', 'Y', now(), now(), 1, 1);
insert into tb_common_code(code_id, code_value, code_nm, use_yn, frst_regist_dt, last_mdfied_dt, frst_regist_user_sn, last_mdfied_user_sn) values ('TK_ADMIN_STTUS_CODE', 'DEAD', '탈퇴', 'Y', now(), now(), 1, 1);
insert into tb_common_code(code_id, code_value, code_nm, use_yn, frst_regist_dt, last_mdfied_dt, frst_regist_user_sn, last_mdfied_user_sn) values ('TK_ADMIN_ROLE_CODE', 'ROLE_SUPER', '최고권한', 'Y', now(), now(), 1, 1);
--@5
drop table tb_tk_admin;
--@6
insert into tb_tk_admin (admin_sn, admin_nm, admin_mail, admin_pw, tk_admin_sttus_code, use_yn, frst_regist_dt, last_mdfied_dt, frst_regist_user_sn, last_mdfied_user_sn) values(1, 'ADMIN', 'ADMIN', 'ADMIN', 'LIVE', 'Y', now(), now(), 1, 1);
--@7
drop table tb_tk_admin_role;
--@8
insert into tb_tk_admin_role(admin_sn, tk_admin_role_code, use_yn, frst_regist_dt, last_mdfied_dt, frst_regist_user_sn, last_mdfied_user_sn) values(1, 'ROLE_SUPER', 'Y', now(), now(), 1, 1);
----------