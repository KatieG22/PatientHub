
drop table if exists hpatient;
create table hpatient(
pps varchar(15) not null,
patientId int primary key AUTO_INCREMENT,
eirCode varchar(15) not null,
registeredByPps varchar(15) not null,
currentDoctorPps varchar(15) not null,
constraint patients foreign key (pps) REFERENCES users(pps),
FOREIGN KEY (eirCode) REFERENCES hospital(eirCode)
on delete cascade
);

insert into hpatient(pps, eircode, registeredByPps, currentDoctorPps)
values (5432, 'x90 gb9', 145, 145 );