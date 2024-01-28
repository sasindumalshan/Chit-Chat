package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import util.Navigation;

import java.io.IOException;

public class LoginController {
    public JFXTextField txtUser;

    public void OkOnAction(ActionEvent actionEvent) {
        try {
            Navigation.popupNavigation("Dashboard.fxml");
            DashboardController.getInstance().txtUser.setText(txtUser.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
