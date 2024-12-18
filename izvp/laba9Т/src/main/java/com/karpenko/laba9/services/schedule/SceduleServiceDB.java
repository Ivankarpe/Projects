package com.karpenko.laba9.services.schedule;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.karpenko.laba9.models.Lesson;

@Service
public class SceduleServiceDB implements ScheduleServiceInterface {

    private final String url = "jdbc:sqlite:db.db";

    public SceduleServiceDB() {
        try (Connection connection = DriverManager.getConnection(url)) {
            System.err.println("Coected to DB");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List readSchedule() {
        
        List<List<Lesson>> lessons = new ArrayList<>();
        lessons.add(readDay("Monday"));
        lessons.add(readDay("Tuesday"));
        lessons.add(readDay("Wednesday"));
        lessons.add(readDay("Thursday"));
        lessons.add(readDay("Friday"));

        return lessons;
    }

    @Override
    public List<Lesson> readDay(String day) {
        List<Lesson> lessons = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url);
                PreparedStatement statement = connection.prepareStatement("Select * from lessons WHERE day = ? ORDER BY time;");) {
            statement.setString(1, day);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Lesson lesson = new Lesson();
                lesson.setId(resultSet.getString("id"));
                lesson.setName(resultSet.getString("name"));
                lesson.setTeacher(resultSet.getString("teacher"));
                lesson.setDay(resultSet.getString("day"));
                lesson.setTime(resultSet.getString("time"));

                lessons.add(lesson);
            }

            System.err.println("scedule for " + day);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lessons;
    }

}
