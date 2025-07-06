package com.chandu.CustomerCreateins.service;

import com.chandu.CustomerCreateins.model.Appointment;

import java.util.List;

public interface AppointmentService {
    List<Appointment> getAllAppointments();
    Appointment getAppointmentById(Long id);
    Appointment saveAppointment(Appointment appointment);
    void deleteAppointmentById(Long id);
}

