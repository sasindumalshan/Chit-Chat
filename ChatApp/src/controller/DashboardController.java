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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    private static DashboardController dashboardController;
    private final ArrayList<String> usersList = new ArrayList<>();
    public JFXTextField txtMessage;
    public VBox contactVbox;
    public VBox vBox;
    public Pane pane;
    public Text txtUser;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private Socket remoteSocket;

    public DashboardController() {
        System.out.println(this);
        dashboardController = this;
    }

    public static DashboardController getInstance() {
        return dashboardController;
    }

    private static Image convertToFxImage(BufferedImage image) {
        WritableImage wr = null;
        if (image != null) {
            wr = new WritableImage(image.getWidth(), image.getHeight());
            PixelWriter pw = wr.getPixelWriter();
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    pw.setArgb(x, y, image.getRGB(x, y));
                }
            }
        }

        return new ImageView(wr).getImage();
    }

    /**
     * message send button
     */
    public void sendOnAction(ActionEvent actionEvent) {
        String message = txtMessage.getText();
        GridPane gridPane = creatText(message, Pos.CENTER_RIGHT);
        vBox.getChildren().add(gridPane);

        try {
            sendType(Type.STRING);
            dataOutputStream.writeUTF(txtUser.getText() + ": " + message);
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendType(Type type) throws IOException {
        dataOutputStream.writeUTF(String.valueOf(type));
    }

    private GridPane creatText(String message, Pos pos) {
        Label text = new Label();
        text.setText("   " + message + "   ");
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
        gridpane.setAlignment(pos);
        return gridpane;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /**
         * creat thread for run separate a socket part
         * */
        Thread thread = new Thread(() -> {
            try {
                /**
                 * Create Remote Socket
                 * */
                remoteSocket = new Socket("localhost", 3002);
                /**
                 * Create Input and Out put Stream
                 * */
                dataInputStream = new DataInputStream(remoteSocket.getInputStream());
                dataOutputStream = new DataOutputStream(remoteSocket.getOutputStream());

            } catch (IOException e) {
                e.printStackTrace();
            }

            /**
             * preview section clint
             * */

            try {

                String message = "";

                while (!message.equals("end")) {
                    String type="STRING";
                    try {

                         type = dataInputStream.readUTF();
                    }catch (UTFDataFormatException e){

                    }


                    if (type.equals("STRING")) {

                        Label text = new Label();
                        text.setStyle("    -fx-shape: \"M 800 100 Q 750 100 750 150 L 50 150 Q 0 100 50 50 Q 400 50 750 50 Z\";\n" +
                                "    -fx-background-color: #b571e0;\n" +
                                "    -fx-font-family: \"fantasy\";\n" +
                                "    -fx-font-size: 15; -fx-padding: 10; -fx-start-margin: 200 ; -fx-text-fill: #fff");
                        text.setMinWidth(200);
                        text.maxHeight(200);

                        final Group root = new Group();
                        final GridPane gridpane = new GridPane();
                        gridpane.setPadding(new Insets(5));
                        gridpane.setHgap(10);
                        gridpane.setVgap(10);
                        gridpane.minHeight(30);
                        gridpane.maxHeight(200);
                        GridPane.setHalignment(text, HPos.CENTER);
                        gridpane.add(text, 0, 0);
                        gridpane.setAlignment(Pos.CENTER_LEFT);
                        root.getChildren().add(gridpane);

                        message = dataInputStream.readUTF();
                        String[] tem = message.split(":");

                        if (!tem[0].equals(txtUser.getText())) {
                            setUser(tem[0]);
                            text.setText("  " + message + "   ");
                            Platform.runLater(() -> {
                                vBox.getChildren().add(gridpane);
                            });
                        }
                    } else if (type.equals("IMAGE")) {
                        System.out.println("Reading: " + System.currentTimeMillis());

                        byte[] sizeAr = new byte[4];
                        dataInputStream.read(sizeAr);
                        int size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();

                        byte[] imageAr = new byte[size];
                        dataInputStream.read(imageAr);

                        BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageAr));

                        Image image1 = convertToFxImage(img);
                        ImageView image = new ImageView(image1);
                        image.setFitWidth(350);
                        image.setPreserveRatio(true);

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

                        Platform.runLater(() -> {
                            vBox.getChildren().add(gridpane);
                        });

                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    private void setUser(String user) {
        boolean isNotAvailable = true;
        for (String u : usersList) {
            if (user.equals(u)) {
                isNotAvailable = false;
                break;
            }
        }
        if (isNotAvailable) {
            usersList.add(user);
            try {
                FXMLLoader loader = new FXMLLoader(DashboardController.class.getResource("/view/ContactBar.fxml"));
                Parent root = loader.load();
                ContactBarController controller = loader.getController();
                controller.setData(user);
                Platform.runLater(() -> {
                    contactVbox.getChildren().add(root);
                });
            } catch (IOException e) {
            }


        }
    }

    public void imageEncoderDecoder(String imgPath) throws IOException {
        sendType(Type.IMAGE);

        BufferedImage image = ImageIO.read(new File(imgPath));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", byteArrayOutputStream);

        byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
        dataOutputStream.write(size);
        dataOutputStream.write(byteArrayOutputStream.toByteArray());
        dataOutputStream.flush();
        System.out.println("Flushed: ");

    }

    public void BrowsImageOnAction(ActionEvent actionEvent) throws IOException {
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(new Stage());
        if (file != null) {

            imageEncoderDecoder(file.getPath());
//            Image image1 = new Image(file.getPath(), 360, 720, true, true);
//
//            ImageView image = new ImageView(image1);


            final Group root = new Group();


//            ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            BufferedImage img = ImageIO.read(new File(file.getPath()));

            Image image1 = convertToFxImage(img);
            ImageView image = new ImageView(image1);
            image.setFitWidth(350);
            image.setPreserveRatio(true);

//            ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

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

        }
    }

    public void BrowsEmojiOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(DashboardController.class.getResource("/view/EmojiComponent.fxml"));
            Parent root = loader.load();
            pane.getChildren().add(root);
        } catch (IOException e) {
        }
    }

    enum Type {
        STRING, IMAGE, EMOJI
    }

}

