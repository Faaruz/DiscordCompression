import com.discordcompression.controllers.VideoCompressionTask;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConditionCoverage {
    private final VideoCompressionTask compressor = new VideoCompressionTask();

    /**
     * CONDITION COVERAGE
     * @throws Exception
     */
    @Test
    public void testCompressVideoConditionCoverage() throws Exception {
        // Test invalid input file + kleiner dan 150MB
        String inputFilePath = "src/testvideos/test1.mov";
        String outputFilePath = "src/testvideos/test2.mp4";
        boolean invalidExtensionDetected = false;
        try {
            compressor.compressVideo(inputFilePath, outputFilePath);
        } catch (java.io.IOException e) {
            invalidExtensionDetected = true;
        }
        assertTrue(invalidExtensionDetected, "Invalid input file extension was not detected.");

        // Test valid input file + kleiner dan 150MB
        inputFilePath = "src/testvideos/large.mp4";
        try {
            compressor.compressVideo(inputFilePath, outputFilePath);
        } catch (java.io.IOException e) {
            invalidExtensionDetected = true;
        }
        assertTrue(invalidExtensionDetected, "Invalid input file extension was not detected.");
    }
}
