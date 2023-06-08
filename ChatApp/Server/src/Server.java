import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private static ServerSocket serverSocket;

    public static ArrayList<ClientSocket> clientSockets=new ArrayList<>();

  // private static Socket clientSocket;
 //   private static  Socket clientSocket2;


   /* DataInputStream dataInputStream;
    DataInputStream dataInputStream2;
    DataOutputStream dataOutputStream;
    String message="";
    Socket sender;*/

  /*  public void newServer2(){
        new Thread(()->{

            try {
                clientSocket2 = serverSocket.accept();


                System.out.println("Client Connected!..");
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            try {
                dataInputStream2=new DataInputStream(clientSocket2.getInputStream());
//                dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
                while (!message.equals("Finish")){
                    message=dataInputStream2.readUTF();
                    System.out.println("client message > "+message);
                    System.out.println(clientSocket2.getPort());
                    send();
                }



            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }).start();
    }*/

//    ================================================================================================================================================

 /*   public void newServer(){
        new Thread(()->{

            try {
                clientSocket = serverSocket.accept();
                System.out.println("Client Connected!..");
                System.out.println("clientSocket               "+clientSocket);
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            try {
                dataInputStream=new DataInputStream(clientSocket.getInputStream());
//                dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
                while (!message.equals("Finish")){
                    message=dataInputStream.readUTF();
                    System.out.println("client message > "+message);
                    System.out.println(clientSocket.getPort());
                    send();
                }



            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }).start();
    }*/

    public static void main(String[] args) throws IOException {
        try {
            serverSocket = new ServerSocket(3000);
            System.out.println("Server Started!..");
        } catch (IOException e) {
            System.err.println("Could not listen on port: 3000.");
            System.exit(1);
        }

       /* Server server=new Server();
        server.newServer();

        Server server2=new Server();
        server2.newServer2();*/

        new Thread(()->{
            try {
                while (!serverSocket.isClosed()){
                    ClientSocket Socket = new ClientSocket();
                    Socket.newServer(serverSocket.accept());
                    clientSockets.add(Socket);
                    System.out.println("clientSockets.size()  > "+clientSockets.size());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();



    }


    public static void send(Socket clientSocket,String message) throws IOException {

        DataOutputStream  dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
        System.out.println("clientSocket               "+clientSocket);
        System.out.println("clientSocket.getOutputStream()              "+clientSocket.getOutputStream());
        dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
       dataOutputStream.writeUTF(message);
        dataOutputStream.flush();
    }
}
