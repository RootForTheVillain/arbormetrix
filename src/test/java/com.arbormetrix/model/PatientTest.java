package com.arbormetrix.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;



public class PatientTest {
    @Test
    public void testGetSex() {
        Patient patient = new Patient();
        patient.setSex("f");
        assertEquals(patient.getSex(),"female");

        patient.setSex("m");
        assertEquals(patient.getSex(),"male");
    }

    @Test
    public void testSetAge() {
        Patient patient = new Patient();
        patient.setDob("11/01/1980");
        assertEquals(patient.getAge(), 36);
    }

    @Test
    public void testGetState() {
        Patient patient = new Patient();
        patient.setState("Michigan");
        assertEquals(patient.getState(),"MI");

        patient.setState("Ohio");
        assertEquals(patient.getState(),"OH");
    }
}
