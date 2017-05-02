package com.arbormetrix.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import java.util.HashMap;



@JsonPropertyOrder({
        "patientid",
        "sex",
        "state",
        "name",
        "age"
})
public class Patient {

    @JsonProperty("patientid")
    private Long id;

    @JsonIgnore
    private String gender;

    private String sex;

    private String name;

    private String state;

    private int age;

    @JsonIgnore
    private String dob;

    @JsonIgnore
    private HashMap<String, String> statesMap = new HashMap<>();

    @JsonIgnore
    private HashMap<String, String> genderMap = new HashMap<>();

    public Patient() {
        // This would contain all 50 states, and be moved
        // to a "utils" package
        this.statesMap.put("Michigan", "MI");
        this.statesMap.put("Ohio", "OH");

        this.genderMap.put("F", "Female");
        this.genderMap.put("M", "Male");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
        this.setSex(this.gender);
    }

    /**
     * Sets sex to full gender from single-letter gender code
     * @param genderCode  "M" or "F"
     */
    public void setSex(String genderCode) {
        this.sex = this.genderMap.getOrDefault(
                    genderCode.toUpperCase(), genderCode)
                        .toLowerCase();
    }

    public String getSex() {
        return this.sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets state abbreviation from full state name
     * @return state abbreviation
     */
    public String getState() {
        return this.statesMap
                .getOrDefault(this.state, this.state);
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
        this.setAge(dob);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Calcaulates and sets age based on date of birth
     * @param dob a String representation of the
     *            date of birth, in MM/dd/yyyy format
     */
    public void setAge(String dob) {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate bday = LocalDate.parse(dob, formatter);
        int age = Period.between(bday, LocalDate.now()).getYears();
        this.setAge(age);
    }
}
