drop table entity;
create table entry(id bigint not null auto_increment primary key, title varchar(256) not null, body varchar(4096) not null, created_at timestamp not null default current_timestamp);
