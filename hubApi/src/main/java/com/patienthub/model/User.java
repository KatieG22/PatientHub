package com.patienthub.model;

import java.nio.charset.StandardCharsets;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.google.common.hash.Hashing;
import com.patienthub.constraintAnnotations.Email;

public class User {

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

    private String gender = "others";

    private String role;

    private boolean isactive;

    @NotNull(message = "password is required")
    private String password;

    public User() {

    }

    public User(@NotNull(message = "first name is required") String firstName,
            @NotNull(message = "last name is required") String lastName,
            @NotNull(message = "contact number is required") String contactNum,
            @NotNull(message = "email is required") String email, @NotNull(message = "pps is required") String pps,
            String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNum = contactNum;
        this.email = email;
        this.pps = pps;
        this.gender = gender;
        this.isactive = false;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean passwordValid(String providedPassword){
        String hashOfProvidedPassword = Hashing.sha256()
        .hashString(password, StandardCharsets.UTF_8)
        .toString();
        return password.equals(hashOfProvidedPassword);
    }

}
