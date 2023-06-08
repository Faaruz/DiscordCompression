package com.discordcompression.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class VideoCompressionTask {
    public void compressVideo(String inputFilePath, String outputFilePath) throws IOException, InterruptedException {
        long fileSize = Files.size(Paths.get(inputFilePath));
        long fileSizeInMB = fileSize / (1024 * 1024);
        long fileSizeLimit = 150;

        if (!inputFilePath.endsWith(".mp4") || fileSizeInMB > fileSizeLimit || fileSizeInMB < 6){
            throw new IOException("Input file size is either bigger than " + fileSizeLimit + "MB or invalid File Extension Current size: " + fileSizeInMB + " " +  fileSizeLimit);
        }

        // Set the target file size in bytes
        long targetFileSize = 9388608; // 8 MB

        // Build the FFmpeg command
        List<String> command = new ArrayList<>();
        command.add("ffmpeg");
        command.add("-i");
        command.add(inputFilePath);
        command.add("-b:v");
        command.add("2M"); // Set the video bitrate to 8 Mbps
        command.add("-maxrate");
        command.add("5M"); // Set the maximum video bitrate to 9 Mbps
        command.add("-bufsize");
        command.add("2M"); // Set the buffer size to 2 MB
        command.add("-vf");
        command.add("scale=w=640:h=360:force_original_aspect_ratio=decrease"); // Set the video resolution to 1280x720
        command.add("-preset");
        command.add("fast"); // Set the encoding preset to fast
        command.add("-movflags");
        command.add("+faststart"); // Enable faststart for streaming
        command.add(outputFilePath);

        // Execute the FFmpeg command in a separate thread
        // Makes it so the GUI doesnt freeze
        ProcessBuilder pb = new ProcessBuilder(command);
        pb.redirectErrorStream(true);
        Process process = pb.start();

        // Check if the output file is within the target file size range
        File outputFile = new File(outputFilePath);
        long outputFileSize = outputFile.length();

        // Check if the output file is within the target file size range
        while (outputFileSize > targetFileSize) {
            // If the output file size is greater than the target file size,
            // decrease the video bitrate by 700 Kbps and re-encode the video
            command.set(4, String.valueOf(Integer.parseInt(command.get(4).replace("M", "")) - 100) + "K");
            command.set(6, String.valueOf(Integer.parseInt(command.get(6).replace("M", "")) - 100) + "K");
            pb = new ProcessBuilder(command);
            pb.redirectErrorStream(true);
            process = pb.start();
            process.waitFor();
            outputFileSize = outputFile.length();

            // Exit the loop if the output file size is less than or equal to the target file size
            if (outputFileSize <= targetFileSize) {
                break;
            }
        }
    }

    public void setFileSize(String inputFilePath, String outputFilePath, long targetFileSize) throws IOException, InterruptedException {

        if (!inputFilePath.endsWith(".mp4")) {
            throw new IOException("Input file format is not supported. Please upload .mp4 file format only.");
        }

        // Build the FFmpeg command
        List<String> command = new ArrayList<>();
        command.add("ffmpeg");
        command.add("-i");
        command.add(inputFilePath);
        command.add("-b:v");
        command.add("2M"); // Set the video bitrate to 8 Mbps
        command.add("-maxrate");
        command.add("5M"); // Set the maximum video bitrate to 9 Mbps
        command.add("-bufsize");
        command.add("2M"); // Set the buffer size to 2 MB
        command.add("-vf");
        command.add("scale=w=640:h=360:force_original_aspect_ratio=decrease"); // Set the video resolution to 1280x720
        command.add("-preset");
        command.add("fast"); // Set the encoding preset to fast
        command.add("-movflags");
        command.add("+faststart"); // Enable faststart for streaming
        command.add(outputFilePath);

        // Execute the FFmpeg command in a separate thread
        // Makes it so the GUI doesnt freeze
        ProcessBuilder pb = new ProcessBuilder(command);
        pb.redirectErrorStream(true);
        Process process = pb.start();

        // Check if the output file is within the target file size range
        File outputFile = new File(outputFilePath);
        long outputFileSize = outputFile.length();

        // Check if the output file is within the target file size range
        while (outputFileSize > targetFileSize) {
            // If the output file size is greater than the target file size,
            // decrease the video bitrate by 700 Kbps and re-encode the video
            command.set(4, String.valueOf(Integer.parseInt(command.get(4).replace("M", "")) - 100) + "K");
            command.set(6, String.valueOf(Integer.parseInt(command.get(6).replace("M", "")) - 100) + "K");
            pb = new ProcessBuilder(command);
            pb.redirectErrorStream(true);
            process = pb.start();
            process.waitFor();
            outputFileSize = outputFile.length();

            // Exit the loop if the output file size is less than or equal to the target file size
            if (outputFileSize <= targetFileSize) {
                break;
            }
        }
    }

    public void deepFryVideo(String inputFilePath, String outputFilePath) throws IOException, InterruptedException {
        // Set the target file size in bytes
        long targetFileSize = 9388608; // 8 MB

        // Build the FFmpeg command
        List<String> command = new ArrayList<>();
        command.add("ffmpeg");
        command.add("-i");
        command.add(inputFilePath);
        command.add("-b:v");
        command.add("2B"); // Set the video bitrate to 8 Mbps
        command.add("-maxrate");
        command.add("5B"); // Set the maximum video bitrate to 9 Mbps
        command.add("-bufsize");
        command.add("2B"); // Set the buffer size to 2 MB
        command.add("-vf");
        command.add("scale=w=640:h=360:force_original_aspect_ratio=decrease"); // Set the video resolution to 1280x720
        command.add("-preset");
        command.add("fast"); // Set the encoding preset to fast
        command.add("-movflags");
        command.add("+faststart"); // Enable faststart for streaming
        command.add("-crf");
        command.add("35"); // Set the Constant Rate Factor (CRF) to 35 for deep frying
        command.add(outputFilePath);

        // Execute the FFmpeg command in a separate thread
        // Makes it so the GUI doesnt freeze
        ProcessBuilder pb = new ProcessBuilder(command);
        pb.redirectErrorStream(true);
        Process process = pb.start();

        // Wait for the process to complete
        process.waitFor();

        // Check if the output file is within the target file size range
        File outputFile = new File(outputFilePath);
        long outputFileSize = outputFile.length();

        // If the output file size is greater than the target file size,
        // decrease the video bitrate by 700 Kbps and re-encode the video
        while (outputFileSize > targetFileSize) {
            command.set(4, String.valueOf(Integer.parseInt(command.get(4).replace("M", "")) - 100) + "K");
            command.set(6, String.valueOf(Integer.parseInt(command.get(6).replace("M", "")) - 100) + "K");
            pb = new ProcessBuilder(command);
            pb.redirectErrorStream(true);
            process = pb.start();
            process.waitFor();
            outputFileSize = outputFile.length();

            // Exit the loop if the output file size is less than or equal to the target file size
            if (outputFileSize <= targetFileSize) {
                break;
            }
        }
    }
}
