import com.discordcompression.controllers.VideoCompressionTask;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ModifiedCdCoverage {
    VideoCompressionTask compressor = new VideoCompressionTask();

    /**
     * MODIFIED CONDITION/DECISION COVERAGE
     * @throws Exception
     */
        @Test
        public void testCompressVideoModifiedConditionDecisionCoverage() throws Exception {
            Boolean invalidExtensionDetected = false;
            Boolean fileSizeExceedsLimit = false;

            // A = true, B = false
            String inputFilePath = "src/testvideos/test1.mp4";
            String outputFilePath = "src/testvideos/test4.mp4";
            try {
                compressor.compressVideo(inputFilePath, outputFilePath);
                fileSizeExceedsLimit = true;
            } catch (java.io.IOException e) {
                invalidExtensionDetected = true;
            }
            assertFalse(invalidExtensionDetected, "Invalid input file extension was not detected.");
            assertTrue(fileSizeExceedsLimit, "Input file size exceeds 150MB limit.");

            // A = false, B = true
            inputFilePath = "src/testvideos/test.mov";
            outputFilePath = "src/testvideos/test4.mp4";
            try {
                compressor.compressVideo(inputFilePath, outputFilePath);
            } catch (java.io.IOException e) {
                fileSizeExceedsLimit = false;
                invalidExtensionDetected = true;
            }
            assertTrue(invalidExtensionDetected, "Invalid input file extension was not detected.");
            assertFalse(fileSizeExceedsLimit, "Input file size exceeds 150MB limit.");
        }
    }

