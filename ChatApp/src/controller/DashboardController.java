package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import sample.BarController;
import util.Navigation;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    public static String port;
    public static ArrayList<String[]> contactList = new ArrayList<>();
    private static DashboardController dashboardController;
    public VBox contactVbox;
    public Text txtName;
    public VBox vBox;
    public JFXTextField txtMessage;
    public Text txtUId;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    String message = "";
    private String checkMessage;

    public DashboardController() {
        dashboardController = this;
    }

    public static DashboardController getInstance() {
        return dashboardController;
    }

    public void addNewContactOnAction(ActionEvent actionEvent) {
        try {
            Navigation.popupNavigation("AddNewContact.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void OkOnAction(ActionEvent actionEvent) throws IOException {


        Label text = new Label();
        text.setText("   " + txtMessage.getText() + "   ");
        text.setStyle("    -fx-shape: \"M 800 100 Q 750 100 750 150 L 50 150 Q 0 100 50 50 Q 400 50 750 50 Z\";\n" +
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
        gridpane.setAlignment(Pos.CENTER_RIGHT);

        root.getChildren().add(gridpane);

        vBox.getChildren().add(gridpane);

        //=================================== action =========================================


        //========================================================================================

        String text1 = txtUId.getText();
        String[] split = text1.split(":");
        try {
            if (split[0].equals("G")) {
                for (int i = 0; i < contactList.size(); i++) {
                    String s = contactList.get(i)[1];
                    String[] split1 = s.split("User");
                    dataOutputStream.writeUTF(split1[1]);
                    dataOutputStream.flush();
                    dataOutputStream.writeUTF(txtMessage.getText());
                    dataOutputStream.flush();
                }
                checkMessage = txtMessage.getText();
                txtMessage.setText("");

            }
        } catch (Exception e) {
            dataOutputStream.writeUTF(port);
            dataOutputStream.flush();
            dataOutputStream.writeUTF(txtMessage.getText());
            checkMessage = txtMessage.getText();
            dataOutputStream.flush();
            txtMessage.setText("");
        }
    }

    public void sendInFo(String port, String info) {
        try {
            dataOutputStream.writeUTF(port);
            dataOutputStream.flush();
            dataOutputStream.writeUTF(info);
            dataOutputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new Thread(() -> {
            try {
                socket = new Socket("localhost", 3000);
                System.out.println("socket.getLocalPort() > " + socket.getLocalPort());
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());

                while (!message.equals("Finish")) {
                    message = dataInputStream.readUTF();
                    System.out.println(">" + message);

                    checkMessage = "";
                    String[] split1 = message.split(":");
                    String checkGroup = "";
                    try {
                        System.out.println(Arrays.toString(split1));
                        checkGroup = split1[0];
                    } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
                        e.printStackTrace();
                    }

                    if (checkGroup.equals("chitChat")) {

                        Platform.runLater(() -> {
                            Label text = new Label();
                            text.setText(message);
                            text.setStyle("    -fx-shape: \"M 800 100 Q 750 100 750 150 L 50 150 Q 0 100 50 50 Q 400 50 750 50 Z\";\n" +
                                    "    -fx-background-color: #b571e0;\n" +
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
                            Button button = new Button();
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

                                System.out.println(" <<<< new >>>>");
                                try {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/GroupBar.fxml"));
                                    Parent barPane = loader.load();
                                    GroupBarController controller = loader.getController();
                                    controller.setData("GROUP", "User" + split1[2]);
                                    Platform.runLater(() -> {
                                        DashboardController.getInstance().contactVbox.getChildren().add(barPane);
                                    });
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                DashboardController.port = String.valueOf(socket.getLocalPort());
                                DashboardController.getInstance().txtName.setText("Group");
                                DashboardController.getInstance().vBox.getChildren().clear();
                                DashboardController.getInstance().txtUId.setText("User" + socket.getLocalPort());

                            });

                            //==============================================================


                            GridPane.setHalignment(text, HPos.CENTER);
                            GridPane.setHalignment(button, HPos.LEFT);
                            gridpane.add(text, 0, 0);
                            gridpane.add(button, 0, 0);
                            gridpane.setAlignment(Pos.CENTER_RIGHT);

                            root.getChildren().add(gridpane);

                            DashboardController.getInstance().vBox.getChildren().add(gridpane);
                        });


                    } else if (message.endsWith(".jpg") | message.endsWith(".png")) {
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

                            vBox.getChildren().add(gridpane);


                        });

                    } else {
                        Platform.runLater(() -> {

                            if (!checkMessage.equals(message)) {
                                checkMessage = null;
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

                                vBox.getChildren().add(gridpane);
                            }


                        });
                    }

                }


            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Platform.runLater(() -> {

            });

        }).start();
    }

    public void BrowsImageOnAction(ActionEvent actionEvent) {
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(new Stage());
        if (file != null) {

            System.out.println(file.getPath());
            Image image1 = new Image(file.getPath(), 360, 720, true, true);
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
            gridpane.setAlignment(Pos.CENTER_RIGHT);

            root.getChildren().add(gridpane);

            vBox.getChildren().add(gridpane);


//           ===============================================================
         /*   try {
                dataOutputStream.writeUTF(port);
                dataOutputStream.flush();
                dataOutputStream.writeUTF("Image "+file.getPath());
                dataOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            try {
                String text1 = txtUId.getText();
                String[] split = text1.split(":");
                try {
                    if (split[0].equals("G")) {
                        for (int i = 0; i < contactList.size(); i++) {
                            String s = contactList.get(i)[1];
                            String[] split1 = s.split("User");
                            dataOutputStream.writeUTF(split1[1]);
                            dataOutputStream.flush();
                            dataOutputStream.writeUTF("Image "+file.getPath());
                            dataOutputStream.flush();
                        }
                    }
                } catch (Exception e) {

                    dataOutputStream.writeUTF(port);
                    dataOutputStream.flush();
                    dataOutputStream.writeUTF("Image "+file.getPath());
                    dataOutputStream.flush();
                }
            }catch (IOException ioException) {
                ioException.printStackTrace();
            }


        }
    }

    public void addNewGroupOnAction(ActionEvent actionEvent) {
        try {
            Navigation.popupNavigation("AddNewGroup.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

