import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TempServer {
    private static Socket socket;
    private static ServerSocket serverSocket;
    private static String messing;
    private static int port;
    private static Socket sendSocket;

    static {
        messing = "start";
    }

    public TempServer(Socket server_Socket) {
        sever(server_Socket);
    }

    public void sever(Socket server_Socket) {

        Thread getMessage = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = server_Socket;
                    DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                    DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

                    do {
                        messing = dataInputStream.readUTF();
                        System.out.println("getMessage > "+messing);

                        port = socket.getPort();
                        dataOutputStream.writeUTF(String.valueOf(port));
                        dataOutputStream.flush();
                    } while (!messing.equals("end"));
                    socket.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        getMessage.start();

     /*   Thread sendPort = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                     if (TempServer.port != 0) {
                           System.out.println("in  the sendPort method > "+port);
                        try {

                            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                            dataOutputStream.writeUTF(String.valueOf(port));
                            dataOutputStream.flush();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            }
        });
        sendPort.start();*/


    }
}
