package com.discordcompression.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SettingViewController implements Initializable {

    @FXML
    private Button menuAudioEdit;

    @FXML
    private Button menuDeepFry;

    @FXML
    private Button menuSettings;

    @FXML
    private Button menuVideoCompress;

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    void MenuChange(MouseEvent event) throws IOException {
        if (event.getSource() == menuSettings) {
            stage = (Stage) menuSettings.getScene().getWindow();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/setting-view.fxml")));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else if (event.getSource() == menuVideoCompress) {
            stage = (Stage) menuSettings.getScene().getWindow();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/start-view.fxml")));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else if (event.getSource() == menuAudioEdit) {
            System.out.println("Audio Edit");
        } else if (event.getSource() == menuDeepFry) {
            System.out.println("Deep Fry");
        }
    }

    @FXML
    void MenuChange1(MouseEvent event) throws IOException {


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
