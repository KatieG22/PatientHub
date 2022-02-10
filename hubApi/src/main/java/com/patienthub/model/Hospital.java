package com.patienthub.model;

import javax.json.bind.annotation.JsonbProperty;
import javax.validation.constraints.NotNull;

public class Hospital {

    @JsonbProperty("hospitalName")
    @NotNull(message = "an hospital must have a name")
    private String name;

    @NotNull(message = "please provide a contact number")
    private String contactNum;

    @NotNull(message = "please provide a contact email")
    private String contactEmail;

    @NotNull(message = "please provide an eir code")
    private String eirCode;

    @NotNull(message = "please provide an address")
    private String addressLine;
    private String county;
    private String city;
    private boolean isHq;
    private boolean hasAdmin;
    private boolean isVerified;

    public Hospital() {

    }

    public Hospital(String name, String contactNum, String contactEmail, String eirCode, String addressLine,
            String county, String city, boolean isHq, boolean hasAdmin, boolean isVerified) {
        this.name = name;
        this.contactNum = contactNum;
        this.contactEmail = contactEmail;
        this.eirCode = eirCode;
        this.addressLine = addressLine;
        this.county = county;
        this.city = city;
        this.isHq = isHq;
        this.hasAdmin = hasAdmin;
        this.isVerified = isVerified;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getEirCode() {
        return eirCode;
    }

    public void setEirCode(String eirCode) {
        this.eirCode = eirCode;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isHq() {
        return isHq;
    }

    public void setHq(boolean isHq) {
        this.isHq = isHq;
    }

    public boolean isHasAdmin() {
        return hasAdmin;
    }

    public void setHasAdmin(boolean hasAdmin) {
        this.hasAdmin = hasAdmin;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

}
