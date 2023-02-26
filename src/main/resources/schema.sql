create sequence IF NOT EXISTS seq_tb_sample;
create table IF NOT EXISTS tb_sample(
    sample_sn int default nextval('seq_tb_sample'),
	sample_nm varchar(255)
);