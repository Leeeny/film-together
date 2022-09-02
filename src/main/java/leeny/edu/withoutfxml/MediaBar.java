package leeny.edu.withoutfxml;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

public class MediaBar extends HBox { // leeny.edu.withoutfxml.MediaBar extends Horizontal Box

    // introducing Sliders
    private final Slider time = new Slider(); // Slider for time
    private final Slider vol = new Slider(); // Slider for volume
    private final Button playButton = new Button("||"); // For pausing the player
    private final Label volume = new Label("Volume: ");
    private final MediaPlayer player;

    private boolean isPlayed = true;

    public MediaBar(MediaPlayer player) { // Default constructor taking the MediaPlayer object
        this.player = player;

        setAlignment(Pos.CENTER); // setting the HBox to center
        setPadding(new Insets(5, 10, 5, 10));
        // Setting the preference for volume bar
        vol.setPrefWidth(70);
        vol.setMinWidth(30);
        vol.setValue(100);
        HBox.setHgrow(time, Priority.ALWAYS);
        playButton.setPrefWidth(30);

        // Adding the components to the bottom
        getChildren().add(playButton);  // play button
        getChildren().add(time);        // time slider
        getChildren().add(volume);      // volume slider
        getChildren().add(vol);

        initComponentFunc();
    }

    private void initComponentFunc() {
        // Adding Functionality to play the media player
        playButton.setOnAction(e -> playButtonFunc());

        // Providing functionality to time slider
        player.currentTimeProperty().addListener(ov -> updatesValues());

        // Inorder to jump to the certain part of video
        time.valueProperty().addListener(ov -> {
            if (time.isPressed()) { // It would set the time as specified by user by pressing
                player.pause();
                player.seek(player.getMedia().getDuration().multiply(time.getValue() / 100));
            }
        });

        time.setOnMouseReleased(ov -> {
            if (isPlayed) {
                player.play();
            } else {
                player.pause();
            }
        });

        // providing functionality to volume slider
        vol.valueProperty().addListener(ov -> {
            if (vol.isPressed()) {
                player.setVolume(vol.getValue() / 100); // It would set the volume as specified by user by pressing
            }
        });
    }

    private void updatesValues() {
        Platform.runLater(() -> {
            // Updating to the new time value
            // This will move the slider while running your video
            time.setValue(player.getCurrentTime().toMillis() /
                    player.getTotalDuration()
                            .toMillis()
                            * 100);
        });
    }

    private void playButtonFunc() {
        Status status = player.getStatus(); // To get the status of leeny.edu.withoutfxml.Player
        if (status == Status.PLAYING) {

            // If the status is Video playing
            if (player.getCurrentTime().greaterThanOrEqualTo(player.getTotalDuration())) {

                // If the player is at the end of video
                player.seek(player.getStartTime()); // Restart the video
                player.play();
                isPlayed = true;
            }
            else {
                // Pausing the player
                player.pause();
                isPlayed = false;

                playButton.setText(">");
            }
        } // If the video is stopped, halted or paused
        if (status == Status.HALTED || status == Status.STOPPED || status == Status.PAUSED) {
            player.play(); // Start the video
            playButton.setText("||");
            isPlayed = true;
        }
    }
}