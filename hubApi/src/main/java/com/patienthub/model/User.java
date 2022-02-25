package com.patienthub.model;

import javax.validation.constraints.NotNull;

import com.patienthub.constraintAnnotations.Email;

public class User {

    @NotNull(message = "first name is required")
    private String firstName;

    @NotNull(message = "last name is required")
    private String lastName;

    @NotNull(message = "contact number is required")
    private String contactNum;

    @NotNull(message = "email is required")
    @Email(message = "not a valid mail")
    private String email;

    @NotNull(message = "pps is required")
    private String pps;

    private String gender = "others";

    private String role;

    private boolean isactive;

    public User() {

    }

    public User(@NotNull(message = "first name is required") String firstName,
            @NotNull(message = "last name is required") String lastName,
            @NotNull(message = "contact number is required") String contactNum,
            @NotNull(message = "email is required") String email, @NotNull(message = "pps is required") String pps,
            String gender, String role, boolean isactive) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNum = contactNum;
        this.email = email;
        this.pps = pps;
        this.gender = gender;
        this.role = role;
        this.isactive = isactive;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPps() {
        return pps;
    }

    public void setPps(String pps) {
        this.pps = pps;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isisactive() {
        return isactive;
    }

    public void setisactive(boolean isactive) {
        this.isactive = isactive;
    }

}
