package game;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class Building {
    BuildingType buildingType;
    private int customCost; // This could be modified during gameplay

    private ProgressIndicator progressIndicator; // visual timer overlay
    private Timeline resourceTimeline; // timer for generating resources

    // Constructor
    public Building(BuildingType buildingType) {
        this.buildingType = buildingType;
        this.customCost = buildingType.getBuildCost(); // Initial cost from type
    }

    // Setters and getters
    public int getCost() {
        return customCost; // Get the cost of the specific building instance
    }

    // Example of cost modification (e.g., after upgrades)
    public void upgradeBuilding(int upgradeCost) {
        this.customCost += upgradeCost; // The cost could change based on upgrades
    }

    /**
     * Starts a repeating timer that updates a circular progress indicator and
     * grants resources when the timer completes.
     */
    public void startResourceCycle(GridPane gridPane, int x, int y, ResourceManager resources) {
        double seconds;
        switch (buildingType) {
            case HOUSE -> seconds = 60;
            case FACTORY -> seconds = 120;
            default -> {
                return; // no timer for other buildings
            }
        }

        progressIndicator = new ProgressIndicator(0);
        progressIndicator.setOpacity(0.5); // transparent overlay
        progressIndicator.setMinSize(40, 40);
        progressIndicator.setMaxSize(40, 40);
        gridPane.add(progressIndicator, x, y);

        resourceTimeline = new Timeline(
            new KeyFrame(Duration.ZERO,
                new KeyValue(progressIndicator.progressProperty(), 0)),
            new KeyFrame(Duration.seconds(seconds), e -> {
                switch (buildingType) {
                    case HOUSE -> {
                        resources.add(ResourceType.POPULATION, 10);
                        resources.add(ResourceType.ENERGY, 50);
                    }
                    case FACTORY -> {
                        resources.add(ResourceType.MONEY, 200);
                        resources.add(ResourceType.MATERIALS, 100);
                        resources.add(ResourceType.ENERGY, -20);
                        resources.add(ResourceType.POPULATION, -5);
                    }
                }
                if (resources.get(ResourceType.ENERGY) > 200) {
                    resources.set(ResourceType.ENERGY, 200);
                }
            }, new KeyValue(progressIndicator.progressProperty(), 1))
        );
        resourceTimeline.setCycleCount(Timeline.INDEFINITE);
        resourceTimeline.play();
    }

    /** Stop the timer and remove the progress indicator. */
    public void stopResourceCycle(GridPane gridPane) {
        if (resourceTimeline != null) {
            resourceTimeline.stop();
        }
        if (progressIndicator != null) {
            gridPane.getChildren().remove(progressIndicator);
        }
    }
}

