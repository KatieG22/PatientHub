drop table if exists users;
create table users(
firstName varchar(15) not null,
lastName varchar(15) not null,
contactNo varchar(15) not null,
email varchar(25) not null,
pps varchar(15) not null primary key,
gender enum ('male','female','not specified') DEFAULT "not specified",
role enum ('admin','staff','patient','doctor')
);

ALTER TABLE users ADD column isactive BOOLEAN DEFAULT false;
ALTER TABLE users ADD column password VARCHAR(256) NOT NULL;
ALTER TABLE users MODIFY column gender enum ('male','female','not specified') DEFAULT "not specified";
ALTER TABLE users MODIFY column role varchar(15);
ALTER TABLE users MODIFY column gender varchar(15);

drop table if exists hAdmin;
create table hAdmin(
id int primary key AUTO_INCREMENT,
pps varchar(15) not null,
hospital VARCHAR(10) NOT NULL,
CONSTRAINT hospitalAdmin FOREIGN KEY (pps) REFERENCES users(pps)  ON DELETE CASCADE,
CONSTRAINT adminHospital FOREIGN KEY (hospital) REFERENCES hospital(eirCode) ON DELETE CASCADE  
)

drop table if exists doctor;
create table doctor(
    pps varchar(15) not null,
    doctorID int primary key AUTO_INCREMENT,
    currentHospital VARCHAR(10) NOT NULL,
    CONSTRAINT hospital FOREIGN KEY (currentHospital) REFERENCES hospital(eirCode) ON DELETE CASCADE,
    CONSTRAINT hospitalDoctor FOREIGN KEY (pps) REFERENCES users(pps) ON DELETE CASCADE

)