delimiter &&
create procedure getAdminByEircode(in eirCode VARCHAR(10))
BEGIN
	select users.*, hadmin.eirCode from users 
	inner join hadmin on hadmin.pps = users.pps
	where hadmin.eirCode = eirCode;
END &&  
DELIMITER ; 
