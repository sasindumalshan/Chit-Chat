import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class Server {

    public static ArrayList<LocalSocket> clientSockets = new ArrayList<>();
    private static ServerSocket serverSocket;
    private static DataOutputStream dataOutputStream;

    public static void main(String[] args) {
        createServerSocket();
        localSocketGenerator();
    }

    /**
     * create a server Socket
     */
    private static void createServerSocket() {
        try {
            serverSocket = new ServerSocket(3002);
            System.out.println("Server Started!..");
        } catch (IOException e) {
            System.err.println("Could not listen on port: 3002.");
            System.exit(1);
        }
    }

    /**
     * auto creating a local socket in requests a client
     */
    private static void localSocketGenerator() {
        new Thread(() -> {
            try {
                while (!serverSocket.isClosed()) {
                    LocalSocket Socket = new LocalSocket();
                    Socket.newServer(serverSocket.accept());
                    clientSockets.add(Socket);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();
    }

    /**
     * sent text to client
     */
    public static void send(String message) throws IOException {
        System.out.println("string :" + message);
        for (LocalSocket localSocket : clientSockets) {

            dataOutputStream = new DataOutputStream(localSocket.getSocket().getOutputStream());
            dataOutputStream.writeUTF(String.valueOf(Type.STRING));
            dataOutputStream.writeUTF(message);
            dataOutputStream.flush();
        }
    }

    public static void sendImage(BufferedImage image, int port) throws IOException {

        for (LocalSocket localSocket : clientSockets) {

            if (localSocket.getSocket().getPort() != port) {
                dataOutputStream = new DataOutputStream(localSocket.getSocket().getOutputStream());
                dataOutputStream.writeUTF(String.valueOf(Type.IMAGE));
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ImageIO.write(image, "jpg", byteArrayOutputStream);

                byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
                dataOutputStream.write(size);
                dataOutputStream.write(byteArrayOutputStream.toByteArray());
                dataOutputStream.flush();
                System.out.println("Flushed: ");
            }
        }

    }

    enum Type {
        STRING, IMAGE, EMOJI
    }
}
