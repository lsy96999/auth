/*
# HISTORY
## 20220225
> test를 위한 sample 데이터 인서트, 먼저 지우고 진행한다.
- delete @1
- insert @2

## 20220226
*/

--@1
delete from tb_sample;

--@2
insert into tb_sample(sample_nm) values ('hi');
insert into tb_sample(sample_nm) values ('hi2');
insert into tb_sample(sample_nm) values ('hi3');