package leeny.edu.controllers;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import leeny.edu.enums.ImageStatus;

import java.io.File;

public class MediaPlayerController {

    private MainController mainController;

    private MediaPlayer mediaPlayerComponent;

    @FXML private MediaView mediaView;
    @FXML private StackPane mediaPlayer;

    @FXML private ImageView playbackImage;
    private boolean isPlayed;

    @FXML private ImageView volumeImage;
    @FXML private Slider volumeSlider;

    @FXML private Slider timeSlider;

    @FXML private ImageView fullScreenImage;
    private boolean isFullScreen = false;

    @FXML public HBox controlPanel;

    public void injectMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private void handleOpenVideoAction(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Select file", "*.mp4");
        fileChooser.getExtensionFilters().add(filter);

        File file = fileChooser.showOpenDialog(null);

        if (file == null) {
            return;
        }

        String filePath = file.toURI().toString();
        if (filePath != null && filePath.endsWith(".mp4")) {
            mediaPlayerComponent = null;

            Media media = new Media(filePath);
            mediaPlayerComponent = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayerComponent);
            playbackImage.setImage(ImageStatus.PAUSE.getImage());

//            DoubleProperty width = mediaView.fitWidthProperty();
//            DoubleProperty height = mediaView.fitHeightProperty();
//            System.out.println("width: " + width);
//            System.out.println("height: " + height);
//
//            width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
//            height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));


            volumeSlider.valueProperty().setValue(mediaPlayerComponent.getVolume() * 100);
            initAfterOpenVideo();

            mediaPlayerComponent.play();
            isPlayed = true;
        }
    }

    @FXML
    private void handlePlaybackVideoAction(MouseEvent event) {
        if (mediaPlayerComponent == null) {
            return;
        }

        MediaPlayer.Status status = mediaPlayerComponent.getStatus();
        if (status == MediaPlayer.Status.PLAYING) {

            // If the status is Video playing
            if (mediaPlayerComponent.getCurrentTime().greaterThanOrEqualTo(mediaPlayerComponent.getTotalDuration())) {

                // If the player is at the end of video
                mediaPlayerComponent.seek(mediaPlayerComponent.getStartTime()); // Restart the video
                playVideo();
            }
            else {
                // Pausing the player
                stopVideo();
            }
        } // If the video is stopped, halted or paused
        if (status == MediaPlayer.Status.HALTED || status == MediaPlayer.Status.STOPPED || status == MediaPlayer.Status.PAUSED) {
            playVideo();
        }
    }

    @FXML
    private void handleFullScreenAction(MouseEvent event) {
        if (isFullScreen) {
            mainController.seekComponents();
        } else {
            mainController.hideComponents();
//            DoubleProperty width = mediaView.fitWidthProperty();
//            DoubleProperty height = mediaView.fitHeightProperty();
//
//            width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
//            height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
        }
        isFullScreen = !isFullScreen;
    }

    private void detectMouseInactivity() {
        BooleanProperty mouseMoving = new SimpleBooleanProperty();
        mouseMoving.addListener((obs, wasMoving, isNowMoving) -> {
            if (!isNowMoving) {
                controlPanel.setVisible(false);
            }
        });

        PauseTransition pause = new PauseTransition(Duration.millis(3000));
        pause.setOnFinished(e -> mouseMoving.set(false));

        // Note: if you want to consider the mouse having moved for
        // other events (e.g. dragging), you can do
        // pane.addEventHandler(MouseEvent.ANY, e -> { ... }); here
        mediaPlayer.setOnMouseMoved(e -> {
            mouseMoving.set(true);
            pause.playFromStart();
            controlPanel.setVisible(true);
        });
    }

    private void initAfterOpenVideo() {
        detectMouseInactivity();

        volumeSlider.valueProperty().addListener(ov -> {
            if (volumeSlider.isPressed()) {
                double volumeValue = volumeSlider.getValue() / 100;
                mediaPlayerComponent.setVolume(volumeValue);
                if (volumeValue > 0) {
                    volumeImage.setImage(ImageStatus.VOLUME_ON.getImage());
                } else {
                    volumeImage.setImage(ImageStatus.VOLUME_OFF.getImage());
                }
            }
        });

        mediaPlayerComponent.currentTimeProperty().addListener(ov ->
                Platform.runLater(() ->
                        timeSlider.setValue(mediaPlayerComponent.getCurrentTime().toMillis() /
                            mediaPlayerComponent.getTotalDuration().toMillis() * 100)));

        timeSlider.valueProperty().addListener(ov -> {
            if (timeSlider.isPressed()) { // It would set the time as specified by user by pressing
                mediaPlayerComponent.pause();
                rewindVideo(timeSlider.getValue() / 100);
            }
        });

        timeSlider.setOnMouseReleased(ov -> {
            if (isPlayed) {
                mediaPlayerComponent.play();
            } else {
                mediaPlayerComponent.pause();
            }
        });
    }

    public void stopVideo() {
        mediaPlayerComponent.pause();
        playbackImage.setImage(ImageStatus.PLAY.getImage());
        isPlayed = false;
    }

    public void playVideo() {
        mediaPlayerComponent.play(); // Start the video
        playbackImage.setImage(ImageStatus.PAUSE.getImage());
        isPlayed = true;
    }

    public void rewindVideo(double time) {
        mediaPlayerComponent.seek(mediaPlayerComponent.getMedia().getDuration().multiply(time));
    }
}
