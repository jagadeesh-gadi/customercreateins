package com.chandu.CustomerCreateins.repository;

import com.chandu.CustomerCreateins.model.Receptionist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceptionistRepository extends JpaRepository<Receptionist, Long> {
    // Additional custom queries (if needed) can be added here
}
