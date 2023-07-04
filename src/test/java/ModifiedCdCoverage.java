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

            // A = False, B = True C = True
            String inputFilePath = "src/testvideos/test1.mov";
            String outputFilePath = "src/testvideos/test4.mp4";
            try {
                compressor.compressVideo(inputFilePath, outputFilePath);
            } catch (java.io.IOException e) {
                invalidExtensionDetected = true;
            }
            assertTrue(invalidExtensionDetected, "Invalid input file extension was not detected.");
            assertFalse(fileSizeExceedsLimit, "Input file size exceeds 150MB limit.");

            // A = True, B = False C = True
            inputFilePath = "src/testvideos/test.mp4";
            outputFilePath = "src/testvideos/test4.mp4";
            try {
                compressor.compressVideo(inputFilePath, outputFilePath);
                invalidExtensionDetected = false;
            } catch (java.io.IOException e) {
                fileSizeExceedsLimit = true;
                invalidExtensionDetected = true;
            }
            assertFalse(invalidExtensionDetected, "Invalid input file extension was not detected.");
            assertFalse(fileSizeExceedsLimit, "Input file size exceeds 150MB limit.");

            // A = True, B = True C = False
            inputFilePath = "src/testvideos/test.mov";
            outputFilePath = "src/testvideos/test4.mp4";
            try {
                compressor.compressVideo(inputFilePath, outputFilePath);
            } catch (java.io.IOException e) {
                fileSizeExceedsLimit = true;
                invalidExtensionDetected = false;
            }
            assertFalse(invalidExtensionDetected, "Invalid input file extension was not detected.");
            assertTrue(fileSizeExceedsLimit, "Input file size exceeds 150MB limit.");

            // A = True, B = True C = True
            inputFilePath = "src/testvideos/test.mov";
            outputFilePath = "src/testvideos/test4.mp4";
            try {
                compressor.compressVideo(inputFilePath, outputFilePath);
            } catch (java.io.IOException e) {
                fileSizeExceedsLimit = false;
                invalidExtensionDetected = false;
            }
            assertFalse(invalidExtensionDetected, "Invalid input file extension was not detected.");
            assertFalse(fileSizeExceedsLimit, "Input file size exceeds 150MB limit.");
        }
    }

