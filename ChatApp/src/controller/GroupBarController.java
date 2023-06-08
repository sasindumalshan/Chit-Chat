package controller;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import util.Navigation;

import java.io.IOException;

public class GroupBarController {
    public Text txtName;
    private  String port;
    public void setData(String name,String port){
        txtName.setText(name);
        String[] split = port.split("User");
        this.port=split[1];
        System.out.println(port);
    }

    public void contactClickOnMouseClick(MouseEvent mouseEvent) {
        DashboardController.port=port;
        DashboardController.getInstance().txtName.setText(txtName.getText());
        DashboardController.getInstance().vBox.getChildren().clear();
        DashboardController.getInstance().txtUId.setText("G:User"+port);



        //======================================================================================

        Label text = new Label();
        text.setText("   chitChat://30000:"+ port);
        text.setStyle("    -fx-shape: \"M 800 100 Q 750 100 750 150 L 50 150 Q 0 100 50 50 Q 400 50 750 50 Z\";\n" +
                "    -fx-background-color: #71e07c;\n" +
                "    -fx-font-family: \"fantasy\";\n" +
                "    -fx-font-size: 15; -fx-padding: 10; -fx-start-margin: 200 ; -fx-text-fill: #fff");
        text.setMinWidth(200);
        final Group root = new Group();


        final GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(5));
        gridpane.setHgap(0);
        gridpane.setVgap(1);
        gridpane.minHeight(30);
        text.maxHeight(200);
        gridpane.maxHeight(200);

        //==============================================================
        Button button=new Button();
        button.setAlignment(Pos.CENTER);
        button.setMinWidth(200);
        button.setStyle("" +
                        "-fx-background-radius: 100;" +
                        "-fx-background-color: transparent;" +
                        "-fx-background-image: url(/view/assest/image/share.png);  " +
                        "-fx-background-repeat: stretch;\n" +
                        "    -fx-background-size: 13 13;\n" +
                        "    -fx-background-position: left;" +
                        "" +
                        ""

                            );


        button.setOnAction(actionEvent -> {
            try {
                Navigation.popupNavigation("ShareList.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        //==============================================================


        GridPane.setHalignment(text, HPos.CENTER);
        GridPane.setHalignment(button, HPos.LEFT);
        gridpane.add(text, 0, 0);
        gridpane.add(button, 0, 0);
        gridpane.setAlignment(Pos.CENTER_RIGHT);

        root.getChildren().add(gridpane);

        DashboardController.getInstance().vBox.getChildren().add(gridpane);
    }
}
