package game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Map {
    private Tile[][] tiles;
    private int width, height;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new Tile[width][height];
        generateMap();
    }

    private void generateMap() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                // همه Tile ها به طور پیش‌فرض چمن هستند
                tiles[x][y] = new Tile("grass", true); // نوع زمین به "چمن" تنظیم شده
            }
        }
    }

    public Tile getTile(int x, int y) {
        return tiles[x][y];
    }

    public void displayMap(GridPane gridPane) {
        gridPane.getChildren().clear();
        Image tileImage = new Image("file:D:/Programming/Repos/Bobzanjani/src/main/resources/images/grasss.png");  // مسیر تصویر چمن

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                ImageView imageView = new ImageView(tileImage);
                imageView.setFitWidth(40);  // اندازه سلول
                imageView.setFitHeight(40);

                gridPane.add(imageView, x, y);  // اضافه کردن تصویر به GridPane
            }
        }
    }
}
