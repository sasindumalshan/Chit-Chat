import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    private static String messing1;
    private static String messing2;

    private static DataOutputStream dataOutputStream1;
    private static DataOutputStream dataOutputStream2;

    private static Socket socket1;
    private static Socket socket2;

    public static void main(String[] args) {
        System.out.println("--------------------------------------------------------------\n" +
                "|                   server                                    |\n" +
                "-------------------------------------------------------------");

        try {
            ServerSocket serverSocket = new ServerSocket(3000);
            Thread client_1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                         socket1 = socket1==null?serverSocket.accept():socket1;
                        System.out.println("socket1 > "+socket1);
                        DataInputStream dataInputStream = new DataInputStream(socket1.getInputStream());
                        dataOutputStream1 = dataOutputStream1==null?new DataOutputStream(socket1.getOutputStream()):dataOutputStream1;
                        do {
                            messing1 = dataInputStream.readUTF();
                            System.out.println(messing1);

                            //++++++++++++++++++++++++
                          /*  try {
                                dataOutputStream2.writeUTF(messing2);
                                dataOutputStream2.flush();
                            } catch (NullPointerException nullPointerException) {
                                socket2 =socket2==null? serverSocket.accept():socket2;
                                dataOutputStream2= dataOutputStream2 ==null? new DataOutputStream(socket2.getOutputStream()):dataOutputStream2;
                                dataOutputStream2.writeUTF(Main.messing2);
                                dataOutputStream2.flush();
                            }*/

                            //++++++++++++++++++++++++

                        } while (!messing1.equals("end"));
                        socket1.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            client_1.start();
            Thread client_2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        socket2 =socket2==null? serverSocket.accept():socket2;
                        System.out.println("socket2 > "+socket2);

                        DataInputStream dataInputStream = new DataInputStream(socket2.getInputStream());
                        dataOutputStream2= dataOutputStream2 ==null? new DataOutputStream(socket2.getOutputStream()):dataOutputStream2;
                        do {
                            messing2 = dataInputStream.readUTF();
                            System.out.println(messing2);
                            //++++++++++++++++++++++++
                          /*  try {
                                dataOutputStream1.writeUTF(messing1);
                                dataOutputStream1.flush();
                            }catch (NullPointerException nullPointerException){
                                socket1 = socket1==null?serverSocket.accept():socket1;
                                dataOutputStream1 = dataOutputStream1==null?new DataOutputStream(socket1.getOutputStream()):dataOutputStream1;
                                System.out.println(Main.messing1);
                                System.out.println(dataOutputStream1==null);
                                System.out.println(dataOutputStream1.toString());
                                System.out.println(dataOutputStream1);
                                dataOutputStream1.writeUTF("messing1");
                                dataOutputStream1.flush();
                            }
*/
                            //++++++++++++++++++++++++
                        } while (!messing2.equals("end"));

                        socket2.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            client_2.start();

        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         *  socket.close();  >> Close a this Socket...
         * */
    }
}

