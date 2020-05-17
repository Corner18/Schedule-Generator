package ru.itis.schedule.usualConrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itis.schedule.dto.TimeslotDayDto;
import ru.itis.schedule.models.TimeslotDay;
import ru.itis.schedule.services.TimeslotDayService;

import java.util.List;

@RequestMapping("/timeslots-day")
@Controller
public class TimeslotDayContorller {

    @Autowired
    private TimeslotDayService timeslotDayService;


    @PostMapping("/set-period")
    public String setPeriod(@RequestParam String begin, @RequestParam String end){
        TimeslotDayDto timeslotDayDto = TimeslotDayDto.builder().begin(begin).end(end).build();
        timeslotDayService.setPeriod(timeslotDayDto);
        return "redirect:/main";
    }

    @GetMapping
    public String getPeriodPage(){
        return "set-period";
    }

}
