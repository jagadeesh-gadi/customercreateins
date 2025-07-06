package com.chandu.CustomerCreateins.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.chandu.CustomerCreateins.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    // You can add custom queries if needed
}
