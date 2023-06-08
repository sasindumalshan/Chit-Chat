package controller;

import ciant.GroupClient;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class AddNewGroupController {

    private static AddNewGroupController addNewGroupController;

    public AddNewGroupController(){
        addNewGroupController=this;
    }
    public static AddNewGroupController getInstance(){
        return addNewGroupController;
    }

    public JFXTextField txtName;

    public void addNewContactOnAction(ActionEvent actionEvent) {
        GroupClient groupClient=new GroupClient();
        groupClient.newGroup();
    }
}
