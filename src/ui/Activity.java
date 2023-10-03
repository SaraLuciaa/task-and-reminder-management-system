package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Activity implements Initializable {

    @FXML private TextField title;
    @FXML private TextArea description;
    @FXML private DatePicker date;

    @FXML private ToggleGroup isTask;
    @FXML private RadioButton reminder;
    @FXML private RadioButton task;

    @FXML private Label category;
    @FXML private ToggleGroup CategoryTask;
    @FXML private RadioButton non_priority;
    @FXML private RadioButton priority;

    @FXML private Label priorityLevel;
    @FXML private ToggleGroup PriorityLevel;
    @FXML private RadioButton high;
    @FXML private RadioButton medium;
    @FXML private RadioButton low;

    @FXML private Button formActivity;
    @FXML private Button goBack;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        task.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                category.setDisable(false);
                priorityLevel.setDisable(false);
                non_priority.setDisable(false);
                priority.setDisable(false);
            } else {
                category.setDisable(true);
                priorityLevel.setDisable(true);
                non_priority.setDisable(true);
                priority.setDisable(true);
                high.setDisable(true);
                medium.setDisable(true);
                low.setDisable(true);
                CategoryTask.selectToggle(null);
                PriorityLevel.selectToggle(null);
            }
        });

        priority.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                high.setDisable(false);
                medium.setDisable(false);
                low.setDisable(false);
            } else {
                high.setDisable(true);
                medium.setDisable(true);
                low.setDisable(true);
                PriorityLevel.selectToggle(null);
            }
        });
    }

    @FXML
    void goHome(MouseEvent event) throws IOException {
        Stage stage = (Stage) goBack.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("task-reminder.fxml"));
        stage.setTitle("Task & Reminder Management :P");
        stage.setScene(new Scene(root));
    }

    @FXML
    void saveActivity(MouseEvent event) {
        System.out.println(title.getText());
        System.out.println(description.getText());
        System.out.println(date.getValue());
        if(isTask.getSelectedToggle()==task){
            System.out.println(CategoryTask.getSelectedToggle());
            if(CategoryTask.getSelectedToggle()==priority){
                System.out.println(PriorityLevel.getSelectedToggle());
            }
        }
    }

    @FXML
    void showTaskProperties(ActionEvent event) {
    }
}