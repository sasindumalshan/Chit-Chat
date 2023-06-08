package ciant;

import controller.AddNewGroupController;
import controller.ContactBarController;
import controller.DashboardController;
import controller.GroupBarController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class GroupClient {
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    String message = "";

    private Socket socket;

    public void newGroup(){
        new Thread(() -> {
            try {
                socket = new Socket("localhost", 3000);
                System.out.println("socket.getLocalPort() > " + socket.getLocalPort());
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());

                while (!message.equals("Finish")) {
                    message = dataInputStream.readUTF();
                    System.out.println(">" + message);


                    if (message.endsWith(".jpg")|message.endsWith(".png")){
                        Platform.runLater(() -> {

                            String[] split = message.split("Image ");

                            System.out.println(split[1]);
                            Image image1 = new Image(split[1], 360, 720, true, true);
                            ImageView image = new ImageView(image1);
                            final Group root = new Group();

                            final GridPane gridpane = new GridPane();
                            gridpane.setPadding(new Insets(5));
                            gridpane.setHgap(10);
                            gridpane.setVgap(10);
                            gridpane.minHeight(30);
                            gridpane.maxHeight(200);


                            GridPane.setHalignment(image, HPos.CENTER);
                            gridpane.add(image, 0, 0);
                            gridpane.setAlignment(Pos.CENTER_LEFT);

                            root.getChildren().add(gridpane);

                            DashboardController.getInstance().vBox.getChildren().add(gridpane);


                        });

                    }else {
                        Platform.runLater(() -> {
                            Label text = new Label();
                            text.setText("   " + message + "   ");
                            text.setStyle("    -fx-shape:  \"M 750 50 Q 800 100 750 150 L 50 150 Q 50 100 0 100 Q 50 50 50 50 Z\";\n" +
                                    "    -fx-background-color: #7190e0;\n" +
                                    "    -fx-font-family: \"fantasy\";\n" +
                                    "    -fx-font-size: 15; -fx-padding: 10; -fx-start-margin: 200 ; -fx-text-fill: #fff");
                            text.setMinWidth(200);
                            final Group root = new Group();

                            final GridPane gridpane = new GridPane();
                            gridpane.setPadding(new Insets(5));
                            gridpane.setHgap(10);
                            gridpane.setVgap(10);
                            gridpane.minHeight(30);
                            text.maxHeight(200);
                            gridpane.maxHeight(200);


                            GridPane.setHalignment(text, HPos.CENTER);
                            gridpane.add(text, 0, 0);
                            gridpane.setAlignment(Pos.CENTER_LEFT);

                            root.getChildren().add(gridpane);

                            DashboardController.getInstance().vBox.getChildren().add(gridpane);


                        });
                    }

                }


            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }).start();
        getPort();
    }
    public void getPort(){
        final int[] newPort = {0};
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
               while (true){
                   if (socket!=null){
                       try {
                           FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/GroupBar.fxml"));
                           Parent root = loader.load();
                           GroupBarController controller = loader.getController();
                           controller.setData(AddNewGroupController.getInstance().txtName.getText(),"User"+socket.getLocalPort());
                           Platform.runLater(() -> {
                               DashboardController.getInstance().contactVbox.getChildren().add(root);
                           });
                       } catch (IOException e) {
                           e.printStackTrace();
                       }
                       break;
                   }

               }
            }

        });
        thread.start();

    }
}
