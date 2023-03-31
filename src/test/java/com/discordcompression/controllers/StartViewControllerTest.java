package com.discordcompression.controllers;

import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
class StartViewControllerTest {

    @Test
    void testCompressFile() {
        VideoCompressionTask videoCompressionTask = new VideoCompressionTask();

        File inputFile = new File("C:\\Users\\Faaruz\\Desktop\\DiscordCompression\\src\\testvideos\\test.mp4");
        File outputFile = new File("C:\\Users\\Faaruz\\Desktop\\DiscordCompression\\src\\testvideos\\test1.mp4");
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
        System.out.println("Video has been compressed");
    }
}