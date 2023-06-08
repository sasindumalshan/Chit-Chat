package sample;

import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public JFXTextField message;
    public VBox vBox;
    String messing;
    DataInputStream dataInputStream;
    Socket socket;
    ServerSocket serverSocket;



    public void sendOnAction(ActionEvent actionEvent) {

      sendData(message.getText());
      message.clear();
    }

    private void sendData(String message) {
        System.out.println(message);
        /*try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("bar.fxml"));
            Parent root = loader.load();
            BarController controller = loader.getController();
            controller.setData(message);
            vBox.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }

    @Override

    public void initialize(URL url, ResourceBundle resourceBundle) {
        new Thread(()->{
            try {
                serverSocket = new ServerSocket(3000);
                socket = serverSocket.accept();
                dataInputStream = new DataInputStream(socket.getInputStream());
                do {
                    messing = dataInputStream.readUTF();

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("bar.fxml"));
                                Parent root = loader.load();
                                BarController controller = loader.getController();
                                controller.setData(messing);
                                vBox.getChildren().add(root);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    });


                } while (!messing.equals("end"));
                socket.close();
            } catch (IOException e) {
            }
        }).start();
        try {




            /**
             * readUTF()  >> USing a Decode input message
             * */


            /**
             *  socket.close();  >> Close a this Socket...
             * */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
