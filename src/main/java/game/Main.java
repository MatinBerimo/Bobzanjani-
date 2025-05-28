package game;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        // ایجاد نقشه با ابعاد 10x10
        Map map = new Map(10, 10);

        // ایجاد GridPane برای نمایش نقشه
        GridPane gridPane = new GridPane();
        map.displayMap(gridPane);

        // تنظیم صحنه و نمایش آن
        Scene scene = new Scene(gridPane, 400, 400);
        primaryStage.setTitle("Citybuilding Simulator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
