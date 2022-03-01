
drop table if exists hpatient;
create table hpatient(
pps varchar(15) not null,
patientId int primary key AUTO_INCREMENT,
eirCode varchar(15) not null,
registeredBy varchar(15) not null,
currentDoctor varchar(15) not null,
CONSTRAINT patientUser FOREIGN KEY (pps) REFERENCES users(pps) ON DELETE CASCADE,
CONSTRAINT patientHospital FOREIGN  KEY (eirCode) REFERENCES hospital(eirCode) ON DELETE CASCADE,
CONSTRAINT patientRegisteredBy FOREIGN KEY (registeredBy) REFERENCES users(pps) ON DELETE CASCADE,
CONSTRAINT patientCurrentDoctor FOREIGN KEY (currentDoctor) REFERENCES users(pps) ON DELETE CASCADE
);

