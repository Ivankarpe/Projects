package com.karpenko.laba9.services.lessons;

import java.util.List;

import com.karpenko.laba9.models.Lesson;


public interface LessonServiceInterface {

    void create(Lesson product);

    List<Lesson> readAll();

    Lesson read(String uuid);

    boolean update(Lesson product, String uuid);

    boolean delete(String uuid);

}