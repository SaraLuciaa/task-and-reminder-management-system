package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

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
    void goHome(MouseEvent event) {

    }

    @FXML
    void saveActivity(MouseEvent event) {

    }

}