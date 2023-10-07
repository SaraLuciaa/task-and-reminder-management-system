package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.VersionController;

import java.io.IOException;

public class Main extends Application {
    public static VersionController vc = new VersionController();

    public static void main(String[] args) {
        /*VersionController vc = new VersionController();
        Calendar date = Calendar.getInstance();
        vc.addActivity("Bleh", "Soy una mariposa 8:8", date);
        vc.getReminderQueue();*/
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("task-reminder.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 820, 620);
        stage.setTitle("Task & Reminder Management :P");
        stage.setScene(scene);
        stage.show();
    }
}