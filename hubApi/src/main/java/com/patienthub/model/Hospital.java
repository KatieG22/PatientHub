package com.patienthub.model;

import javax.json.bind.annotation.JsonbProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Hospital {

    @JsonbProperty("hospitalName")
    @NotNull(message = "an hospital must have a name")
    @Size(max = 25)
    private String name;

    @NotNull(message = "please provide a contact number")
    @Size(max = 15)
    private String contactNum;

    @NotNull(message = "please provide a contact email")
    @Size(max = 30)
    private String contactEmail;

    @NotNull(message = "please provide an eir code")
    @Size(max = 10)
    private String eirCode;

    @NotNull(message = "please provide an address")
    @Size(max = 25)
    private String addressLine;

    @NotNull(message = "please provide a county")
    @Size(max = 15)
    private String county;

    @NotNull(message = "please provide a city")
    @Size(max = 15)
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

    public void setIsHq(boolean isHq) {
        this.isHq = isHq;
    }

    public boolean hasAdmin() {
        return hasAdmin;
    }

    public void setHasAdmin(boolean hasAdmin) {
        this.hasAdmin = hasAdmin;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setisVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

}
