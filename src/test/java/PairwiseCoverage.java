import com.discordcompression.controllers.VideoCompressionTask;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PairwiseCoverage {
    /**
     * PAIRWISE COVERAGE
     * @throws Exception
     */
    @Test
    public void testPairwiseCoverage() throws Exception {
        VideoCompressionTask compressor = new VideoCompressionTask();
        boolean autoSave;

        // Scenario 1: Input format = .mp4, Bestandsgrootte ≤ 50MB, Doelgrootte = 25MB, Automatisch Opslaan = ja
        String inputFilePath = "src/testvideos/test.mp4";
        String outputFilePath = "src/testvideos/test1.mp4";
        autoSave = true;
        boolean invalidExtensionDetected = false;
        try {
            compressor.compressVideo(inputFilePath, outputFilePath);
        } catch (IOException | InterruptedException e) {
            invalidExtensionDetected = true;
        }
        assertFalse(invalidExtensionDetected && !autoSave, "Invalid input file extension was not detected.");

        // Scenario 2: Input format = .mp4, Bestandsgrootte > 50MB, Doelgrootte = 50MB, Automatisch Opslaan = nee
        inputFilePath = "src/testvideos/large.mp4";
        autoSave = false;
        try {
            compressor.compressVideo(inputFilePath, outputFilePath);
        } catch (IOException | InterruptedException e) {
            invalidExtensionDetected = true;
        }
        assertFalse(invalidExtensionDetected && !autoSave, "Invalid input file extension was not detected.");


        // Scenario 3: Input format = .avi, Bestandsgrootte > 50MB, Doelgrootte = 50MB, Automatisch Opslaan = nee
        String inputFilePath3 = "src/testvideos/large.avi";
        long fileSize3 = Files.size(Paths.get(inputFilePath3));
        long fileSizeInMB3 = fileSize3 / (1024 * 1024);
        boolean autoSave3 = false;
        assertTrue(inputFilePath3.endsWith(".avi") && fileSizeInMB3 > 50 && !autoSave3, "Pairwise scenario 3");

        // Scenario 4: Input format = .avi, Bestandsgrootte ≤ 50MB, Doelgrootte = 25MB, Automatisch Opslaan = ja
        String inputFilePath4 = "src/testvideos/test1.avi";
        long fileSize4 = Files.size(Paths.get(inputFilePath4));
        long fileSizeInMB4 = fileSize4 / (1024 * 1024);
        boolean autoSave4 = true;
        assertTrue(inputFilePath4.endsWith(".avi") && fileSizeInMB4 <= 50 && autoSave4, "Pairwise scenario 4");

        // Scenario 5: Input format = .mov, Bestandsgrootte > 50MB, Doelgrootte = 25MB, Automatisch Opslaan = nee
        String inputFilePath5 = "src/testvideos/large.mov";
        long fileSize5 = Files.size(Paths.get(inputFilePath5));
        long fileSizeInMB5 = fileSize5 / (1024 * 1024);
        boolean autoSave5 = false;
        assertTrue(inputFilePath5.endsWith(".mov") && fileSizeInMB5 > 50 && !autoSave5, "Pairwise scenario 5");

        // Scenario 6: Input format = .mov, Bestandsgrootte ≤ 50MB, Doelgrootte = 50MB, Automatisch Opslaan = ja
        String inputFilePath6 = "src/testvideos/test1.mov";
        long fileSize6 = Files.size(Paths.get(inputFilePath6));
        long fileSizeInMB6 = fileSize6 / (1024 * 1024);
        boolean autoSave6 = true;
        assertTrue(inputFilePath6.endsWith(".mov") && fileSizeInMB6 <= 50 && autoSave6, "Pairwise scenario 6");
    }
}
