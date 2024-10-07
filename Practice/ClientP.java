package Practice;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientP {

    public static void main(String[] args) throws UnknownHostException, IOException {

        Socket sockP = new Socket("localhost", 3000); // Creating a socket to connect with server at port 3000
        System.out.println("Connecting to server");

        try (OutputStream os = sockP.getOutputStream()) { // Creating an output stream to server
            BufferedOutputStream bos = new BufferedOutputStream(os);
            DataOutputStream dos = new DataOutputStream(bos);

            dos.writeUTF("Hello World"); // output containing message to server
            System.out.println("Sending message to server");
            dos.flush();
            dos.close();
        } catch (Exception ex) {
            sockP.close();
        }
    }
}
