create table contact(
	id int primary key,
	firstname varchar(255),
	lastname varchar(255),
	birth date
);

create sequence contact_seq;