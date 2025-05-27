package game;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        City city = new City();
        root.getChildren().add(city.getMapCanvas());

        Scene scene = new Scene(root, 1600, 1200);
        primaryStage.setTitle("Fantasy City Builder");

        primaryStage.setFullScreen(true);  // Game runs in fullscreen mode
        primaryStage.setFullScreenExitHint(""); // Disable fullscreen exit hint
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
