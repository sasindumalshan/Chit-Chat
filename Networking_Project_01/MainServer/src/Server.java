import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private static final ArrayList<Socket> sockets = new ArrayList<>();
    private static final ArrayList<TempSocket> temSockets = new ArrayList<>();
    private static ServerSocket serverSocket;
    private static DataInputStream dataInputStream;
   private static Socket accept;
    public static void main(String[] args) {
        System.out.println("main");


        setServerSocket();
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
              while (true){
                  try {
                    sockets.add(serverSocket.accept());
                      System.out.println(sockets.size());
                      for (int i = 0; i < sockets.size(); i++) {
                          System.out.println(sockets.get(i));
                      }
                      //temSockets.add(new TempSocket(serverSocket.accept()));
                     // System.out.println(temSockets.size());
                      for (TempSocket socket:temSockets) {
                          System.out.println(socket.getNewSocket());
                      }
                     /* if (!temSockets.isEmpty()){
                          getMessage(temSockets.get(0));
                      }*/

                      if (sockets.size()==1){

                          Socket messing2 =sockets.get(0);
                          System.out.println("socket2 > "+messing2);
                          System.out.println("socket2 > "+messing2.getPort());

                          DataInputStream dataInputStream = new DataInputStream(messing2.getInputStream());
                          DataOutputStream dataOutputStream = new DataOutputStream(messing2.getOutputStream());
                          final String[] s = {"start"};
                          Thread thread1=new Thread(new Runnable() {
                              @Override
                              public void run() {
                                  try {
                                      do {
                                          dataOutputStream.writeUTF(String.valueOf(messing2.getPort()));
                                          dataOutputStream.flush();
                                      }while (!s[0].equals("end"));

                                  } catch (IOException e) {
                                      e.printStackTrace();
                                  }
                              }
                          });
                          thread1.run();

                            Thread thread2=new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    do {
                                        try {
                                            s[0] = dataInputStream.readUTF();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        System.out.println(s[0]);
                                    }while (!s[0].equals("end"));

                                }
                            });
                            thread2.run();

                      }



                  } catch (IOException e) {
                      e.printStackTrace();
                  }
              }
            }
        });
        thread.start();


        sendMessage();
    }

    private static void setServerSocket() {
        try {
            serverSocket = serverSocket == null ? new ServerSocket(3000) : serverSocket;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendMessage() {

    }

    private static void getMessage(Socket newSocket) {

                temSockets.remove(0);
                System.out.println("getMessage > temSockets.size()  : "+temSockets.size());
                System.out.println("getMessage");
                if (sockets.isEmpty()) {
                    sockets.add(newSocket);
                    System.out.println("sockets.isEmpty() >--->   " + sockets.get(0));
                }
                for (Socket socket : sockets) {
                    System.out.println(socket);
                    if (!socket.equals(newSocket)) {
                        System.out.println("in the if ..");
                        sockets.add(newSocket);
                        System.out.println("!socket.equals(newSocket) >--->   " +socket );

                    }
                }
                System.err.println(sockets.size());
                try {
                    Socket currentSocket = null;
                    for (Socket socket : sockets) {
                        if (socket.equals(newSocket)) {
                            currentSocket = socket;

                        }
                    }
                    dataInputStream = dataInputStream == null ? new DataInputStream(currentSocket.getInputStream()) : dataInputStream;
                    String read = null;
                    read = dataInputStream.readUTF();
                    System.out.println(read);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


    }

