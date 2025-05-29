package game;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    private Building selectedBuilding; // Store the currently selected building

    @Override
    public void start(Stage primaryStage) {
        // Create a new map of 100x100 tiles
        Map map = new Map(100, 100); // Set the map size to 100x100

        // Create the GridPane for displaying the map
        GridPane gridPane = new GridPane();
        map.displayMap(gridPane);

        // Create a StackPane to overlay the grid and control panel
        StackPane mainLayout = new StackPane();
        mainLayout.getChildren().addAll(gridPane); // Only add the grid to the layout

        // Set the scene and show the stage
        Scene scene = new Scene(mainLayout, 800, 800); // Adjust window size for 100x100 grid
        primaryStage.setTitle("City Building Simulator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
