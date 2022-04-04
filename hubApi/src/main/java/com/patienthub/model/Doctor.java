package com.patienthub.model;

import java.util.ArrayList;

public class Doctor extends User {

    private long staffID;
    private Hospital currentHospital;
    private String specialization;
    private ArrayList<Patient> patients;

    public long getStaffID() {
        return staffID;
    }

    public void setStaffID(long staffID) {
        this.staffID = staffID;
    }

    public Hospital getCurrentHospital() {
        return currentHospital;
    }

    public void setCurrentHospital(Hospital currentHospital) {
        this.currentHospital = currentHospital;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public void setPatients(ArrayList<Patient> patients) {
        this.patients = patients;
    }

    // TODO: add method to get specific patien and get patient

}
