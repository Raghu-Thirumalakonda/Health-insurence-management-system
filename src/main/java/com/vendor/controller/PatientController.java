package com.vendor.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.time.LocalDate;

import com.vendor.model.PatientModel;
import com.vendor.service.PatientService;

@Controller
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public String patientPage(Model model) {

        model.addAttribute("patientModel", new PatientModel());
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("today", LocalDate.now());
        model.addAttribute("expiringCount", patientService.getAllPatients().stream().filter(p -> p.getDaysToExpiry() >= 0 && p.getDaysToExpiry() <= 30).count());

        return "patient";
    }

    @GetMapping("/edit/{id}")
    public String editPatient(@PathVariable Integer id, Model model) {
        model.addAttribute("patientModel", patientService.getPatientById(id));
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("today", LocalDate.now());
        model.addAttribute("expiringCount", patientService.getAllPatients().stream().filter(p -> p.getDaysToExpiry() >= 0 && p.getDaysToExpiry() <= 30).count());
        return "patient";
    }

    @PostMapping("/save")
    public String savePatient(@ModelAttribute PatientModel patientModel, RedirectAttributes flash) {
        try {
            patientService.savePatient(patientModel);
            flash.addFlashAttribute("saveSuccess", "Patient record saved.");
        } catch (IllegalArgumentException ex) {
            flash.addFlashAttribute("saveError", ex.getMessage());
        }
        return "redirect:/patient";
    }

    @PostMapping("/delete/{id}")
    public String deletePatient(@PathVariable Integer id) {

        patientService.deletePatient(id);

        return "redirect:/patient";
    }
}
