package workshop.day04;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;

public class FIleUploadMain {

    public static void main(String[] args) throws IOException {

        int port =5000;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }

        System.out.printf(">> Listening on port %d\n", port);

        // Create the Server
        ServerSocket serverFile = new ServerSocket(port);


        
    }

    // DataInputStream.readUTF();
    // DataInputStream.readLong();
    // DataInputStream.BufferedReader();
}
