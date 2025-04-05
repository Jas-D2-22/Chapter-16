import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class CountdownTimer extends Application {
    private int remainingTime; // To track the countdown time
    private Timeline timeline; // To update the countdown

    @Override
    public void start(Stage primaryStage) {
        // UI Elements
        TextField timeInput = new TextField();
        Label countdownDisplay = new Label("Enter seconds and press Enter");
        timeInput.setPromptText("Enter seconds here");

        // Handle Enter key press
        timeInput.setOnAction(e -> {
            // Parse user input
            try {
                remainingTime = Integer.parseInt(timeInput.getText());
                countdownDisplay.setText(String.valueOf(remainingTime)); // Display initial time
                startCountdown(countdownDisplay);
            } catch (NumberFormatException ex) {
                countdownDisplay.setText("Invalid input! Please enter an integer.");
            }
        });

        // Arrange UI
        VBox root = new VBox(10, timeInput, countdownDisplay);
        Scene scene = new Scene(root, 300, 150);

        // Setup Stage
        primaryStage.setTitle("Countdown Timer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void startCountdown(Label countdownDisplay) {
        if (timeline != null) {
            timeline.stop(); // Reset timeline if already running
        }

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            remainingTime--;
            if (remainingTime > 0) {
                countdownDisplay.setText(String.valueOf(remainingTime));
            } else {
                timeline.stop();
                countdownDisplay.setText("Time's up! Playing music...");
                playMusic();
            }
        }));
        timeline.setCycleCount(remainingTime); // Run for the specified number of seconds
        timeline.play();
    }

    private void playMusic() {
        try {
            String musicFile = "https://liveexample.pearsoncmg.com/common/audio/anthem/anthem0.mp3"; // URL for media
            Media media = new Media("https://liveexample.pearsoncmg.com/common/audio/anthem/anthem0.mp3");
            //Media media = new Media(new File(musicFile).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);

            mediaPlayer.setVolume(1.0); // Set The Volume maxium (0.0 to 1.0)
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Play continuously
            mediaPlayer.play();
        } catch (Exception e) {
            System.out.println("Error playing music: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

