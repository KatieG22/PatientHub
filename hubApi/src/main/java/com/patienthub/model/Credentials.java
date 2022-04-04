package com.patienthub.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.patienthub.annotation.Email;

/**
 * @author Glen
 *         This class contains all creditentials user needs to login
 */
public class Credentials {

    @NotNull(message = "email is required")
    @Email(message = "not a valid mail")
    @Size(max = 25)
    private String email;

    @NotNull(message = "password is required")
    private String password;

    public Credentials() {

    }

    public Credentials(@NotNull(message = "email is required") @Size(max = 25) String email,
            @NotNull(message = "password is required") String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
