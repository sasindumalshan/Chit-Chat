package view;

import com.jfoenix.controls.JFXTextField;
import controller.ContactBarController;
import controller.DashboardController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import sample.BarController;

import java.io.IOException;

public class AddNewContactController {
    public JFXTextField txtUserNAme;
    public JFXTextField txtName;

    public void addNewContactOnAction(ActionEvent actionEvent) {
        String[] contact={txtName.getText(),txtUserNAme.getText()};
        DashboardController.contactList.add(contact);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ContactBar.fxml"));
            Parent root = loader.load();
            ContactBarController controller = loader.getController();
            controller.setData(txtName.getText(),txtUserNAme.getText());
            DashboardController.getInstance().contactVbox.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
