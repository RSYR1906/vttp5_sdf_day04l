package workshop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;

public class FileClient {

    public static void main(String[] args) throws UnknownHostException, IOException {

        String filePath = "Hello.txt";
        Socket sockOne = new Socket("localhost", 1500);

        System.out.println(">>>> connected to server");

        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write("This file will be transferred");

        // Output stream
        // create data output stream
        DataOutputStream dos = new DataOutputStream(sockOne.getOutputStream());
        FileInputStream fis = new FileInputStream(filePath);

        File file = new File(filePath);
        String fileName = file.getName();
        dos.writeUTF(fileName);

        long fileSize = file.length();
        dos.writeLong(fileSize);

        byte[] buffer = new byte[4096];
        int bytesRead;

        while ((bytesRead = fis.read(buffer)) != -1) {
            dos.write(buffer, 0, bytesRead);
        }

        // System.out.println("Transferring file: " + fileName + " of size: " + fileSize + " bytes");
        // System.out.println("File transferred");

        dos.flush();
        fis.close();
        writer.close();
        sockOne.close();
        dos.close();
    }

}
