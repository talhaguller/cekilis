package com.tolga.cekilis.controller;

import com.tolga.cekilis.service.RandomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WinnerController {

    @Autowired
    private RandomService randomService;

    @PostMapping("/winners")
    public String getWinners(
            @RequestParam String title,
            @RequestParam int bd,
            @RequestParam int sd,
            @RequestParam int kk,
            @RequestParam String onay,
            Model model
    ) {
        List<String> winners = randomService.generateWinners(title, bd, sd, kk, onay);
        model.addAttribute("title", title);
        model.addAttribute("winners", winners);
        return "winners"; // Thymeleaf şablon dosyasının adı
    }
}
