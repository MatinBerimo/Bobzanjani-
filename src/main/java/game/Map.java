package game;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.EnumMap;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;

public class Map {
    private Tile[][] tiles;
    private int width, height;
    private Rectangle previousRectangle;
    private BuildingType selectedBuildingType; // Reference to the selected building type (instead of Building)
    private ResourceManager resources; // Reference to the resources class

    public Map(int width, int height, ResourceManager resources) {
        this.width = width;
        this.height = height;
        this.tiles = new Tile[width][height];
        this.resources = resources; // Initialize the resources
        generateMap();
    }

    private void generateMap() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = new Tile("grass", true); // Default to grass tiles that are buildable
            }
        }
    }
    //commit test

    public Tile getTile(int x, int y) {
        return tiles[x][y];
    }

    public void displayMap(GridPane gridPane) {
        gridPane.getChildren().clear();
        Image tileImage = new Image(getClass().getResourceAsStream("/images/grasss.png"));

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                ImageView imageView = new ImageView(tileImage);
                imageView.setFitWidth(40); // Each tile is 40x40 pixels
                imageView.setFitHeight(40);

                final int finalX = x;
                final int finalY = y;

                // Set on-click event to place building using the selectedBuildingType
                imageView.setOnMouseClicked(event -> {
                    if (event.getButton() == MouseButton.SECONDARY) { // Right-click
                        ContextMenu contextMenu = new ContextMenu();
                        Tile tile = tiles[finalX][finalY];
                        if (tile.getBuilding() == null && tile.isBuildable()) {
                            MenuItem buildMenu = new MenuItem("Build");
                            ContextMenu buildSubMenu = new ContextMenu();
                            for (BuildingType type : BuildingType.values()) {
                                MenuItem typeItem = new MenuItem(type.name().charAt(0) + type.name().substring(1).toLowerCase());
                                typeItem.setOnAction(e -> {
                                    placeBuilding(gridPane, finalX, finalY, type);
                                });
                                buildSubMenu.getItems().add(typeItem);
                            }
                            buildMenu.setOnAction(e -> buildSubMenu.show(imageView, event.getScreenX(), event.getScreenY()));
                            contextMenu.getItems().add(buildMenu);
                        }
                        if (tile.getBuilding() != null) {
                            MenuItem upgradeItem = new MenuItem("Upgrade");
                            upgradeItem.setOnAction(e -> {
                                upgradeBuilding(gridPane, finalX, finalY);
                            });
                            contextMenu.getItems().add(upgradeItem);
                        }
                        MenuItem infoItem = new MenuItem("Show Info");
                        infoItem.setOnAction(e -> {
                            showTileInfo(finalX, finalY);
                        });
                        contextMenu.getItems().add(infoItem);
                        if (tile.getBuilding() != null) {
                            MenuItem demolishItem = new MenuItem("Demolish");
                            demolishItem.setOnAction(e -> {
                                removeBuilding(gridPane, finalX, finalY);
                            });
                            contextMenu.getItems().add(demolishItem);
                        }
                        MenuItem cancelItem = new MenuItem("Cancel");
                        cancelItem.setOnAction(e -> contextMenu.hide());
                        contextMenu.getItems().add(cancelItem);
                        contextMenu.show(imageView, event.getScreenX(), event.getScreenY());
                    } else if (event.getButton() == MouseButton.PRIMARY) { // Left-click
                        if (selectedBuildingType != null) {
                            placeBuilding(gridPane, finalX, finalY, selectedBuildingType);
                        }
                    }
                });

                // Add the image view to the grid
                gridPane.add(imageView, x, y);
            }
        }
    }

    public void selectTile(GridPane gridPane, int x, int y) {
        if (previousRectangle != null) {
            gridPane.getChildren().remove(previousRectangle);
        }

        previousRectangle = new Rectangle(40, 40, Color.GRAY.deriveColor(0, 1, 1, 0.5)); // Gray with 50% transparency
        gridPane.add(previousRectangle, x, y);
    }

    public void placeBuilding(GridPane gridPane, int x, int y, BuildingType type) {
        Tile tile = tiles[x][y];
        if (tile.isBuildable() && tile.getBuilding() == null) {
            if (resources.canAfford(type.getBuildCost(), type.getMaterialsCost(), type.getEnergyConsumption())) {
                resources.spendResources(type.getBuildCost(), type.getMaterialsCost(), type.getEnergyConsumption());
                tile.placeBuilding(new Building(type));
                Image buildingImage = new Image(getClass().getResourceAsStream("/images/" + type.name().toLowerCase() + ".png"));
                for (Node node : gridPane.getChildren()) {
                    if (GridPane.getRowIndex(node) == y && GridPane.getColumnIndex(node) == x) {
                        ((ImageView) node).setImage(buildingImage);
                    }
                }
            } else {
                System.out.println("Not enough resources to build this building.");
            }
        } else {
            System.out.println("Cannot build here! Tile is not buildable or already has a building.");
        }
    }

    public void removeBuilding(GridPane gridPane, int x, int y) {
        Tile tile = tiles[x][y];
        if (tile.getBuilding() != null) {
            // Add resources back or whatever is appropriate for demolishing
            // For now, just remove the building and revert image to grass
            tile.placeBuilding(null); // Remove the building
            Image grassImage = new Image(getClass().getResourceAsStream("/images/grasss.png"));
            for (Node node : gridPane.getChildren()) {
                if (GridPane.getRowIndex(node) == y && GridPane.getColumnIndex(node) == x) {
                    ((ImageView) node).setImage(grassImage);
                }
            }
            System.out.println("Building at " + x + ", " + y + " demolished.");
        } else {
            System.out.println("No building to demolish at " + x + ", " + y + ".");
        }
    }

    // Stub for upgrade
    public void upgradeBuilding(GridPane gridPane, int x, int y) {
        Tile tile = tiles[x][y];
        if (tile.getBuilding() != null) {
            tile.getBuilding().upgradeBuilding(100); // Example: add 100 to cost
            System.out.println("Building at " + x + ", " + y + " upgraded.");
        } else {
            System.out.println("No building to upgrade at " + x + ", " + y + ".");
        }
    }

    // Stub for info
    public void showTileInfo(int x, int y) {
        Tile tile = tiles[x][y];
        if (tile.getBuilding() != null) {
            System.out.println("Tile (" + x + ", " + y + ") has building: " + tile.getBuilding().getClass().getSimpleName());
        } else {
            System.out.println("Tile (" + x + ", " + y + ") is empty.");
        }
    }
}
