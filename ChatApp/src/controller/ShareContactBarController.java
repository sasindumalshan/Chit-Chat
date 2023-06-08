package controller;

import com.jfoenix.controls.JFXCheckBox;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class ShareContactBarController {

    public Text txtName;
    private  String port;
    public JFXCheckBox select;

    public static ArrayList<String> shareContact =new ArrayList<>();

    public void setData(String name,String port){
        txtName.setText(name);
        String[] split = port.split("User");
        this.port=split[1];
        System.out.println(this.port);
    }

    public void contactClickOnMouseClick(MouseEvent mouseEvent) {
       /* DashboardController.port=ShareContactBarController.port;
        DashboardController.getInstance().txtName.setText(txtName.getText());
        DashboardController.getInstance().vBox.getChildren().clear();
        DashboardController.getInstance().txtUId.setText("User"+port);*/
    }

    public void selectOnAction(ActionEvent actionEvent) {
        if (select.isSelected()){
            shareContact.add(port);
        }else {
            shareContact.remove(port);
        }
        System.out.println(shareContact.size());
    }
}
