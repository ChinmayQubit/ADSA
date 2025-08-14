Create database org;
use org;

create table Customer(  
cust_id int primary key,
phone_no Bigint
);

create table Customer_add(
Street varchar(20),
state varchar(20),
city varchar(20),
pin varchar(10),
name varchar(255),
cust_id int,
foreign key(cust_id) references Customer(cust_id)
);

create table Books(
ISBN Bigint primary key,
Author varchar(255),
price bigint ,
publisher varchar(255) 
);

create table Website(
web_id int primary key,
email varchar(255),
Rating varchar(255),
url varchar(255)
);

create table Admin(
Name varchar(255),
Admin_id int primary key,
Admin_role varchar(255),
web_id int,
foreign key(web_id) references Website(web_id)
);
create table courier(
courier_id int primary key,
status varchar(255),
contact Bigint 
);

create table order_books(
order_id int primary key,
payment int ,
cust_id int,
ISBN Bigint,
courier_id int,
foreign key(cust_id) references Customer(cust_id),
foreign key(ISBN) references Books(ISBN),
foreign key(courier_id) references courier(courier_id)
);
