package ru.itis.schedule.usualConrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.schedule.services.AuditoryResourceService;

@Controller
public class AuditoryResourceController {
    @Autowired
    private AuditoryResourceService auditoryResourceService;

    @GetMapping("/auditories/resources/generate")
    public String generate(){
        auditoryResourceService.generate();
        return "redirect:/main";
    }
}
