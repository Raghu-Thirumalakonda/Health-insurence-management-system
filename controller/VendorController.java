package com.vendor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.vendor.model.VendorModel;
import com.vendor.service.VendorService;

@Controller
@RequestMapping("/vendor")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    @GetMapping
    public String vendorPage(Model model) {

        model.addAttribute("vendorModel", new VendorModel());
        model.addAttribute("vendors", vendorService.getAllVendors());

        return "vendor";
    }

    @PostMapping("/save")
    public String saveVendor(@ModelAttribute VendorModel vendorModel) {

        vendorService.saveVendor(vendorModel);

        return "redirect:/vendor";
    }

    @GetMapping("/delete/{id}")
    public String deleteVendor(@PathVariable Integer id) {

        vendorService.deleteVendor(id);

        return "redirect:/vendor";
    }
}
