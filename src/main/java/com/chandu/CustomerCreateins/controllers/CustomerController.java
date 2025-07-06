package com.chandu.CustomerCreateins.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chandu.CustomerCreateins.model.Doctor;
import com.chandu.CustomerCreateins.service.DoctorService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class CustomerController {

    @Autowired
    private DoctorService doctorService;

    // Display the form to add a doctor
    @GetMapping("/add-doctor")
    public String showAddDoctorForm() {
        return "admin/add-doctor"; // Path to templates/admin/add-doctor.html
        
    }

    // View all doctors
    @GetMapping("/doctors/view")
    public String viewDoctors(Model model) {
        List<Doctor> doctors = doctorService.getAllDoctors();
        model.addAttribute("doctors", doctors);
        return "admin/view-doctors"; // Path to templates/admin/view-doctors.html
    }

    // Add a new doctor
    @PostMapping("/doctors/add")
    public String addDoctor(@ModelAttribute Doctor doctor, RedirectAttributes redirectAttributes) {
        doctorService.saveDoctor(doctor);
        redirectAttributes.addFlashAttribute("successMessage", "Doctor added successfully!");
        return "redirect:/admin/doctors/view"; // Redirect to the view page after adding
    }

    // Show the edit form for an existing doctor
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Doctor doctor = doctorService.getDoctorById(id);
        if (doctor == null) {
            return "redirect:/admin/doctors/view?error=notfound";
        }
        model.addAttribute("doctor", doctor);
        return "admin/edit-doctor"; // Path to templates/admin/edit-doctor.html
    }

    // Edit an existing doctor
    @PostMapping("/edit")
    public String editDoctor(@ModelAttribute Doctor doctor, RedirectAttributes redirectAttributes) {
        if (doctor.getName() == null || doctor.getSpecialization() == null || doctor.getContactDetails() == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "All fields are required");
            return "redirect:/admin/edit/" + doctor.getId();
        }
        doctorService.saveDoctor(doctor);
        redirectAttributes.addFlashAttribute("successMessage", "Doctor updated successfully!");
        return "redirect:/admin/doctors/view";
    }

    // Delete a doctor
    @PostMapping("/delete")
    public String deleteDoctor(@RequestParam Long id) {
        doctorService.deleteDoctor(id);
        return "redirect:/admin/doctors/view";
    }

    // Error handling for Method Not Allowed
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ModelAndView handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex) {
        ModelAndView modelAndView = new ModelAndView("error"); // Path to templates/error.html
        modelAndView.addObject("message", "Method Not Allowed: " + ex.getMessage());
        return modelAndView;
    }

    // JSON API to create a doctor
    @PostMapping("/create")
    public ResponseEntity<String> createDoctor(@RequestBody Doctor doctor) {
        doctorService.saveDoctor(doctor); // Save the doctor via the service layer
        return ResponseEntity.ok("Doctor created successfully");
    }
}
