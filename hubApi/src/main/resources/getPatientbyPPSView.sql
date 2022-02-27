drop view if exists getPatientbyPPS;
create view  getPatientbyPPS as
	select users.* from users 
	inner join hpatient on hpatient.pps = users.pps;
    
select * from getPatientbyPPS where pps = 5432;


drop view if exists getPatientbyPatientId;
create view  getPatientbyPatientId as
	select users.* , hpatient.patientId from users 
	inner join hpatient on hpatient.pps = users.pps;
    
select * from getPatientbyPatientId where patientId = 1;