package com.karpenko.laba9.controlers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.karpenko.laba9.models.Lesson;
import com.karpenko.laba9.services.lessons.LessonServiceDB;
import com.karpenko.laba9.services.lessons.LessonServiceInterface;
import com.karpenko.laba9.services.schedule.SceduleServiceDB;
import com.karpenko.laba9.services.schedule.ScheduleServiceInterface;

@RestController
public class ScheduleController {

    private final ScheduleServiceInterface sceduleService;

    public ScheduleController(SceduleServiceDB sceduleServiceDB) {
        this.sceduleService = sceduleServiceDB;
    }

    @GetMapping(value = "/api/schedule")
    public ResponseEntity<List<List<Lesson>>> readAll() {
        final List lessons = sceduleService.readSchedule();
        System.err.println("readingSchedule");
        if (lessons == null || lessons.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(lessons, HttpStatus.OK);
    }

    @GetMapping(value = "/api/schedule/{day}")
    public ResponseEntity<List<Lesson>> read(@PathVariable(name = "day") String day) {
        final List<Lesson> lessons = sceduleService.readDay(day);

        if (lessons == null || lessons.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(lessons, HttpStatus.OK);
    }

    
}
