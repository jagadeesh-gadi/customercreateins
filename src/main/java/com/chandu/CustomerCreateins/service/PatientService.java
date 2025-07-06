package com.chandu.CustomerCreateins.service;

import com.chandu.CustomerCreateins.model.Patient;

import java.util.List;

public interface PatientService {
    List<Patient> getAllPatients();
    Patient getPatientById(Long id);
    Patient savePatient(Patient patient);
    void deletePatientById(Long id);
}
