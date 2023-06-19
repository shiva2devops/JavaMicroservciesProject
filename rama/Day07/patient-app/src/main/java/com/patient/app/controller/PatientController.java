package com.patient.app.controller;

import com.patient.app.exception.PatientIdNotFoundException;
import com.patient.app.exception.PatientNameNotFoundException;
import com.patient.app.model.Patient;
import com.patient.app.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PatientController {

    @Autowired
    PatientService patientService;


    @PostMapping("/patient/save")
    public Patient savePatient(@RequestBody Patient patient) {
        if (patient.getId() == null) {
            throw new PatientIdNotFoundException("patient Id in null");
        }
        if (patient.getName() == null) {
            throw new PatientNameNotFoundException("patient Name is null");
        }
        return patient;
    }

    @PutMapping("/patient/update")
    public Patient updatePatient(@RequestBody Patient patient, @RequestParam("disease") String disease, @RequestParam("age") String age) {
        return patientService.updateDetail(patient, disease, age);
    }

    @PostMapping("/patient/detail")
    public Patient detailPatient(@RequestBody Patient patient,@RequestHeader("disease") String disease)  {
        patient.setDisease(disease);
        System.out.println(patient);
        return patient;
    }

}

