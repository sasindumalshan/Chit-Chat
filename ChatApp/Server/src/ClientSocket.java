import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ClientSocket {
    private Socket clientSocket;
    DataInputStream dataInputStream;
    String message="";
    ArrayList<String>messages=new ArrayList<>();
    public  Socket getSocket(){
        return clientSocket;
    }
    public void newServer(Socket socket){
        new Thread(()->{

            clientSocket = socket;
            System.out.println("Client add ... !");
            System.out.println("clientSocket               "+clientSocket);

            try {
                dataInputStream=new DataInputStream(clientSocket.getInputStream());
//                dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
                while (!message.equals("Finish")){
                    message=dataInputStream.readUTF();
                    System.out.println("client message > "+message);
                   // System.out.println(clientSocket.getPort());
                    messages.add(message);
                    if (messages.size()==2){
                        for (int i = 0; i < Server.clientSockets.size(); i++) {
                           // System.out.println("( "+messages.get(0)+" , "+Server.clientSockets.get(i).getSocket().getPort() +" )");
                          //  System.out.println(Integer.valueOf(Server.clientSockets.get(i).getSocket().getPort()));
                          //  System.out.println(Integer.parseInt(messages.get(0)));
                          //  System.out.println(Integer.parseInt(messages.get(0))==Server.clientSockets.get(i).getSocket().getPort());
                            if (Integer.parseInt(messages.get(0))==Server.clientSockets.get(i).getSocket().getPort()){
                                System.out.println("send( "+Server.clientSockets.get(i).getSocket()+" , "+messages.get(1)+" ) ");
                                Server.send(Server.clientSockets.get(i).getSocket(),messages.get(1));
                                System.out.println("send");
                                messages.clear();
                                break;
                            }
                        }

                    }
                }



            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }).start();
    }

}
