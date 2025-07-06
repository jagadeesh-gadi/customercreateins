package com.chandu.CustomerCreateins.repository;

import com.chandu.CustomerCreateins.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
