package com.patienthub.model;

import javax.validation.constraints.NotNull;

public class HospitalAdmin extends User {

    private Hospital hospital;

    /** Creating the hospitalAdmin that is set in HospitalDAO 
     * HospitalAdmin is a type of User
     * Hospital must exist in order to create a hosptial admin
     */
    public HospitalAdmin(@NotNull(message = "first name is required") String firstName,
            @NotNull(message = "last name is required") String lastName,
            @NotNull(message = "contact number is required") String contactNum,
            @NotNull(message = "email is required") String email, @NotNull(message = "pps is required") String pps,
            String gender, Hospital hospital) {
        super(firstName, lastName, contactNum, email, pps, gender);
        this.hospital = hospital;

    }

    public HospitalAdmin() {
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

}
