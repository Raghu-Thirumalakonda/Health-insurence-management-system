package com.vendor.controller;

import com.vendor.repository.DoctorRepository;
import com.vendor.repository.PatientRepository;
import com.vendor.repository.VendorRepository;
import com.vendor.repository.PolicyRepository;
import com.vendor.repository.ClaimRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Controller
public class HomeController {
    private final PatientRepository patients;
    private final DoctorRepository doctors;
    private final VendorRepository vendors;
    private final PolicyRepository policies;
    private final ClaimRepository claims;

    public HomeController(PatientRepository patients, DoctorRepository doctors, VendorRepository vendors,
                          PolicyRepository policies, ClaimRepository claims) {
        this.patients = patients; this.doctors = doctors; this.vendors = vendors;
        this.policies = policies; this.claims = claims;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("today", LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy", Locale.ENGLISH)).toUpperCase(Locale.ENGLISH));
        model.addAttribute("patientCount", patients.count());
        model.addAttribute("doctorCount", doctors.count());
        model.addAttribute("vendorCount", vendors.count());
        model.addAttribute("policyCount", policies.count());
        model.addAttribute("claimCount", claims.count());
        model.addAttribute("recentClaims", claims.findTop5ByOrderByIdDesc());
        return "index";
    }
}
