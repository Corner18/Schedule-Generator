package ru.itis.schedule.usualConrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.schedule.services.TimeslotService;

@Controller

public class TimeslotController {

    @Autowired
    private TimeslotService timeslotService;

    @GetMapping("/timeslots/generate")
    public String generate(){
        timeslotService.generateTimeSlots();
        return "redirect:/main";
    }
}
