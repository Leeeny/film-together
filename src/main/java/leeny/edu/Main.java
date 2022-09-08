package leeny.edu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private Parent rootNode;

    @Override
    public void init() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/sample.fxml"));
        rootNode = fxmlLoader.load();
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Film together");

        Scene scene = new Scene(rootNode, 1000, 750);
        scene.setRoot(rootNode);
        stage.setScene(scene);

        stage.setMinWidth(800);
        stage.setMinHeight(600);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
