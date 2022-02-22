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
 FOREIGN KEY (pps) REFERENCES users(pps),
  FOREIGN KEY (eirCode) REFERENCES hospital(eirCode)
)