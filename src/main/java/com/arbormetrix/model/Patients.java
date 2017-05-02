package com.arbormetrix.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "patients")
public class Patients {
    private List<Patient> patients;

    public List<Patient> getPatients() {
        return patients;
    }

    @XmlElement(name = "patient")
    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }
}
