create database if not exists patienthub;
use patienthub;


drop table if exists hospital;
create table hospital(
hName VARCHAR(25) NOT NULL,
contactNum VARCHAR(15) NOT NULL,
contactEmail VARCHAR(30) NOT NULL,
eirCode VARCHAR(10) NOT NULL,
addressLine VARCHAR(25) NOT NULL,
county VARCHAR (15) NOT NULL,
city VARCHAR (15) NOT NULL,
isHq BOOLEAN default false,
hasAdmin BOOLEAN default false,
isVerified BOOLEAN default false,
primary key(eirCode)
);



