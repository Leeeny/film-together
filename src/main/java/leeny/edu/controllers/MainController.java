package leeny.edu.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainController {

    @FXML private MediaPlayerController mediaPlayerController;
    @FXML private ChatController chatController;
    @FXML private SettingsController settingsController;

    private ClientController clientController;

    @FXML private HBox mainComponent;

    @FXML
    private void initialize() {
        mediaPlayerController.injectMainController(this);
        settingsController.injectMainController(this);
        clientController = new ClientController(this);
    }

    public void hideComponents() {
        // TODO нужно скрыть чат, настройки
        chatController.setVisible(false);
        settingsController.setVisible(false);
//        mediaPlayerController.resizeWindow();
        ((Stage)mainComponent.getScene().getWindow()).setFullScreen(true);
    }

    public void seekComponents() {
        // TODO нужно раскрыть чат, настройки
        chatController.setVisible(true);
        settingsController.setVisible(true);
//        mediaPlayerController.resizeWindow();
        ((Stage)mainComponent.getScene().getWindow()).setFullScreen(false);
    }

    public MediaPlayerController getMediaPlayerController() {
        return mediaPlayerController;
    }

        public void stopVideo() {
        mediaPlayerController.stopVideo();
    }

    public void playVideo() {
        mediaPlayerController.stopVideo();
    }

    public void rewindVideo(double time) {
        mediaPlayerController.rewindVideo(time);
    }

    public void addMessage(String mes) {

    }

    public void addUser(String username) {

    }
}
