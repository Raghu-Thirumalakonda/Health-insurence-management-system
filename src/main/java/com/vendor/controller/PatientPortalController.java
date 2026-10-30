package com.vendor.controller;

import com.vendor.model.PatientModel;
import com.vendor.service.PatientService;
import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/portal")
public class PatientPortalController {
    private final PatientService patients;
    public PatientPortalController(PatientService patients) { this.patients = patients; }

    @GetMapping
    public String home(Principal principal, Model model) {
        PatientModel patient = patients.getPatientByEmail(principal.getName());
        if (patient == null) return "redirect:/login?error";
        model.addAttribute("patient", patient);
        return "patient-portal";
    }

    @PostMapping("/password")
    public String changePassword(Principal principal, @RequestParam String currentPassword,
            @RequestParam String newPassword, @RequestParam String confirmPassword,
            RedirectAttributes flash) {
        if (!newPassword.equals(confirmPassword)) flash.addFlashAttribute("passwordError", "The new passwords do not match.");
        else if (newPassword.length() < 10) flash.addFlashAttribute("passwordError", "Choose a password with at least 10 characters.");
        else if (!patients.changePortalPassword(principal.getName(), currentPassword, newPassword)) flash.addFlashAttribute("passwordError", "Current password was not correct.");
        else flash.addFlashAttribute("passwordSuccess", "Your password has been updated.");
        return "redirect:/portal";
    }
}
