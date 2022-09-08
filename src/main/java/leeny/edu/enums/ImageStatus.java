package leeny.edu.enums;

import javafx.scene.image.Image;

public enum ImageStatus {
    PLAY(new Image("/icons/play.png")),
    PAUSE(new Image("/icons/pause.png")),
    VOLUME_ON(new Image("/icons/volume_on.png")),
    VOLUME_OFF(new Image("/icons/volume_off.png"));


    private final Image image;

    ImageStatus(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }
}
