package workshop;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class FileUploadServer {
    // java -cp classes day04.FileUploadServer 3000

    public static void main(String[] args) throws IOException {

        int port = Integer.parseInt(args[0]);
        // Create the Server
        ServerSocket fileUploadServer = new ServerSocket(port);

        System.out.println("Waiting for connection");

        while (true) {
            // Wait for incoming connection, block
            Socket connNew = fileUploadServer.accept();
            System.out.println("Got a client connection!");

            // Get the input stream
            try (InputStream is = connNew.getInputStream()) {
                try (DataInputStream dis = new DataInputStream(is)) {

                    String fileName = dis.readUTF();
                    long fileSize = dis.readLong();

                    try (FileOutputStream fos = new FileOutputStream(fileName)) {
                        try (BufferedOutputStream bos = new BufferedOutputStream(fos)) {
                            // byte buffer[] = new byte[1024 * 4];
                            // long totalBytesRead = 0;
                            // int bytesRead = 0;

                            // while (totalBytesRead < fileSize) {
                            // bos.write(buffer, 0, bytesRead);
                            // totalBytesRead += bytesRead;

                            byte[] buff = new byte[2 * 1024];
                            int bytesRead = 0;
                            int bytesRecv = 0;
                            int idx = 0;

                            System.out.printf("Receiving file %s of size %d\n", fileName, fileSize);

                            while (bytesRecv < fileSize) {
                                // Number of bytes read
                                bytesRead = dis.read(buff);
                                bytesRecv += bytesRead;

                                // Write to local file
                                bos.write(buff, 0, bytesRead);

                                System.out.printf("%d> %d Recv %d of %d\n", idx, bytesRead, bytesRecv, fileSize);
                                idx++;
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
