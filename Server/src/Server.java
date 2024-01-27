import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public static ArrayList<LocalSocket> localSockets = new ArrayList<>();
    private static ServerSocket serverSocket;

    public static void main(String[] args){
        createServerSocket();
        localSocketGenerator();
    }

    /**
     * create a server Socket
     */
    private static void createServerSocket() {
        try {
            serverSocket = new ServerSocket(3000);
            System.out.println("Server Started!..");
        } catch (IOException e) {
            System.err.println("Could not listen on port: 3000.");
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
                    LocalSocket localSocket = new LocalSocket();
                    localSocket.newServer(serverSocket.accept());
                    localSockets.add(localSocket);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();
    }

    /**
     * sent text
     */
    protected static void sendText(Socket clientSocket, String message) throws IOException {

        DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
        dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
        dataOutputStream.writeUTF(message);
        dataOutputStream.flush();
    }
}
