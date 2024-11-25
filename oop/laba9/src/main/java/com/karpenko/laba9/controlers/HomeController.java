package com.karpenko.laba9.controlers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.karpenko.laba9.models.Lesson;
import com.karpenko.laba9.services.lessons.LessonServiceDB;
import com.karpenko.laba9.services.lessons.LessonServiceInterface;



@Controller
public class HomeController {

    private final LessonServiceInterface lessonService;

    public HomeController(LessonServiceDB lessonServiceDB) {
        this.lessonService = lessonServiceDB;
    }



    @GetMapping({"/", "/home"})
    public String Home(Model model) {

        List<Lesson> products = lessonService.readAll();
        model.addAttribute("lessons", products );
        System.err.println(products.size());
        return "index"; 
    }

    @GetMapping("/schedule")
    public String Schedule() {

        return "scedule"; 
    }
}
