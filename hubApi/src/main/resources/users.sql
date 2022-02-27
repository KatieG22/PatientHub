drop table if exists users;
create table users(
firstName varchar(15) not null,
lastName varchar(15) not null,
contactNo varchar(15) not null,
email varchar(25) not null,
pps varchar(15) not null primary key,
gender enum ('male','female','prefer not to say'),
role enum ('admin','staff','patient','doctor')
);

ALTER TABLE users ADD column isactive BOOLEAN DEFAULT false;

drop table if exists hAdmin;
create table hAdmin(
id int primary key,
pps varchar(15) not null,
eirCode VARCHAR(10) NOT NULL,
FOREIGN KEY (pps) REFERENCES users(pps),
FOREIGN KEY (eirCode) REFERENCES hospital(eirCode)
ON DELETE CASCADE  
)



drop table if exists hpatient;
create table hpatient(
pps varchar(15) not null,
patientId int primary key AUTO_INCREMENT,
eirCode varchar(15) not null,
registeredBy varchar(15) not null,
currentDoctor varchar(15) not null,
FOREIGN KEY (pps) REFERENCES users(pps),
FOREIGN KEY (eirCode) REFERENCES hospital(eirCode),
FOREIGN KEY (registeredBy) REFERENCES users(pps),
FOREIGN KEY (currentDoctor) REFERENCES users(pps)
on delete cascade
);