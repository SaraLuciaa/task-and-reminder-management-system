package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
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