package com.chandu.CustomerCreateins.service;

import com.chandu.CustomerCreateins.model.Receptionist;
import com.chandu.CustomerCreateins.repository.ReceptionistRepository;
import com.chandu.CustomerCreateins.service.ReceptionistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceptionistServiceImpl implements ReceptionistService {

    @Autowired
    private ReceptionistRepository receptionistRepository;

    @Override
    public List<Receptionist> getAllReceptionists() {
        return receptionistRepository.findAll();
    }

    @Override
    public Receptionist getReceptionistById(Long id) {
        return receptionistRepository.findById(id).orElse(null);
    }

    @Override
    public Receptionist saveReceptionist(Receptionist receptionist) {
        return receptionistRepository.save(receptionist);
    }

    @Override
    public void deleteReceptionistById(Long id) {
        receptionistRepository.deleteById(id);
    }
}
