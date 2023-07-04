import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import com.discordcompression.controllers.VideoCompressionTask;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class Equivalentieklassen {

    /**
     * EQUIVALENTIEKLASSEN
     * @throws Exception
     */
    Boolean invalidExtensionDetected = false;
    Boolean fileSizeExceedsLimit = false;

    @Test
    public void testCompressVideoEquivalentieKlassen() throws Exception {
        VideoCompressionTask compressor = new VideoCompressionTask();

        // Test with valid input file + 0MB file size
        String validInputFilePath = createCustomTestFile(0);
        String outputFilePath = "src/testvideos/test1.mp4";
        try {
            compressor.compressVideo(validInputFilePath, outputFilePath);
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
            invalidExtensionDetected = true;

        }
        assertTrue(invalidExtensionDetected, "Invalid input file extension was not detected.");


        // Test with valid input file + 1MB file size
        validInputFilePath = createCustomTestFile(1);
        outputFilePath = "src/testvideos/test1.mp4";
        try {
            compressor.compressVideo(validInputFilePath, outputFilePath);
        } catch (IOException | InterruptedException e) {
            invalidExtensionDetected = true;
        }
        assertTrue(invalidExtensionDetected, "Invalid input file extension was not detected.");


        // Test with valid input file + 150MB file size
        validInputFilePath = createCustomTestFile(150);
        outputFilePath = "src/testvideos/test1.mp4";
        try {
            compressor.compressVideo(validInputFilePath, outputFilePath);
            invalidExtensionDetected = false;
        } catch (IOException | InterruptedException e) {
            invalidExtensionDetected = true;
        }
        assertFalse(invalidExtensionDetected, "Invalid input file extension was not detected.");


        // Test with valid input file + 151MB file size
        validInputFilePath = createCustomTestFile(151);
        outputFilePath = "src/testvideos/test1.mp4";
        try {
            compressor.compressVideo(validInputFilePath, outputFilePath);
        } catch (IOException | InterruptedException e) {
            invalidExtensionDetected = true;
        }
        assertFalse(invalidExtensionDetected, "Invalid input file extension was not detected.");




    }

    private String createCustomTestFile(int fileSizeInMB) throws IOException {
        String filePath = "src/testvideos/custom_test_file.mp4";
        File file = new File(filePath);
        FileOutputStream fos = new FileOutputStream(file);
        byte[] data = new byte[fileSizeInMB * 1024 * 1024];
        fos.write(data);
        fos.close();
        return filePath;
    }

}





