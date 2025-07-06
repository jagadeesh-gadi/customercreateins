package com.chandu.CustomerCreateins.service;

import com.chandu.CustomerCreateins.model.Receptionist;

import java.util.List;

public interface ReceptionistService {
    List<Receptionist> getAllReceptionists();
    Receptionist getReceptionistById(Long id);
    Receptionist saveReceptionist(Receptionist receptionist);
    void deleteReceptionistById(Long id);
}
