package game;

import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Map {
    private Tile[][] tiles;
    private int width, height;
    private Rectangle previousRectangle;
    private int previousX = -1, previousY = -1;
    private Building selectedBuilding; // Reference to the selected building

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new Tile[width][height];
        generateMap();
    }

    private void generateMap() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = new Tile("grass", true); // Default to grass tiles that are buildable
            }
        }
    }

    public Tile getTile(int x, int y) {
        return tiles[x][y];
    }

    public void displayMap(GridPane gridPane) {
        gridPane.getChildren().clear();
        Image tileImage = new Image("file:D:/Programming/Repos/Bobzanjani/src/main/resources/images/grasss.png");

        // Loop over each tile to add to the grid
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                ImageView imageView = new ImageView(tileImage);
                imageView.setFitWidth(40); // Each tile is 40x40 pixels
                imageView.setFitHeight(40);

                final int finalX = x;
                final int finalY = y;

                // Set context menu on right-click
                imageView.setOnContextMenuRequested(event -> {
                    showBuildingMenu(finalX, finalY, gridPane);
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

    public void showBuildingMenu(int x, int y, GridPane gridPane) {
        // Create a ContextMenu to display building options
        ContextMenu buildingMenu = new ContextMenu();

        // Create MenuItems for different building types with icons
        MenuItem houseItem = new MenuItem("Place House");
        houseItem.setGraphic(createIcon("file:D:/Programming/Repos/Bobzanjani/src/main/resources/images/house.png"));
        houseItem.setOnAction(e -> {
            selectedBuilding = new Building("House", 100, "Residential", true);
            placeBuilding(gridPane, x, y);
        });

        MenuItem roadItem = new MenuItem("Place Road");
        roadItem.setGraphic(createIcon("file:D:/Programming/Repos/Bobzanjani/src/main/resources/images/road.png"));
        roadItem.setOnAction(e -> {
            selectedBuilding = new Building("Road", 50, "Infrastructure", true);
            placeBuilding(gridPane, x, y);
        });

        MenuItem parkItem = new MenuItem("Place Park");
        parkItem.setGraphic(createIcon("file:D:/Programming/Repos/Bobzanjani/src/main/resources/images/park.png"));
        parkItem.setOnAction(e -> {
            selectedBuilding = new Building("Park", 30, "Recreational", true);
            placeBuilding(gridPane, x, y);
        });

        // Add the MenuItems to the ContextMenu
        buildingMenu.getItems().addAll(houseItem, roadItem, parkItem);

        // Show the ContextMenu at the position of the tile
        buildingMenu.show(gridPane, x * 40, y * 40); // Position the menu at the clicked tile
    }
    private ImageView createIcon(String imagePath) {
        Image image = new Image(imagePath);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(20);  // Set the size of the icon
        imageView.setFitHeight(20); // Set the size of the icon
        return imageView;
    }

    public void placeBuilding(GridPane gridPane, int x, int y) {
        Tile tile = tiles[x][y];

        // Check if the tile is buildable and doesn't already have a building
        if (tile.isBuildable() && tile.getBuilding() == null) {
            tile.placeBuilding(selectedBuilding); // Place the selected building on the tile

            // Update the tile's image to reflect the building placement
            Image buildingImage = new Image("file:D:/Programming/Repos/Bobzanjani/src/main/resources/images/" + selectedBuilding.getName().toLowerCase() + ".png");
            for (Node node : gridPane.getChildren()) {
                if (GridPane.getRowIndex(node) == y && GridPane.getColumnIndex(node) == x) {
                    ((ImageView) node).setImage(buildingImage); // Update image to reflect the house
                }
            }
        } else {
            System.out.println("Cannot build here! Tile is not buildable or already has a building.");
        }
    }
}
