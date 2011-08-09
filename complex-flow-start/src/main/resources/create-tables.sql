create table contact(
	id int auto_increment primary key,
	firstname varchar(255),
	lastname varchar(255),
	birth date
);

create table tracking(
	filename varchar(1024),
	reason varchar(255)
);
