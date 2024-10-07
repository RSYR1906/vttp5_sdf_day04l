package Practice;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.io.*;

public class ServerP {

    public static void main(String[] args) throws IOException {

        ServerSocket serverP = new ServerSocket(3000); // opening a port to wait for connection with client

        System.out.println("Waiting for connection");

        Socket sockP = serverP.accept(); // Blocking until connection forms with client. No downwards action if not
                                         // connected

        System.out.println("Connection established with client");

        try(InputStream is = sockP.getInputStream()){ // creating input stream to receive client output stream as input
        BufferedInputStream bis = new BufferedInputStream(is);
        DataInputStream dis = new DataInputStream(bis);

        String line = dis.readUTF();        //reading the message in the output stream from client

        System.out.println(line);    
        }
        catch (Exception e) {
            serverP.close();// TODO: handle exception
        }   
    }
}
