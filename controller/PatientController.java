package com.vendor.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        return "patient";
    }

    @PostMapping("/save")
    public String savePatient(@ModelAttribute PatientModel patientModel) {

        patientService.savePatient(patientModel);

        return "redirect:/patient";
    }

    @GetMapping("/delete/{id}")
    public String deletePatient(@PathVariable Integer id) {

        patientService.deletePatient(id);

        return "redirect:/patient";
    }
}
