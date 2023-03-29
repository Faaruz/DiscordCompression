module com.example.discordcompression {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires IVCompressor;
    requires org.apache.commons.io;
    requires ffmpeg;

    opens com.discordcompression to javafx.fxml;
    exports com.discordcompression;
    exports com.discordcompression.controllers;
    opens com.discordcompression.controllers to javafx.fxml;
}