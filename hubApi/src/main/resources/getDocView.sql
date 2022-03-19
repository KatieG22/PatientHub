drop view if exists getDoctor;
create view  getDoctor as
    select users.*, doctor.staffID, doctor.currentHospital from users 
    inner join doctor on doctor.pps = users.pps;