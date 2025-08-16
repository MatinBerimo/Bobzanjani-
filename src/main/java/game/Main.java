package game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.BorderPane;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Initialize resources (money, materials, energy, and population)
        ResourceManager resources = new ResourceManager(500, 100, 50, 10); // Initial resources

        // Create a new map of 100x100 tiles and pass the resources object
        Map map = new Map(100, 100, resources); // Pass the resources object

        // Create the GridPane for displaying the map
        javafx.scene.layout.GridPane gridPane = new javafx.scene.layout.GridPane();
        map.displayMap(gridPane);

        // Create a resource bar (HBox) - keep this at the top
        javafx.scene.layout.HBox resourceBar = new javafx.scene.layout.HBox(20); // 20px spacing
        resourceBar.setStyle("-fx-background-color: #222; -fx-padding: 10;");
        resourceBar.setAlignment(javafx.geometry.Pos.CENTER_LEFT);

        // Add resource displays using your helper method
        resourceBar.getChildren().setAll(
            createResourceDisplay("money.png", resources.get(ResourceType.MONEY)),
            createResourceDisplay("materials.png", resources.get(ResourceType.MATERIALS)),
            createResourceDisplay("energy.png", resources.get(ResourceType.ENERGY)),
            createResourceDisplay("population.png", resources.get(ResourceType.POPULATION))
        );

        // Create the building selection bar (HBox) at the bottom
        javafx.scene.layout.HBox buildingSelectionBar = new javafx.scene.layout.HBox(15); // 15px spacing
        buildingSelectionBar.setStyle("-fx-background-color: #333; -fx-padding: 10;");
        buildingSelectionBar.setAlignment(javafx.geometry.Pos.CENTER);

        // Building selection buttons (add them here)
        // Example:
        javafx.scene.control.Button houseButton = new javafx.scene.control.Button("House");
        houseButton.setOnAction(e -> map.setSelectedBuildingType(BuildingType.HOUSE));
        buildingSelectionBar.getChildren().add(houseButton);

        javafx.scene.control.Button roadButton = new javafx.scene.control.Button("Road");
        roadButton.setOnAction(e -> map.setSelectedBuildingType(BuildingType.ROAD));
        buildingSelectionBar.getChildren().add(roadButton);

        javafx.scene.control.Button parkButton = new javafx.scene.control.Button("Park");
        parkButton.setOnAction(e -> map.setSelectedBuildingType(BuildingType.PARK));
        buildingSelectionBar.getChildren().add(parkButton);

        // Factory button (from previous request)
        javafx.scene.control.Button factoryButton = new javafx.scene.control.Button("Factory");
        factoryButton.setOnAction(e -> map.setSelectedBuildingType(BuildingType.FACTORY));
        buildingSelectionBar.getChildren().add(factoryButton);

        // Main layout using BorderPane
        BorderPane root = new BorderPane();
        root.setTop(resourceBar);
        root.setCenter(gridPane);
        root.setBottom(buildingSelectionBar);

        // Update resources every second (simulate daily update or timer)
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            // resources.add(ResourceType.MONEY, 100);
            // resources.add(ResourceType.ENERGY, -10);
            // Cap energy at 200
            if (resources.get(ResourceType.ENERGY) > 200) {
                resources.set(ResourceType.ENERGY, 200);
            }
            // Update the resource bar after resources change
            resourceBar.getChildren().setAll(
                createResourceDisplay("money.png", resources.get(ResourceType.MONEY)),
                createResourceDisplay("materials.png", resources.get(ResourceType.MATERIALS)),
                createResourceDisplay("energy.png", resources.get(ResourceType.ENERGY)),
                createResourceDisplay("population.png", resources.get(ResourceType.POPULATION))
            );
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        // Add a Timeline for house effects every minute
        Timeline houseEffectTimeline = new Timeline(new KeyFrame(Duration.seconds(60), e -> {
            int houseCount = 0;
            for (int x = 0; x < 100; x++) {
                for (int y = 0; y < 100; y++) {
                    Tile tile = map.getTile(x, y);
                    if (tile.getBuilding() != null && tile.getBuilding().buildingType == BuildingType.HOUSE) {
                        houseCount++;
                    }
                }
            }
            resources.add(ResourceType.POPULATION, 10 * houseCount);
            resources.add(ResourceType.ENERGY, 50 * houseCount);
            // Cap energy at 200
            if (resources.get(ResourceType.ENERGY) > 200) {
                resources.set(ResourceType.ENERGY, 200);
            }
            // Update the resource bar after resources change
            resourceBar.getChildren().setAll(
                createResourceDisplay("money.png", resources.get(ResourceType.MONEY)),
                createResourceDisplay("materials.png", resources.get(ResourceType.MATERIALS)),
                createResourceDisplay("energy.png", resources.get(ResourceType.ENERGY)),
                createResourceDisplay("population.png", resources.get(ResourceType.POPULATION))
            );
        }));
        houseEffectTimeline.setCycleCount(Timeline.INDEFINITE);
        houseEffectTimeline.play();

        // Add a Timeline for factory effects every 2 minutes
        Timeline factoryEffectTimeline = new Timeline(new KeyFrame(Duration.minutes(2), e -> {
            int factoryCount = 0;
            for (int x = 0; x < 100; x++) {
                for (int y = 0; y < 100; y++) {
                    Tile tile = map.getTile(x, y);
                    if (tile.getBuilding() != null && tile.getBuilding().buildingType == BuildingType.FACTORY) {
                        factoryCount++;
                    }
                }
            }
            resources.add(ResourceType.MONEY, 200 * factoryCount);
            resources.add(ResourceType.MATERIALS, 100 * factoryCount);
            resources.add(ResourceType.ENERGY, -20 * factoryCount);
            resources.add(ResourceType.POPULATION, -5 * factoryCount);
            // Cap energy at 200
            if (resources.get(ResourceType.ENERGY) > 200) {
                resources.set(ResourceType.ENERGY, 200);
            }
            // Update the resource bar after resources change
            resourceBar.getChildren().setAll(
                createResourceDisplay("money.png", resources.get(ResourceType.MONEY)),
                createResourceDisplay("materials.png", resources.get(ResourceType.MATERIALS)),
                createResourceDisplay("energy.png", resources.get(ResourceType.ENERGY)),
                createResourceDisplay("population.png", resources.get(ResourceType.POPULATION))
            );
        }));
        factoryEffectTimeline.setCycleCount(Timeline.INDEFINITE);
        factoryEffectTimeline.play();

        // Set the scene and show the stage
        Scene scene = new Scene(root, 800, 800); // Set scene with BorderPane as root
        primaryStage.setTitle("City Building Simulator");
        scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Helper method to create a resource display (icon + value)
    private javafx.scene.layout.HBox createResourceDisplay(String iconFile, int value) {
        ImageView icon = new ImageView(new Image(getClass().getResourceAsStream("/images/" + iconFile)));
        icon.setFitWidth(24);
        icon.setFitHeight(24);
        Label valueLabel = new Label(String.valueOf(value));
        valueLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        valueLabel.setStyle("-fx-text-fill: #fff;");
        javafx.scene.layout.HBox box = new javafx.scene.layout.HBox(5, icon, valueLabel);
        box.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        return box;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
