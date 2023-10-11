package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Activity;
import structure.Nodes.Node;
import structure.Queue.Entry;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML private Button addActivity;

    @FXML private TableView<Activity> highTasks = new TableView<>();
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

    @FXML private TableView<Activity> nonPriorityTasks = new TableView<>();
    @FXML private TableColumn<Activity, Calendar> tasksDate;
    @FXML private TableColumn<Activity, String> tasksDescription;
    @FXML private TableColumn<Activity, String> tasksTitle;

    @FXML private TableView<Activity> reminders = new TableView<>();
    @FXML private TableColumn<Activity, Calendar> reminderDate;
    @FXML private TableColumn<Activity, String> reminderDescription;
    @FXML private TableColumn<Activity, String> reminderTitle;

    @FXML
    void goToNewActivity(MouseEvent event) throws IOException {
        Stage stage = (Stage) addActivity.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("new-activity.fxml"));
        Parent root = loader.load();
        stage.setTitle("Task & Reminder Management :P");
        stage.setScene(new Scene(root));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listReminders();
        listTasks();
    }

    public void listReminders(){
        ObservableList<Activity> reminder = FXCollections.observableArrayList();
        Node<Activity> reminderQ = Main.vc.getReminderQueue();

        while(reminderQ!=null) {
            reminder.add(reminderQ.getData());
            reminderQ = reminderQ.getNext();
        }

        reminderTitle.setCellValueFactory(new PropertyValueFactory<>("tittle"));
        reminderDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        reminderDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        reminders.setItems(reminder);
    }

    public void listTasks(){
        ObservableList<Activity> nonTask = FXCollections.observableArrayList();
        Node<Activity> taskQ = Main.vc.getTaskQueue();

        while(taskQ!=null) {
            nonTask.add(taskQ.getData());
            taskQ = taskQ.getNext();
        }

        tasksTitle.setCellValueFactory(new PropertyValueFactory<>("tittle"));
        tasksDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tasksDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        nonPriorityTasks.setItems(nonTask);

        ObservableList<Activity> highTaskList = FXCollections.observableArrayList();
        List<Entry<Activity>> highT = Main.vc.getHighTasks();

        for (Entry<Activity> entry : highT) {
            highTaskList.add(entry.getItem());
            System.out.println(entry.getItem().toString());
        }

        highTitle.setCellValueFactory(new PropertyValueFactory<>("tittle"));
        highDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        highDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        highTasks.setItems(highTaskList);
    }
}