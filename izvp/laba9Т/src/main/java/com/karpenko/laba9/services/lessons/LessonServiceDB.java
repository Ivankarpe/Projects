package com.karpenko.laba9.services.lessons;

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
public class LessonServiceDB implements LessonServiceInterface {

    private final String url = "jdbc:sqlite:db.db";

    public LessonServiceDB() {
        try (Connection connection = DriverManager.getConnection(url)) {
            System.err.println("Coected to DB");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(Lesson lesson) {
        try (Connection connection = DriverManager.getConnection(url);
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO lessons(id, name, teacher, day, time) VALUES(?,?,?,?,?);");) {

            String uuid = UUID.randomUUID().toString();
            statement.setString(1, uuid);
            statement.setString(2, lesson.getName());
            statement.setString(3, lesson.getTeacher());
            statement.setString(4, lesson.getDay());
            statement.setString(5, lesson.getTime());

            statement.executeUpdate();
            System.err.println("created");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Lesson> readAll() {
        List<Lesson> lessons = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url);
                PreparedStatement statement = connection.prepareStatement("Select * from lessons;");
                ResultSet resultSet = statement.executeQuery();) {

            while (resultSet.next()) {
                Lesson lesson = new Lesson();
                lesson.setId(resultSet.getString("id"));
                lesson.setName(resultSet.getString("name"));
                lesson.setTeacher(resultSet.getString("teacher"));
                lesson.setDay(resultSet.getString("day"));
                lesson.setTime(resultSet.getString("time"));

                lessons.add(lesson);
            }

            System.err.println("read all");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lessons;
    }

    @Override
    public Lesson read(String uuid) {
        Lesson lesson = new Lesson();
        try (Connection connection = DriverManager.getConnection(url);
                PreparedStatement statement = connection.prepareStatement("Select * from Products WHERE id = ?;");) {
            statement.setString(1, uuid);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            lesson.setId(resultSet.getString("id"));
            lesson.setName(resultSet.getString("name"));
            lesson.setTeacher(resultSet.getString("teacher"));
            lesson.setDay(resultSet.getString("day"));
            lesson.setTime(resultSet.getString("time"));

            System.err.println("readed from " + uuid);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lesson;

    }

    @Override
    public boolean update(Lesson lesson, String uuid) {
        try (Connection connection = DriverManager.getConnection(url);
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE lessons SET  name=?, teacher=?, day=?, time=? WHERE id = ?;");) {

            statement.setString(1, lesson.getName());
            statement.setString(2, lesson.getTeacher());
            statement.setString(3, lesson.getDay());
            statement.setString(4, lesson.getTime());

            statement.setString(5, uuid);

            statement.executeUpdate();
            System.err.println("Updated");

            Lesson newLesson = read(uuid);
            if (newLesson.getName() == lesson.getName() && newLesson.getTeacher() == lesson.getTeacher()
                    && newLesson.getTime() == lesson.getTime() && newLesson.getDay() == lesson.getDay()) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String uuid) {
        try (Connection connection = DriverManager.getConnection(url);
                PreparedStatement statement = connection.prepareStatement(
                        "DELETE FROM lessons WHERE id=? ");) {

            statement.setString(1, uuid);

            statement.executeUpdate();
            System.err.println("Deleted");

            Lesson newProduct = read(uuid);
            if (newProduct == null) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
