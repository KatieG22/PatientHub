package com.patienthub.model;

public class Doctor extends User {

    /** Instansiating a doctor object that is set in UserDAO
     * A doctor is a  type of user defined in User.java
      */
    private String staffID;
    private Hospital currentHospital;
    private String spectiality;

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

}
