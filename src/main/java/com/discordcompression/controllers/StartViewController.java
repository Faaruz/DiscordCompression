package com.discordcompression.controllers;

import io.github.techgnious.IVCompressor;
import io.github.techgnious.exception.VideoException;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StartViewController {
    VideoCompressionTask videoCompressionTask = new VideoCompressionTask();

    public String path;
    public File file;
    public MediaPlayer mediaPlayer;

    @FXML
    private MediaView mediaView;

    @FXML
    private Slider progressBar;

    @FXML
    private Slider volumeSlider;

    @FXML
    void handleDragOver(DragEvent event) {
        Dragboard db = event.getDragboard();
        if (db.hasFiles()) {
            event.acceptTransferModes(javafx.scene.input.TransferMode.COPY);
        } else {
            event.consume();
        }
    }

    @FXML
    void handleDropped(DragEvent event) {
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasFiles()) {
            success = true;
            List<File> files = db.getFiles();
            path = files.get(0).toURI().toString();
            videoSetup();
            this.file = db.getFiles().get(0);
        }
        event.setDropCompleted(success);
        event.consume();
    }

    public void chooseFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        this.file = file;
        path = file.toURI().toString();

        if (path != null) {
            videoSetup();
        }
    }

    private void videoSetup() {
        Media media = new Media(path);
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);

        mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observableValue, Duration duration, Duration t1) {
                progressBar.setValue(t1.toSeconds());
            }
        });

        progressBar.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.seek(Duration.seconds(progressBar.getValue()));
            }
        });

        progressBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mediaPlayer.seek(Duration.seconds(progressBar.getValue()));
            }
        });

        mediaPlayer.setOnReady(new Runnable() {
            @Override
            public void run() {
                progressBar.setMin(0.0);
                progressBar.setMax(mediaPlayer.getTotalDuration().toSeconds());
            }
        });

        volumeSlider.setValue(mediaPlayer.getVolume() * 100);
        volumeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                mediaPlayer.setVolume(volumeSlider.getValue() / 100);
            }
        });
        mediaPlayer.play();
    }

    public void play(ActionEvent event) {
        mediaPlayer.play();

    }

    public void pause(ActionEvent event) {
        mediaPlayer.pause();
    }

    @FXML
    void compressFile(ActionEvent event) {
        File inputFile = file;
        String fileName = "output.mp4";
        File outputFile = new File(inputFile.getParentFile(), "output/" + fileName);
        int count = 1;
        while (outputFile.exists()) {
            fileName = "output" + count + ".mp4";
            outputFile = new File(inputFile.getParentFile(), "output/" + fileName);
            count++;
        }
// create the output folder if it doesn't exist
        outputFile.getParentFile().mkdirs();
        File finalOutputFile = outputFile;
        Thread thread = new Thread(() -> {
            try {
                videoCompressionTask.compressVideo(inputFile.getAbsolutePath(), finalOutputFile.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }
}
