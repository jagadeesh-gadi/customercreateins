package com.chandu.CustomerCreateins.repository;

import com.chandu.CustomerCreateins.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    // Additional custom queries (if needed) can be added here
}
