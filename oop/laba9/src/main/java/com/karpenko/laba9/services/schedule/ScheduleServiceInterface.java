package com.karpenko.laba9.services.schedule;

import java.util.List;

import com.karpenko.laba9.models.Lesson;


public interface ScheduleServiceInterface {

    
    public List<List<Lesson>>  readSchedule();

    public List<Lesson> readDay(String day);
    

}