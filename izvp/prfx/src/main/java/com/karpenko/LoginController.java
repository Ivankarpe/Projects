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
import javafx.scene.control.TextField;

public class LoginController {

    private final String url = "jdbc:sqlite:db.db";

    @FXML private TextField LoginText;
    @FXML private PasswordField PasswordText;
    @FXML private Button LogimButt;



    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("Register");
    }
    
    @FXML
    private void Login() throws IOException {
        String error = "";

        if (LoginText.getText().isEmpty()) {
            System.err.println("sdsad");
            error += "Login, ";
        }
        if (PasswordText.getText().isEmpty()) {
            System.err.println("sdsad");
            error += "Password, ";
        }
         if (error != "") {
            Alert alert = new Alert(AlertType.ERROR, "erors in folowing sections: " + error, ButtonType.OK);
            alert.show();
            return;
        }
        try (Connection connection = DriverManager.getConnection(url);
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT  name, lastName FROM Users WHERE login = ? AND password = ?;");) {

           

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(PasswordText.getText().getBytes(StandardCharsets.UTF_8));

            String hex = DatatypeConverter.printHexBinary(hash);
            System.out.println(hex);
            statement.setString(1, LoginText.getText().toString());
            statement.setString(2, hex);
            

            
           
            ResultSet result = statement.executeQuery();
            if(!result.isBeforeFirst()){
                Alert alert = new Alert(AlertType.ERROR, "THERE NO SHUCH MAN", ButtonType.OK);
                alert.show();
                return;
            }                


            result.next();



            Alert alert = new Alert(AlertType.INFORMATION, "Whelcome " + result.getString("name")+" " + result.getString("lastName"), ButtonType.OK);
            alert.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}