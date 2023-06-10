import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;
public class Client {
    public Client(){
        String serverAddress = "127.0.0.1";
        int portNumber = 12345;
        int x = 0;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                Socket socket = new Socket(serverAddress, portNumber);

                InputStream inputStream = socket.getInputStream();

                int variable1 = inputStream.read();

                byte[] variable2Bytes = new byte[1024];
                int bytesRead = inputStream.read(variable2Bytes);
                String variable2 = new String(variable2Bytes, 0, bytesRead);

                if (x != variable1) {
                    System.out.println("Variable 1: " + variable1);
                    System.out.println("Variable 2: " + variable2);
                    x = variable1;
                }

                inputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
