delete table if exists logs;

create table if not exists logs (
id int auto_increment primary key,
expression varchar(512) not null,
result varchar(100) not null,
calculation_time int not null,
created_at timestamp default current_timestamp
) ENGINE=INNODB;
