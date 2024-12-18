package com.karpenko.laba9.models;

public class Lesson {

    String id;
    String name;
    String teacher;
    String day;
    String time;

    public String getDay() {
        return day;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getTeacher() {
        return teacher;
    }
    public String getTime() {
        return time;
    }
    public void setDay(String day) {
        this.day = day;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
    public void setTime(String time) {
        this.time = time;
    }
}
