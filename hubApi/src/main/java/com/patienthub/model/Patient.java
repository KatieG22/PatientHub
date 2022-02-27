package com.patienthub.model;

import javax.validation.constraints.NotNull;

public class Patient extends User {

    private int patientID;
    private Hospital hospital;
    private Doctor registeredBy;
    private Doctor currentDoctor;

    public Patient() {
        super();
    }

    public Patient(@NotNull(message = "first name is required") String firstName,
            @NotNull(message = "last name is required") String lastName,
            @NotNull(message = "contact number is required") String contactNum,
            @NotNull(message = "email is required") String email, @NotNull(message = "pps is required") String pps,
            String gender, String role, boolean isactive, int patientID, Hospital hospital, Doctor registeredBy,
            Doctor currentDoctor) {
        super(firstName, lastName, contactNum, email, pps, gender, role, isactive);
        this.patientID = patientID;
        this.hospital = hospital;
        this.registeredBy = registeredBy;
        this.currentDoctor = currentDoctor;
    }

    public int getpatientID() {
        return patientID;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public Doctor getRegisteredBy() {
        return registeredBy;
    }

    public void setRegisteredBy(Doctor registeredBy) {
        this.registeredBy = registeredBy;
    }

    public Doctor getCurrentDoctor() {
        return currentDoctor;
    }

    public void setCurrentDoctor(Doctor currentDoctor) {
        this.currentDoctor = currentDoctor;
    }

}
