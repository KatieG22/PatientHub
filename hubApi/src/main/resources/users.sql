drop table if exists users;
create table users(
firstName varchar(15) not null,
lastName varchar(15) not null,
contactNo varchar(15) not null,
email varchar(25) not null,
pps varchar(15) not null primary key,
gender enum ('male','female','prefer not to say'),
role enum ('hAdmin','hStaff','hPatient')
);

drop table if exists hAdmin;
create table hAdmin(
id int primary key,
pps varchar(15) not null,
eirCode VARCHAR(10) NOT NULL,
 constraint hospAdmin FOREIGN KEY (pps) REFERENCES users(pps),
  FOREIGN KEY (eirCode) REFERENCES hospital(eirCode)
  on delete cascade
);

drop table if exists hStaff;
create table hStaff(
pps varchar(15) not null,
staffId varchar (15) not null,
eirCode VARCHAR(10) NOT NULL,
speciality varchar(15) not null,
constraint hospStaff FOREIGN KEY (pps) REFERENCES users(pps),
FOREIGN KEY (eirCode) REFERENCES hospital(eirCode)
on delete cascade
);

drop table if exists hPatient;
create table hPatient(
pps varchar(15) not null,
patientId varchar(15) not null,
eirCode varchar(10) not null,
constraint hospPatient FOREIGN KEY (pps) REFERENCES users(pps),
FOREIGN KEY (eirCode) REFERENCES hospital(eirCode)
on delete cascade
);