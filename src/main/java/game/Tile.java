package game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Tile {
    private int x, y; // Tile's position in the grid
    private double width, height; // Tile's size
    private Image grassImage; // The image used for the grass tile
    private Color selectionColor; // Color to indicate selection

    public Tile(int x, int y, double width, double height, Image grassImage) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.grassImage = grassImage;
        this.selectionColor = Color.TRANSPARENT; // No selection color initially
    }

    // Getters and setters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public Image getGrassImage() {
        return grassImage;
    }

    public void setSelectionColor(Color selectionColor) {
        this.selectionColor = selectionColor;
    }

    // Draw the tile on the canvas
    public void draw(GraphicsContext gc) {
        // Draw the grass image as the background for the tile
        gc.drawImage(grassImage, x, y, width, height);

        // If the tile is selected, we overlay the selection color
        if (!selectionColor.equals(Color.TRANSPARENT)) {
            gc.setFill(selectionColor);
            gc.fillRect(x, y, width, height); // Overlay the selection color
        }
    }
}
