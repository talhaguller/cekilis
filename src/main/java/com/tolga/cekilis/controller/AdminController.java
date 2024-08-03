package com.tolga.cekilis.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tolga.cekilis.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private User user;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final File dbFile = new File("src/main/resources/db.json");

    public AdminController() {
        try {
            user = objectMapper.readValue(dbFile, User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping
    public String adminPage(HttpSession session, Model model) {
        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
        if (loggedIn != null && loggedIn) {
            model.addAttribute("user", user);
            return "admin";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/update")
    public String updateSettings(@RequestParam(required = false) String jokerName,
                                 @RequestParam(required = false) Integer jokerSayi,
                                 @RequestParam(defaultValue = "false") boolean activate,
                                 HttpSession session,
                                 Model model) {
        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
        if (loggedIn != null && loggedIn) {
            user.setJokerName(jokerName);
            user.setJokerSayi(jokerSayi);
            user.setActivate(activate);

            try {
                objectMapper.writeValue(dbFile, user);
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("error", "Veriler kaydedilemedi.");
            }

            model.addAttribute("user", user);
            return "admin";
        } else {
            return "redirect:/login";
        }
    }
}
