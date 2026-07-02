package com.vendor.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.vendor.model.DoctorModel;
import com.vendor.service.DoctorService;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public String doctorPage(Model model) {

        model.addAttribute("doctorModel", new DoctorModel());
        model.addAttribute("doctors", doctorService.getAllDoctors());

        return "doctor";
    }

    @PostMapping("/save")
    public String saveDoctor(@ModelAttribute DoctorModel doctorModel) {

        doctorService.saveDoctor(doctorModel);

        return "redirect:/doctor";
    }

    @GetMapping("/delete/{id}")
    public String deleteDoctor(@PathVariable Integer id) {

        doctorService.deleteDoctor(id);

        return "redirect:/doctor";
    }
}
