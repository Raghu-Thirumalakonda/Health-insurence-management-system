package com.vendor.controller;

import com.vendor.entity.PolicyEntity;
import com.vendor.repository.PolicyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@Controller @RequestMapping("/policies")
public class PolicyController {
    private final PolicyRepository repository;
    public PolicyController(PolicyRepository repository){this.repository=repository;}
    @GetMapping public String list(@RequestParam(required=false) String q, Model model){
        model.addAttribute("policies", q == null || q.isBlank() ? repository.findAll() : repository.findByPolicyNameContainingIgnoreCase(q));
        model.addAttribute("q",q); model.addAttribute("policy",new PolicyEntity()); return "policies";
    }
    @PostMapping("/save") public String save(@ModelAttribute PolicyEntity policy){
        if(policy.getPremium()==null) policy.setPremium(BigDecimal.ZERO);
        repository.save(policy); return "redirect:/policies";
    }
    @GetMapping("/edit/{id}") public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("policies",repository.findAll()); model.addAttribute("policy",repository.findById(id).orElseThrow()); return "policies";
    }
    @PostMapping("/delete/{id}") public String delete(@PathVariable Integer id){repository.deleteById(id); return "redirect:/policies";}
}
