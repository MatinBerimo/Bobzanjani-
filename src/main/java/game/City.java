package game;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class City {
    private Canvas mapCanvas;
    private List<Tile> tiles; // List to store all the tiles
    private double tileWidth, tileHeight; // Tile dimensions
    private int cols, rows; // Grid dimensions
    private Tile selectedTile; // Currently selected tile
    private Image grassTileImage; // The grass tile image

    public City() {
        this.tileWidth = 32; // Example tile size (32x32)
        this.tileHeight = 32;
        this.cols = 50; // 50 columns
        this.rows = 40; // 40 rows
        this.grassTileImage = new Image("file:/D:/Programming/Repos/Bobzanjani/src/main/resources/images/grass.png"); // Path to your grass tile image

        this.mapCanvas = new Canvas(cols * tileWidth, rows * tileHeight);
        this.tiles = new ArrayList<>();
        this.selectedTile = null;

        // Create the grid of tiles
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                // Pass the grass tile image to each Tile
                tiles.add(new Tile(x * (int) tileWidth, y * (int) tileHeight, tileWidth, tileHeight, grassTileImage));
            }
        }

        // Draw the map
        drawCityMap();

        // Set up mouse click handler to select tiles
        mapCanvas.setOnMouseClicked(this::handleTileClick);
    }

    public Canvas getMapCanvas() {
        return mapCanvas;
    }

    // This method will be called every time the canvas is resized
    public void drawCityMap() {
        GraphicsContext gc = mapCanvas.getGraphicsContext2D();

        // Clear the canvas before redrawing
        gc.clearRect(0, 0, mapCanvas.getWidth(), mapCanvas.getHeight());

        // Draw all tiles
        for (Tile tile : tiles) {
            tile.draw(gc);
        }
    }

    // Handle tile selection
    private void handleTileClick(MouseEvent event) {
        // Get the position of the click relative to the canvas
        double clickX = event.getX();
        double clickY = event.getY();

        // Find the tile that was clicked
        for (Tile tile : tiles) {
            if (clickX >= tile.getX() && clickX <= tile.getX() + tile.getWidth() &&
                clickY >= tile.getY() && clickY <= tile.getY() + tile.getHeight()) {
                // Change the selection color of the clicked tile to indicate selection
                if (selectedTile != null) {
                    selectedTile.setSelectionColor(Color.TRANSPARENT); // Reset previous tile color
                }
                tile.setSelectionColor(Color.YELLOW); // Set the color of the selected tile
                selectedTile = tile; // Update the selected tile
                drawCityMap(); // Redraw the map with updated selection
                break;
            }
        }
    }
}
