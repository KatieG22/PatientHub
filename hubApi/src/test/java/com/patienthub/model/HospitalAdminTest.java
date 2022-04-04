package com.patienthub.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HospitalAdminTest {
    /**
     * test ensures that password
     * generated is the first 7 chatacter of pps number
     * first letter of last name is Caps
     * and first letter of first name in lower case
     */
    @Test
    public void testGeneratePassword() {
        HospitalAdmin admin = new HospitalAdmin();
        Doctor doctor = new Doctor();
        doctor.setFirstName("tumise");
        doctor.setLastName("alade");
        doctor.setPps("p299iow669");
        assertEquals("p299iowAt", admin.generatePasswordFor(doctor));
    }

}
