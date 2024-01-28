package com.example.hw4.contoller;

import com.example.hw4.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@Controller
public class PageController {
    @RequestMapping
    public String helloPage(Model model) {
        model.addAttribute("time", timeNow());
        return "index";
    }

    @GetMapping("/form")
    public String form() {
        return "form";
    }


    @PostMapping("/accept")
    public String acceptForm(User user, Model model) {
        String dataProcessing = "'" + user.getName() + "'. ";
        model.addAttribute("name", dataProcessing);
        return "main";
    }

    private String timeNow() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
        return currentDateTime.format(formatter);
    }


}
