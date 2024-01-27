package controller;

import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.util.Arrays;

public class ContactBarController {
    public Text txtName;
    private  String port;
    public void setData(String name,String port){
        System.out.println(name);
        System.out.println(port);
        txtName.setText(name);
        String[] split = port.split("User");
        System.out.println(Arrays.toString(split));
        this.port=split[1];
        System.out.println(this.port);
    }

    public void contactClickOnMouseClick(MouseEvent mouseEvent) {
        DashboardController.port=this.port;
        DashboardController.getInstance().txtName.setText(txtName.getText());
        DashboardController.getInstance().vBox.getChildren().clear();
        DashboardController.getInstance().txtUId.setText("User"+port);
    }
}
