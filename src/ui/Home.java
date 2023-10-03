package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Home {

    @FXML
    private Button addActivity;

    @FXML
    void goToNewActivity(MouseEvent event) throws IOException {
        Stage stage = (Stage) addActivity.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("new-activity.fxml")));
        stage.setTitle("Task & Reminder Management :P");
        stage.setScene(new Scene(root));
    }
}