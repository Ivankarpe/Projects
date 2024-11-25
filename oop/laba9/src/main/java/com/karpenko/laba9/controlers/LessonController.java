package com.karpenko.laba9.controlers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.karpenko.laba9.models.Lesson;
import com.karpenko.laba9.services.lessons.LessonServiceDB;
import com.karpenko.laba9.services.lessons.LessonServiceInterface;

@RestController
public class LessonController {

    private final LessonServiceInterface lessonService;

    public LessonController(LessonServiceDB lessonServiceDB) {
        this.lessonService = lessonServiceDB;
    }

    @GetMapping(value = "/api/lessons")
    public ResponseEntity<List<Lesson>> readAll() {
        final List<Lesson> lessons = lessonService.readAll();

        if (lessons == null || lessons.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(lessons, HttpStatus.OK);
    }

    @GetMapping(value = "/api/lessons/{id}")
    public ResponseEntity<Lesson> read(@PathVariable(name = "id") String id) {
        final Lesson lesson = lessonService.read(id);

        if (lesson == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(lesson, HttpStatus.OK);
    }

    @PostMapping(value = "/api/lessons")
    public ResponseEntity<?> create(@RequestBody Lesson lesson) {
        lessonService.create(lesson);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/lessons/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") String id, @RequestBody Lesson client) {
        final boolean updated = lessonService.update(client, id);

        return new ResponseEntity<>(updated ? HttpStatus.OK : HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/api/lessons/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") String id) {
        final boolean deleted = lessonService.delete(id);

        return new ResponseEntity<>(deleted ? HttpStatus.OK : HttpStatus.NOT_MODIFIED);

    }
}
