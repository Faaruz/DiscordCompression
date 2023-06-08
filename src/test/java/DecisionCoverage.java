import com.discordcompression.controllers.VideoCompressionTask;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DecisionCoverage {
    @Test
    public void testCompressVideoDecisionCoverage() throws Exception {
        VideoCompressionTask compressor = new VideoCompressionTask();
//        // Test invalid input file + groter dan 150MB
        String inputFilePath = "src/testvideos/large.mov";
        String outputFilePath = "src/testvideos/test1.mov";
        boolean invalidExtensionDetected = false;
        try {
            compressor.compressVideo(inputFilePath, outputFilePath);
        } catch (java.io.IOException e) {
            invalidExtensionDetected = true;
        }
        assertTrue(invalidExtensionDetected, "Test invalid input file + groter dan 150MB niet geslaagd. Check is niet uitgevoerd");

        // Test valid input file + kleiner dan 150MB
        inputFilePath = "src/testvideos/test1.mp4";
        outputFilePath = "src/testvideos/test2.mp4";
        invalidExtensionDetected = false;
        try {
            compressor.compressVideo(inputFilePath, outputFilePath);
        } catch (java.io.IOException e) {
            System.out.println(e.getMessage());
            invalidExtensionDetected = true;
        }
        assertFalse(invalidExtensionDetected, "Invalid input file extension was detected.");
    }
}
