package com.patienthub.model;

import javax.validation.constraints.NotNull;

public class HospitalAdmin extends User {

    private Hospital hospital;

    public HospitalAdmin(@NotNull(message = "first name is required") String firstName,
            @NotNull(message = "last name is required") String lastName,
            @NotNull(message = "contact number is required") String contactNum,
            @NotNull(message = "email is required") String email, @NotNull(message = "pps is required") String pps,
            Gender gender, Hospital hospital) {
        super(firstName, lastName, contactNum, email, pps, gender);
        this.hospital = hospital;

    }

    public HospitalAdmin() {
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public String generatePasswordFor(User user) {
        // get date
        String firstName = user.getFirstName().toLowerCase();
        String lastName = user.getLastName().toUpperCase();
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            char character = user.getPps().charAt(i);
            str.append(character);

        }
        str.append(lastName.charAt(0));
        str.append(firstName.charAt(0));
        return str.toString();

    }

}
