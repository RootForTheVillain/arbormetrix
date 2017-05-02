package com.arbormetrix.controller;

import com.arbormetrix.model.Patients;

import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;



@RestController
@RequestMapping(path="rest/*")
public class PatientsController {

    @Autowired
    private ResourceLoader resourceLoader;

    private static final String XML_FILE_PATH =
            "classpath:/patients.xml";

    @ResponseBody
    @GetMapping(path="patients", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getPatients() {
        String json = null;
        try {
            // Read input file from disk
            Resource resource = resourceLoader.getResource(XML_FILE_PATH);
            InputStream inputStream = resource.getInputStream();

            JAXBContext jaxbContext = JAXBContext.newInstance(Patients[].class);

            // Unmarshal XML to POJO
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Patients patients = (Patients) unmarshaller.unmarshal(inputStream);

            // Map POJO to JSON
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            Object[] patientList = patients.getPatients().toArray();
            json = mapper.writeValueAsString(patientList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

}