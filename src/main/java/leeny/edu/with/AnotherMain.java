package leeny.edu.with;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AnotherMain extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        stage.setTitle("Film together");
        stage.setScene(new Scene(root, 300, 275)); // Setting the scene to stage
        stage.show(); // Showing the stage
    }

    public static void main(String[] args) {
        launch(args);
    }
}
