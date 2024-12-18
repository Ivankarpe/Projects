package com.karpenko;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.xml.bind.DatatypeConverter;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class RegisterController {

    private final String url = "jdbc:sqlite:db.db";

    @FXML
    private TextField nameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField cityField;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registerButton;

    @FXML
    private ToggleGroup gender;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("Login");
    }

    @FXML
    private void handleRegister() throws IOException {

        String error = "";

        if (nameField.getText().isEmpty()) {
            System.err.println("sdsad");
            error += "name, ";
        }
        if (lastNameField.getText().isEmpty()) {
            System.err.println("sdsad");
            error += "lastName, ";
        }
        if (cityField.getText().isEmpty()) {
            System.err.println("sdsad");

            error += "city, ";
        }
        if (loginField.getText().isEmpty()) {
            System.err.println("sdsad");
            error += "login, ";
        }
        if (passwordField.getText().isEmpty()) {
            System.err.println("sdsad");

        }
        RadioButton butt = (RadioButton) gender.getSelectedToggle();
        if (butt == null) {
            error += "gender, ";

        }

        if (error != "") {
            Alert alert = new Alert(AlertType.ERROR, "erors in folowing sections: " + error, ButtonType.OK);
            alert.show();
            return;
        }
        try (Connection connection = DriverManager.getConnection(url);
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT  COUNT(*) FROM Users WHERE login = ?; ");) {

            statement.setString(1, loginField.getText());

            ResultSet result = statement.executeQuery();
            result.next();
            if (result.getInt(1) != 0) {
                Alert alert = new Alert(AlertType.ERROR, "Login taken", ButtonType.OK);
                alert.show();
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(url);
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT  login FROM Users WHERE password = ?; ");) {

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(passwordField.getText().getBytes(StandardCharsets.UTF_8));

            String hex = DatatypeConverter.printHexBinary(hash);
            System.out.println(hex);

            statement.setString(1, hex);

            ResultSet result = statement.executeQuery();
            if (result.isBeforeFirst()) {
                result.next();
                Alert alert = new Alert(AlertType.ERROR,
                        "that pasword is taken by login \"" + result.getString("login") + "\"", ButtonType.OK);
                alert.show();
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(url);
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO Users ( name, lastName, city, login, password, genda) VALUES(?,?,?,?,?,?);");) {

            statement.setString(1, nameField.getText());
            statement.setString(2, lastNameField.getText());
            statement.setString(3, cityField.getText());
            statement.setString(4, loginField.getText());

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(passwordField.getText().getBytes(StandardCharsets.UTF_8));

            String hex = DatatypeConverter.printHexBinary(hash);
            System.out.println(hex);

            statement.setString(5, hex);
            String gender = butt.getText().toString();
            statement.setString(6, gender);

            statement.executeUpdate();
            System.err.println("created");
            Alert alert = new Alert(AlertType.INFORMATION, "Created" + error, ButtonType.OK);
            alert.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}