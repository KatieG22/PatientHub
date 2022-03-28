package com.patienthub.serializer;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.patienthub.annotation.Email;
import com.patienthub.model.Gender;

public abstract class UserSerializer {

    @NotNull(message = "first name is required")
    @Size(max = 15)
    private String firstName;

    @NotNull(message = "last name is required")
    @Size(max = 15)
    private String lastName;

    @NotNull(message = "contact number is required")
    @Size(max = 15)
    private String contactNum;

    @NotNull(message = "email is required")
    @Email(message = "not a valid mail")
    @Size(max = 25)
    private String email;

    @NotNull(message = "pps is required")
    @Size(max = 15)
    private String pps;

    private Gender gender = Gender.OTHERS;

    private boolean isactive;

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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public boolean isIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }

}
