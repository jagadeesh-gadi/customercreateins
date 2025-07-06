package com.chandu.CustomerCreateins.repository.jpa;

import com.chandu.CustomerCreateins.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    // Additional custom queries (if needed) can be added here
}
