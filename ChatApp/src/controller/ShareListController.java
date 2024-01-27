package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShareListController implements Initializable {
    public VBox contactVbox;

    public void OkOnAction(ActionEvent actionEvent) {
        for (int i = 0; i <ShareContactBarController.shareContact.size(); i++) {
            String text = DashboardController.getInstance().txtUId.getText();
            String[] split = text.split("User");
            DashboardController.getInstance().sendInFo(ShareContactBarController.shareContact.get(i),"chitChat://30000:"+split[1]);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int i = 0; i < DashboardController.contactList.size(); i++) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ShareContactBar.fxml"));
                Parent root = loader.load();
                ShareContactBarController controller = loader.getController();
                controller.setData(DashboardController.contactList.get(i)[0],DashboardController.contactList.get(i)[1]);
                contactVbox.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
