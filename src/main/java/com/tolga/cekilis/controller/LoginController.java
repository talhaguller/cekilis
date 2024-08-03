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
public class LoginController {

    private User user;

    public LoginController() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            user = objectMapper.readValue(new File("src/main/resources/db.json"), User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
            session.setAttribute("loggedIn", true);
            return "redirect:/admin";
        } else {
            model.addAttribute("error", "Geçersiz kullanıcı adı veya şifre!");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
