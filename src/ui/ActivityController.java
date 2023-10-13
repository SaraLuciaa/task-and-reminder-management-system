package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Activity;
import model.Task;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.ResourceBundle;

public class ActivityController implements Initializable {
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
    private Activity activityToEdit;

    @FXML
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("task-reminder.fxml"));
        Parent root = loader.load();
        stage.setTitle("Task & Reminder Management :P");
        stage.setScene(new Scene(root));
    }

    @FXML
    void saveActivity(MouseEvent event) {
        String message = "";

        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, date.getValue().getYear());
        calendar.set(Calendar.MONTH, date.getValue().getMonthValue() - 1);
        calendar.set(Calendar.DAY_OF_MONTH, date.getValue().getDayOfMonth());

        if(activityToEdit == null){
            if (task.isSelected()) {
                if (priority.isSelected()) {
                    message = Main.vc.addActivity(title.getText(), description.getText(), calendar,
                            true, ((RadioButton) PriorityLevel.getSelectedToggle()).getId());
                } else {
                    message = Main.vc.addActivity(title.getText(), description.getText(), calendar,
                            false, "");
                }
            } else {
                message = Main.vc.addActivity(title.getText(), description.getText(), calendar);
            }
        } else {
            if (task.isSelected()) {
                if (priority.isSelected()) {
                    message = Main.vc.editActivity(activityToEdit, title.getText(), description.getText(), calendar,
                            true, ((RadioButton) PriorityLevel.getSelectedToggle()).getId());
                } else {
                    message = Main.vc.editActivity(activityToEdit, title.getText(), description.getText(), calendar,
                            false, "");
                }
            } else {
                message = Main.vc.editActivity(activityToEdit, title.getText(), description.getText(), calendar);
            }
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(message);

        ButtonType buttonTypeA = new ButtonType("New activity");
        ButtonType buttonTypeB = new ButtonType("Go back");
        alert.getButtonTypes().setAll(buttonTypeA, buttonTypeB);

        alert.showAndWait().ifPresent(response -> {
            if (response == buttonTypeA) {
                title.clear();
                description.clear();
                date.setValue(null);
                CategoryTask.selectToggle(null);
                PriorityLevel.selectToggle(null);
                isTask.selectToggle(null);
            } else {
                try {
                    Stage stage = (Stage) goBack.getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("task-reminder.fxml"));
                    Parent root = loader.load();
                    stage.setTitle("Task & Reminder Management :P");
                    stage.setScene(new Scene(root));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void setActivityToEdit(Activity selectedActivity) {
        this.activityToEdit = selectedActivity;
        if (activityToEdit != null) {
            title.setText(activityToEdit.getTittle());
            description.setText(activityToEdit.getDescription());
            date.setValue(activityToEdit.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            if (activityToEdit instanceof Task) {
                isTask.selectToggle(task);
                if (((Task) activityToEdit).isPriority()) {
                    CategoryTask.selectToggle(priority);
                    if (((Task) activityToEdit).getPriorityLevel() == model.PriorityLevel.HIGH) {
                        PriorityLevel.selectToggle(high);
                    } else if (((Task) activityToEdit).getPriorityLevel() == model.PriorityLevel.MEDIUM) {
                        PriorityLevel.selectToggle(medium);
                    } else if (((Task) activityToEdit).getPriorityLevel() == model.PriorityLevel.LOW) {
                        PriorityLevel.selectToggle(low);
                    }
                } else {
                    reminder.setSelected(true);
                }
            } else {
                reminder.setSelected(true);
            }
        }
    }
}