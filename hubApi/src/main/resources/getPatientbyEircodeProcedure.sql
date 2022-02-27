drop procedure if exists getPatientByEircode;
delimiter &&
create procedure getPatientByEircode(in eirCode VARCHAR(10))
BEGIN
	select users.*, hpatient.eirCode from users 
	inner join hpatient on hpatient.pps = users.pps
	where hpatient.eirCode = eirCode;
END &&  
DELIMITER ; 

call getPatientByEircode('x90 gb9');