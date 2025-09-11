package game;

import javafx.application.Platform;
import javafx.scene.control.Alert;

/**
 * Utility class for displaying alerts in the JavaFX application.
 */
public final class AlertHandler {

    private AlertHandler() {
        // Prevent instantiation
    }

    /**
     * Shows an informational alert with the given message.
     *
     * @param message the message to display
     */
    public static void showAlert(String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Resource Alert");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }
}
