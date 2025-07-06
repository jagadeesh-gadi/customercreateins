package com.chandu.CustomerCreateins.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.chandu.CustomerCreateins.model.Receptionist;
import com.chandu.CustomerCreateins.service.ReceptionistService;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class ReceptionistController {

    @Autowired
    private ReceptionistService receptionistService;

    // Display the form to add a receptionist
    @GetMapping("/receptionists/add")
    public String showAddReceptionistForm(Model model) {
        model.addAttribute("receptionist", new Receptionist());  // Add a new, empty receptionist object to the model
        return "admin/add-receptionist"; // Path to templates/admin/add-receptionist.html
    }

    // Add a new receptionist (Handle POST request)
    @PostMapping("/receptionists/add")
    public String addReceptionist(@ModelAttribute Receptionist receptionist, RedirectAttributes redirectAttributes) {
        receptionistService.saveReceptionist(receptionist);
        redirectAttributes.addFlashAttribute("successMessage", "Receptionist added successfully!");
        return "redirect:/admin/receptionists/view"; // Redirect to the view page after adding
    }

    // View all receptionists
    @GetMapping("/receptionists/view")
    public String viewReceptionists(Model model) {
        List<Receptionist> receptionists = receptionistService.getAllReceptionists();
        model.addAttribute("receptionists", receptionists);
        return "admin/view-receptionists"; // Path to templates/admin/view-receptionists.html
    }

    // Show the edit form for an existing receptionist
    @GetMapping("/receptionists/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Receptionist receptionist = receptionistService.getReceptionistById(id);
        if (receptionist == null) {
            return "redirect:/admin/receptionists/view?error=notfound";
        }
        model.addAttribute("receptionist", receptionist);
        return "admin/edit-receptionist"; // Path to templates/admin/edit-receptionist.html
    }

    // Edit an existing receptionist (Handle POST request)
    @PostMapping("/receptionists/edit")
    public String editReceptionist(@ModelAttribute Receptionist receptionist, RedirectAttributes redirectAttributes) {
        if (receptionist.getName() == null || receptionist.getContact() == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "All fields are required");
            return "redirect:/admin/receptionists/edit/" + receptionist.getId();
        }
        receptionistService.saveReceptionist(receptionist);
        redirectAttributes.addFlashAttribute("successMessage", "Receptionist updated successfully!");
        return "redirect:/admin/receptionists/view";
    }

    // Delete a receptionist
    @PostMapping("/receptionists/delete")
    public String deleteReceptionist(@RequestParam Long id) {
        receptionistService.deleteReceptionistById(id);
        return "redirect:/admin/receptionists/view";
    }

    // Error handling for Method Not Allowed
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ModelAndView handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex) {
        ModelAndView modelAndView = new ModelAndView("error"); // Path to templates/error.html
        modelAndView.addObject("message", "Method Not Allowed: " + ex.getMessage());
        return modelAndView;
    }

    // JSON API to create a receptionist
    @PostMapping("/receptionists/create")
    public ResponseEntity<String> createReceptionist(@RequestBody Receptionist receptionist) {
        receptionistService.saveReceptionist(receptionist); // Save the receptionist via the service layer
        return ResponseEntity.ok("Receptionist created successfully");
    }
}
