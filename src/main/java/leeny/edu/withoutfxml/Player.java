package leeny.edu.withoutfxml;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

// leeny.edu.withoutfxml.Player class extend BorderPane
// in order to divide the media
// player into regions
public class Player extends BorderPane {

    private final Media media;
    private final MediaPlayer player;
    private final MediaView view;
    private final Pane mpane;
    private final MediaBar bar;

    // Default constructor
    public Player(String file) {
        media = new Media(file);
        player = new MediaPlayer(media);
        view = new MediaView(player);
        mpane = new Pane();
        mpane.getChildren().add(view); // Calling the function getChildren

        // inorder to add the view
        setCenter(view);
        bar = new MediaBar(player); // Passing the player to leeny.edu.withoutfxml.MediaBar
        setBottom(bar); // Setting the leeny.edu.withoutfxml.MediaBar at bottom
        setStyle("-fx-background-color:#bfc2c7"); // Adding color to the mediabar
        player.play(); // Making the video play
    }

    public MediaPlayer getPlayer() {
        return player;
    }
}