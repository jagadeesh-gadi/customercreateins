package com.chandu.CustomerCreateins.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chandu.CustomerCreateins.model.Patient;
import com.chandu.CustomerCreateins.service.PatientService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class PatientController {

    @Autowired
    private PatientService patientService;

    // Display list of patients
    @GetMapping("/patients/view")
    public String listPatients(Model model) {
        List<Patient> patients = patientService.getAllPatients();
        model.addAttribute("patients", patients);
        return "admin/view-patients"; // Path to templates/admin/view-patients.html
    }

    // Show the form to add a new patient
    @GetMapping("/patients/add")
    public String showAddPatientForm() {
        return "admin/add-patient"; // Path to templates/admin/add-patient.html
    }

    // Add a new patient
    @PostMapping("/patients/add")
    public String addPatient(@ModelAttribute Patient patient, RedirectAttributes redirectAttributes) {
        patientService.savePatient(patient);
        redirectAttributes.addFlashAttribute("successMessage", "Patient added successfully!");
        return "redirect:/admin/patients/view";
    }

    // Show the edit form for an existing patient
    @GetMapping("/patients/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Patient patient = patientService.getPatientById(id);
        if (patient == null) {
            return "redirect:/admin/patients/view?error=notfound";
        }
        model.addAttribute("patient", patient);
        return "admin/edit-patient"; // Path to templates/admin/edit-patient.html
    }

    // Edit an existing patient
    @PostMapping("/patients/edit")
    public String editPatient(@ModelAttribute Patient patient, RedirectAttributes redirectAttributes) {
        if (patientService.getPatientById(patient.getId()) != null) {
            patientService.savePatient(patient);
            redirectAttributes.addFlashAttribute("successMessage", "Patient updated successfully!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Patient not found!");
        }
        return "redirect:/admin/patients/view";
    }

    // Delete a patient
    @PostMapping("/patients/delete")
    public String deletePatient(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        patientService.deletePatientById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Patient deleted successfully!");
        return "redirect:/admin/patients/view";
    }

    // Error handling for Method Not Allowed
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ModelAndView handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex) {
        ModelAndView modelAndView = new ModelAndView("error"); // Path to templates/error.html
        modelAndView.addObject("message", "Method Not Allowed: " + ex.getMessage());
        return modelAndView;
    }

    // JSON API to create a patient
    @PostMapping("/patients/create")
    public ResponseEntity<String> createPatient(@RequestBody Patient patient) {
        patientService.savePatient(patient);
        return ResponseEntity.ok("Patient created successfully");
    }
}
