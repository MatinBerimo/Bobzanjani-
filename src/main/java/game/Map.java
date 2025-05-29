package game;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Map {
    private Tile[][] tiles;
    private int width, height;
    private Rectangle previousRectangle;  // Keep track of the previous selected tile
    private int previousX = -1, previousY = -1; // Store the coordinates of the previous selected tile

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new Tile[width][height];
        generateMap();
    }

    private void generateMap() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                // All tiles are set to grass by default
                tiles[x][y] = new Tile("grass", true);
            }
        }
    }

    public Tile getTile(int x, int y) {
        return tiles[x][y];
    }

    public void displayMap(GridPane gridPane) {
        gridPane.getChildren().clear();
        Image tileImage = new Image("file:D:/Programming/Repos/Bobzanjani/src/main/resources/images/grasss.png");

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                ImageView imageView = new ImageView(tileImage);
                imageView.setFitWidth(40);
                imageView.setFitHeight(40);

                final int finalX = x;
                final int finalY = y;
                imageView.setOnMouseClicked(event -> {
                    selectTile(gridPane, finalX, finalY); // Select the tile when clicked
                });

                gridPane.add(imageView, x, y);
            }
        }
    }

    public void selectTile(GridPane gridPane, int x, int y) {
        // Only remove the rectangle from the last selected tile
        if (previousRectangle != null && previousX != x || previousY != y) {
            gridPane.getChildren().remove(previousRectangle);
        }

        // Create a new rectangle for the selected tile
        previousRectangle = new Rectangle(40, 40, Color.GRAY.deriveColor(0, 1, 1, 0.5)); // Gray with 50% transparency
        gridPane.add(previousRectangle, x, y);

        // Update the previous selected tile coordinates
        previousX = x;
        previousY = y;
    }
}
