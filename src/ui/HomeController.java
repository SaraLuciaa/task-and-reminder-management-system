package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Activity;
import model.VersionController;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    private VersionController versionController;

    public HomeController(){
        versionController = new VersionController();
    }

    @FXML private Button addActivity;

    @FXML private TableView<Activity> highTasks;
    @FXML private TableColumn<Activity, Calendar> highDate;
    @FXML private TableColumn<Activity, String> highDescription;
    @FXML private TableColumn<Activity, String> highTitle;

    @FXML private TableView<Activity> mediumTasks;
    @FXML private TableColumn<Activity, Calendar> mediumDate;
    @FXML private TableColumn<Activity, String> mediumDescription;
    @FXML private TableColumn<Activity, String> mediumTitle;

    @FXML private TableView<Activity> lowTasks;
    @FXML private TableColumn<Activity, Calendar> lowDate;
    @FXML private TableColumn<Activity, String> lowDescription;
    @FXML private TableColumn<Activity, String> lowTitle;

    @FXML private TableView<Activity> nonPriorityTasks;
    @FXML private TableColumn<Activity, Calendar> tasksDate;
    @FXML private TableColumn<Activity, String> tasksDescription;
    @FXML private TableColumn<Activity, String> tasksTitle;

    @FXML private TableView<Activity> reminders;
    @FXML private TableColumn<Activity, Calendar> reminderDate;
    @FXML private TableColumn<Activity, String> reminderDescription;
    @FXML private TableColumn<Activity, String> reminderTitle;

    @FXML
    void goToNewActivity(MouseEvent event) throws IOException {
        Stage stage = (Stage) addActivity.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("new-activity.fxml"));
        Parent root = loader.load();
        ActivityController activity = loader.getController();
        activity.setVersionController(versionController);
        stage.setTitle("Task & Reminder Management :P");
        stage.setScene(new Scene(root));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}