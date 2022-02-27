
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

