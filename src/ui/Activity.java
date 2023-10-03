package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Activity {

    @FXML
    private ToggleGroup CategoryTask;

    @FXML
    private ToggleGroup PriorityLevel;

    @FXML
    private DatePicker date;

    @FXML
    private TextArea description;

    @FXML
    private Button formActivity;

    @FXML
    private Button goBack;

    @FXML
    private ToggleGroup isTask;

    @FXML
    private TextField title;

    @FXML
    void goHome(MouseEvent event) throws IOException {
        Stage stage = (Stage) goBack.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("task-reminder.fxml"));
        stage.setTitle("Task & Reminder Management :P");
        stage.setScene(new Scene(root));
    }

    @FXML
    void saveActivity(MouseEvent event) {

    }

}