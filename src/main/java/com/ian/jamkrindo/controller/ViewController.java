package com.ian.jamkrindo.controller;

import com.ian.jamkrindo.service.GetDataClaimService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class ViewController {
    private final GetDataClaimService getDataClaimService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("dataClaimResponse", getDataClaimService.getDataClaim());
        return "index";
    }
}
