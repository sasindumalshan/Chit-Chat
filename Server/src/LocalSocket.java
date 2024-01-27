import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class LocalSocket {
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
                while (!message.equals("Finish")){
                    message=dataInputStream.readUTF();
                    System.out.println("client message > "+message);
                    messages.add(message);
                    if (messages.size()==2){
                        for (int i = 0; i < Server.localSockets.size(); i++) {
                            if (Integer.parseInt(messages.get(0))==Server.localSockets.get(i).getSocket().getPort()){
                                System.out.println("send( "+Server.localSockets.get(i).getSocket()+" , "+messages.get(1)+" ) ");
                                Server.sendText(Server.localSockets.get(i).getSocket(),messages.get(1));
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
