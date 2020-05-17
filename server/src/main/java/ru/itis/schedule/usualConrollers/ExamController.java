package ru.itis.schedule.usualConrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.schedule.services.ExamService;
import ru.itis.schedule.services.GenerationService;

@Controller
public class ExamController {

    @Autowired
    private GenerationService generationService;

    @Autowired
    private ExamService examService;

    @GetMapping("/generate")
    public String generateExams(){
        generationService.generate();
        return "redirect:/main";
    }

    @GetMapping("/exams")
    public String getSchedule(Model model){
        model.addAttribute("exams", examService.getExamsDto());
        return "exams";
    }
}
