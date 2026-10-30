package com.vendor.controller;

import com.vendor.entity.ClaimEntity;
import com.vendor.repository.ClaimRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller @RequestMapping("/claims")
public class ClaimController {
    private final ClaimRepository repository;
    public ClaimController(ClaimRepository repository){this.repository=repository;}
    @GetMapping public String list(Model model){model.addAttribute("claims",repository.findAll()); model.addAttribute("claim",new ClaimEntity()); return "claims";}
    @PostMapping("/save") public String save(@ModelAttribute ClaimEntity claim){if(claim.getStatus()==null || claim.getStatus().isBlank()) claim.setStatus("Pending"); repository.save(claim); return "redirect:/claims";}
    @PostMapping("/status/{id}") public String status(@PathVariable Integer id,@RequestParam String status){ClaimEntity claim=repository.findById(id).orElseThrow(); claim.setStatus(status); repository.save(claim); return "redirect:/claims";}
    @PostMapping("/delete/{id}") public String delete(@PathVariable Integer id){repository.deleteById(id); return "redirect:/claims";}
}
