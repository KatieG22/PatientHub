package com.patienthub.model;

import javax.validation.constraints.NotNull;

public class HospitalAdmin extends User {

    private Hospital hospital;

    public HospitalAdmin(@NotNull(message = "first name is required") String firstName,
            @NotNull(message = "last name is required") String lastName,
            @NotNull(message = "contact number is required") String contactNum,
            @NotNull(message = "email is required") String email, @NotNull(message = "pps is required") String pps,
            String gender, String role, boolean isactive, Hospital hospital) {
        super(firstName, lastName, contactNum, email, pps, gender, role, isactive);
        this.hospital = hospital;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

}
