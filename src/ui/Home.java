package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class Home {

    @FXML
    private Button addActivity;

    @FXML
    void goToNewActivity(MouseEvent event) {
        System.out.println(":P");
    }
}