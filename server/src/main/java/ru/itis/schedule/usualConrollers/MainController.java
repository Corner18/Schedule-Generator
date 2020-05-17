package ru.itis.schedule.usualConrollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.Column;

@Controller
public class MainController {

    @GetMapping("/main")
    public String getMainPage(){
        return "main";
    }
}
