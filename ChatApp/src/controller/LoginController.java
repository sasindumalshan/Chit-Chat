package controller;

import javafx.event.ActionEvent;
import util.Navigation;

import java.io.IOException;

public class LoginController {
    public void OkOnAction(ActionEvent actionEvent) {
        try {
            Navigation.switchNavigation("Dashboard.fxml",actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
