import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;



public class Sudu {
    private static int port;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        Thread printPort=new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if (port!=0){
                        System.err.println(port);
                    }
                }
            }
        });
        printPort.start();

        try {
            Socket mySocket = new Socket("localhost", 3000);
            DataOutputStream dataOutputStream1 = new DataOutputStream(mySocket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(mySocket.getInputStream());

            String massage = null;
            do {
                System.out.print("Sudu Typing... ->> ");
                massage = scanner.nextLine();
                dataOutputStream1.writeUTF(massage);
                dataOutputStream1.flush();

                System.out.println("\n\n----------------------------------------------\n\n");
                /*Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            String massage = dataInputStream.readUTF();
                            port= Integer.parseInt(massage);
                            System.err.println(port);


                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });*/
                //thread.start();

            } while (!massage.equals("end"));

            mySocket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
