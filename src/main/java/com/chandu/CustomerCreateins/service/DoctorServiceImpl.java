package com.chandu.CustomerCreateins.service;

import com.chandu.CustomerCreateins.model.Doctor;
import com.chandu.CustomerCreateins.repository.DoctorRepository; // Make sure to create this repository
import com.chandu.CustomerCreateins.service.DoctorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository; // Inject the repository

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor); // Save the doctor to the database
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll(); // Retrieve all doctors from the database
    }

    @Override
    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id).orElse(null); // Find a doctor by ID
    }

    @Override
    public Doctor updateDoctor(Doctor doctor) {
        return doctorRepository.save(doctor); // Update the doctor details
    }

    @Override
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id); // Delete the doctor by ID
    }
}
