import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    private static int port;
    private static Socket mySocket;
    private static   String massage ;
    static {
        massage="start";
    }
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        try {
           mySocket = new Socket("localhost", 3000);
            DataOutputStream dataOutputStream1 = new DataOutputStream(mySocket.getOutputStream());
            do {
                System.out.print("I'm Typing... ->> ");
                massage = scanner.nextLine();
                dataOutputStream1.writeUTF(massage);
                dataOutputStream1.flush();
                System.out.println("\n----------------------------------------------\n");
            } while (!massage.equals("end"));
            mySocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread getMessage = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    DataInputStream dataInputStream = new DataInputStream(mySocket.getInputStream());
                    do {
                        String s = dataInputStream.readUTF();
                        System.out.println("getMessage > "+s);
                    } while (!massage.equals("end"));
                    mySocket.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        getMessage.start();
    }
}
