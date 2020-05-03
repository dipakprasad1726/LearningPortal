create table courses(
course_id int not null auto_increment,
course_name varchar(100) not null,
base_price double not null,
course_status varchar(10) default 'ACTIVE',
created_at datetime default current_timestamp,
updated_at datetime default current_timestamp,
primary key (course_id)
);
alter table courses auto_increment=10000;
alter table courses add column (course_description varchar(500) default 'This going to be an interestin course.');
create table country_component_value(
ccvid int not null auto_increment,
country_code varchar(10) not null,
tax_value double default null,
currency_conversion_value double default null,
primary key (ccvid)
);
alter table country_component_value auto_increment=1000;
show tables;
insert into courses(course_name,base_price) values('Data Structures',5000.0);
insert into courses(course_name,base_price,course_description) values('Algorithms',7000.0,'Algorithm refers to a set of rules/instructions that step-by-step define how a work is to be executed upon in order to get the expected results.');
insert into country_component_value(country_code,tax_value) 
values('IN',15);
insert into country_component_value(country_code,tax_value,currency_conversion_value) 
values('US',25,70);
insert into country_component_value(country_code,tax_value,currency_conversion_value) 
values('OTHERS',15,10);