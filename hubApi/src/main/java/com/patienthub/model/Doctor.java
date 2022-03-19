package com.patienthub.model;

import java.util.ArrayList;

public class Doctor extends User {

    private String staffID;
    private Hospital currentHospital;
    private String spectiality;
    private ArrayList<Patient> patients;

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public Hospital getCurrentHospital() {
        return currentHospital;
    }

    public void setCurrentHospital(Hospital currentHospital) {
        this.currentHospital = currentHospital;
    }

    public String getSpectiality() {
        return spectiality;
    }

    public void setSpectiality(String spectiality) {
        this.spectiality = spectiality;
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public void setPatients(ArrayList<Patient> patients) {
        this.patients = patients;
    }

    // TODO: add method to get specific patien and get patient

}
