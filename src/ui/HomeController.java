package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.VersionController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    private VersionController versionController;

    public HomeController(){
        versionController = new VersionController();
    }

    @FXML
    private Button addActivity;

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