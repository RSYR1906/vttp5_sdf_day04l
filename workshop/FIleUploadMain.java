package workshop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

import javax.imageio.IIOException;

public class FIleUploadMain {

    public static void main(String[] args) throws IOException {

        int port = 1500;

        String portNumber = args[0];
        String filetoTransfer = args[1];

        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }

        System.out.printf(">> Listening on port %d\n", port);

        // Create the Server
        ServerSocket serverFile = new ServerSocket(port);

        while (true) {
            System.out.println("Waiting for connection");

            // Wait for incoming connection, block
            Socket connNew = serverFile.accept();
            System.out.println("Got a client connection");

            // Get the input stream
            try (DataInputStream dataInputStream = new DataInputStream(connNew.getInputStream())) {
                String fileName = dataInputStream.readUTF();

                long fileSize = dataInputStream.readLong();

                try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
                    byte buffer[] = new byte[4096];
                    long totalBytesRead = 0;
                    int bytesRead;

                    while (totalBytesRead < fileSize && (bytesRead = dataInputStream.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, bytesRead);
                        totalBytesRead += bytesRead;
                    }
                }

                // Source file path (file you want to transfer)
                Path sourcePath = Paths.get("./workshop", "Hello.txt");

                // Destination file path (where you want to copy the file)
                Path destinationPath = Paths.get("transferredfiles", "Hello.txt");

                try {
                    // Transfer the file from the source directory to the destination directory
                    Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("File transferred successfully from " + sourcePath + " to " + destinationPath);
                } catch (IOException e) {
                    System.out.println("An error occurred during file transfer.");
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
