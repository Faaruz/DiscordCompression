import com.discordcompression.controllers.VideoCompressionTask;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class ConditionDecisionCoverage {
    private VideoCompressionTask compressor = new VideoCompressionTask();

    @Test
    public void testCompressVideoConditionDecisionCoverage() throws Exception {
        boolean invalidExtensionDetected = false;
        boolean fileSizeExceedsLimit = false;

        // .mp4 file + kleiner dan 150MB
        String inputFilePath = "src/testvideos/test1.mp4";
        String outputFilePath = "src/testvideos/test1.mp4";
        try {
            compressor.compressVideo(inputFilePath, outputFilePath);
        } catch (java.io.IOException e) {
            invalidExtensionDetected = true;
        }
        assertFalse(invalidExtensionDetected, "Test valid input file + kleiner dan 150MB niet geslaagd. Check is niet uitgevoerd");

        // .mp4 file + groter dan 150MB
        inputFilePath = "src/testvideos/test.mp4";
        outputFilePath = "src/testvideos/test2.mp4";
        try {
            compressor.compressVideo(inputFilePath, outputFilePath);
        } catch (java.io.IOException e) {
            invalidExtensionDetected = true;
        }
        assertTrue(invalidExtensionDetected, "Input file size exceeds limit.");

        // .mov file + kleiner dan 150MB
        inputFilePath = "src/testvideos/test1.mov";
        outputFilePath = "src/testvideos/test3.mp4";
        try {
            compressor.compressVideo(inputFilePath, outputFilePath);
        } catch (java.io.IOException e) {
            fileSizeExceedsLimit = true;
        }
        assertTrue(fileSizeExceedsLimit, "Input file size exceeds 200MB limit.");

        // Test case for valid input file - A: false, B: false
        inputFilePath = "src/testvideos/test.mov";
        outputFilePath = "src/testvideos/test4.mp4";
        try {
            compressor.compressVideo(inputFilePath, outputFilePath);
        } catch (java.io.IOException e) {
            fileSizeExceedsLimit = true;
            invalidExtensionDetected = true;
        }
        assertTrue(fileSizeExceedsLimit && invalidExtensionDetected, "Input file size exceeds 200MB limit.");
    }
}
