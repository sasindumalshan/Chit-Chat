import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Main {
    private static ServerSocket serverSocket;
    private static final ArrayList<TempServer> TEMP_SERVERS=new ArrayList<>();
    public static void main(String[] args) {
        setServerSocket();
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        TEMP_SERVERS.add(new TempServer(serverSocket.accept()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("TEMP_SERVERS.size() > "+TEMP_SERVERS.size());
                }
            }
        });
        thread.start();

    }
    private static void setServerSocket() {
        try {
            serverSocket = serverSocket == null ? new ServerSocket(3000) : serverSocket;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
