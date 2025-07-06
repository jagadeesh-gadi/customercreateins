package com.chandu.CustomerCreateins.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chandu.CustomerCreateins.model.Appointment;
import com.chandu.CustomerCreateins.service.AppointmentService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    // Display list of appointments
    @GetMapping("/appointment/view")
    public String listAppointments(Model model) {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        model.addAttribute("appointments", appointments);
        return "admin/view-appointments"; // Path to templates/admin/appointments.html
    }

    // Show the form to add a new appointment
    @GetMapping("/add-appointment")
    public String showAddAppointmentForm() {
        return "admin/add-appointment"; // Path to templates/admin/add-appointment.html
    }

    // Add a new appointment
    @PostMapping("/appointments/add")
    public String addAppointment(@ModelAttribute Appointment appointment, RedirectAttributes redirectAttributes) {
        appointmentService.saveAppointment(appointment);
        redirectAttributes.addFlashAttribute("successMessage", "Appointment booked successfully!");
        return "redirect:/admin/appointment/view";
    }

    // Show the edit form for an existing appointment
    @GetMapping("/appointments/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        if (appointment == null) {
            return "redirect:/admin/appointments?error=notfound";
        }
        model.addAttribute("appointment", appointment);
        return "admin/edit-appointment"; // Path to templates/admin/edit-appointment.html
    }

    // Edit an existing appointment
    @PostMapping("/appointments/edit")
    public String editAppointment(@ModelAttribute Appointment appointment, RedirectAttributes redirectAttributes) {
        if (appointment.getDate() == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Date cannot be null");
            return "redirect:/admin/appointments/edit/" + appointment.getId();
        }
        appointmentService.saveAppointment(appointment);
        redirectAttributes.addFlashAttribute("successMessage", "Appointment updated successfully!");
        return "redirect:/admin/appointment/view";
    }

    // Delete an appointment
    @PostMapping("/appointments/delete")
    public String deleteAppointment(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        appointmentService.deleteAppointmentById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Appointment deleted successfully!");
        return "redirect:/admin/appointment/view";
    }

    // Error handling for Method Not Allowed
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ModelAndView handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex) {
        ModelAndView modelAndView = new ModelAndView("error"); // Path to templates/error.html
        modelAndView.addObject("message", "Method Not Allowed: " + ex.getMessage());
        return modelAndView;
    }

    // JSON API to create an appointment
    @PostMapping("/appointments/create")
    public ResponseEntity<String> createAppointment(@RequestBody Appointment appointment) {
        appointmentService.saveAppointment(appointment); // Save the appointment via the service layer
        return ResponseEntity.ok("Appointment created successfully");
    }
}
