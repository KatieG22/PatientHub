create view  getAdmin as
    select users.*, hadmin.eirCode from users 
	inner join hadmin on hadmin.pps = users.pps;