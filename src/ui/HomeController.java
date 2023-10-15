package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Activity;
import structure.Nodes.Node;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML private Button addActivity;
    @FXML private Button editActivity;
    @FXML private Button deleteActivity;
    @FXML private Button undo;

    @FXML private TableView<Activity> highTasks = new TableView<>();
    @FXML private TableColumn<Activity, String> highDate;
    @FXML private TableColumn<Activity, String> highDescription;
    @FXML private TableColumn<Activity, String> highTitle;

    @FXML private TableView<Activity> mediumTasks = new TableView<>();
    @FXML private TableColumn<Activity, String> mediumDate;
    @FXML private TableColumn<Activity, String> mediumDescription;
    @FXML private TableColumn<Activity, String> mediumTitle;

    @FXML private TableView<Activity> lowTasks = new TableView<>();
    @FXML private TableColumn<Activity, String> lowDate;
    @FXML private TableColumn<Activity, String> lowDescription;
    @FXML private TableColumn<Activity, String> lowTitle;

    @FXML private TableView<Activity> nonPriorityTasks = new TableView<>();
    @FXML private TableColumn<Activity, String> tasksDate;
    @FXML private TableColumn<Activity, String> tasksDescription;
    @FXML private TableColumn<Activity, String> tasksTitle;

    @FXML private TableView<Activity> reminders = new TableView<>();
    @FXML private TableColumn<Activity, String> reminderDate;
    @FXML private TableColumn<Activity, String> reminderDescription;
    @FXML private TableColumn<Activity, String> reminderTitle;

    private TableView<Activity> selectedTable;

    @FXML
    void addActivity(MouseEvent event) throws IOException {
        Stage stage = (Stage) addActivity.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("new-activity.fxml"));
        Parent root = loader.load();
        stage.setTitle("Task & Reminder Management :P");
        stage.setScene(new Scene(root));
    }

    @FXML
    void editActivity(MouseEvent event) throws IOException {
        if (selectedTable != null) {
            Activity selectedActivity = selectedTable.getSelectionModel().getSelectedItem();
            if (selectedActivity != null) {
                Stage stage = (Stage) editActivity.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("new-activity.fxml"));
                Parent root = loader.load();
                ActivityController activityController = loader.getController();
                activityController.setActivityToEdit(selectedActivity);
                stage.setScene(new Scene(root));
                stage.setTitle("Task & Reminder Management :P");
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Please select an activity to edit.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Please select an activity to edit.");
            alert.showAndWait();
        }
    }

    @FXML
    void deleteActivity(MouseEvent event) throws IOException {
        if (selectedTable != null) {
            Activity selectedActivity = selectedTable.getSelectionModel().getSelectedItem();
            if (selectedActivity != null) {
                Main.vc.deleteActivity(selectedActivity);
                listReminders();
                listTasks();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Please select an activity to delete.");
                alert.showAndWait();
            }
            listTasks();
            listReminders();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Please select an activity to delete.");
            alert.showAndWait();
        }
    }


    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listReminders();
        listTasks();

        highTasks.setOnMouseClicked(event -> selectedTable = highTasks);
        mediumTasks.setOnMouseClicked(event -> selectedTable = mediumTasks);
        lowTasks.setOnMouseClicked(event -> selectedTable = lowTasks);
        nonPriorityTasks.setOnMouseClicked(event -> selectedTable = nonPriorityTasks);
        reminders.setOnMouseClicked(event -> selectedTable = reminders);
    }

    private void listReminders(){
        ObservableList<Activity> reminder = FXCollections.observableArrayList();
        Node<Activity> reminderQ = Main.vc.getReminderQueue();

        while(reminderQ!=null) {
            reminder.add(reminderQ.getData());
            reminderQ = reminderQ.getNext();
        }

        reminderTitle.setCellValueFactory(new PropertyValueFactory<>("tittle"));
        reminderDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        reminderDate.setCellValueFactory(new PropertyValueFactory<>("formattedDate"));
        reminders.setItems(reminder);
    }

    private void listTasks(){
        ObservableList<Activity> nonTask = FXCollections.observableArrayList();
        Node<Activity> taskQ = Main.vc.getTaskQueue();

        while(taskQ!=null) {
            nonTask.add(taskQ.getData());
            taskQ = taskQ.getNext();
        }

        tasksTitle.setCellValueFactory(new PropertyValueFactory<>("tittle"));
        tasksDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tasksDate.setCellValueFactory(new PropertyValueFactory<>("formattedDate"));
        nonPriorityTasks.setItems(nonTask);

        ObservableList<Activity> highTaskList = FXCollections.observableArrayList();
        List<Activity> highT = Main.vc.getHighTasks();

        for (Activity entry : highT) {
            highTaskList.add(entry);
        }

        highTitle.setCellValueFactory(new PropertyValueFactory<>("tittle"));
        highDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        highDate.setCellValueFactory(new PropertyValueFactory<>("formattedDate"));
        highTasks.setItems(highTaskList);

        ObservableList<Activity> mediumTaskList = FXCollections.observableArrayList();
        List<Activity> mediumT = Main.vc.getMediumTasks();

        for (Activity entry : mediumT) {
            mediumTaskList.add(entry);
        }

        mediumTitle.setCellValueFactory(new PropertyValueFactory<>("tittle"));
        mediumDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        mediumDate.setCellValueFactory(new PropertyValueFactory<>("formattedDate"));
        mediumTasks.setItems(mediumTaskList);

        ObservableList<Activity> lowTaskList = FXCollections.observableArrayList();
        List<Activity> lowT = Main.vc.getLowTasks();

        for (Activity entry : lowT) {
            lowTaskList.add(entry);
        }

        lowTitle.setCellValueFactory(new PropertyValueFactory<>("tittle"));
        lowDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        lowDate.setCellValueFactory(new PropertyValueFactory<>("formattedDate"));
        lowTasks.setItems(lowTaskList);
    }

    @FXML
    void undoAction(MouseEvent event) {
        Main.vc.undoAction();
        listReminders();
        listTasks();
    }
}