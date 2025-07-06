package com.chandu.CustomerCreateins.service;

import com.chandu.CustomerCreateins.model.Doctor;

import java.util.List;

public interface DoctorService {
    Doctor saveDoctor(Doctor doctor);
    List<Doctor> getAllDoctors();
    Doctor getDoctorById(Long id);
    Doctor updateDoctor(Doctor doctor);
    void deleteDoctor(Long id);
}
