import java.net.Socket;

public class TempSocket {
    private  Socket newSocket;
    private int port;

    public TempSocket(Socket accept) {
        this.newSocket = accept;
        this.port=accept.getPort();
    }

    public Socket getNewSocket() {
        return newSocket;
    }

    public void setNewSocket(Socket newSocket) {
        this.newSocket = newSocket;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
