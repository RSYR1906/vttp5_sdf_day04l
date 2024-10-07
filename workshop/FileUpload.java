package workshop;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class FileUpload {

    // java -cp classes day04.FileUpload localhost:3000 files/polarbear.jpg

    public static void main(String[] args) throws UnknownHostException, IOException {

        String port = args[0];
        int portNumber = Integer.parseInt(port); // converting port number into integer form

        File f = new File(args[1]); // Read file

        // check if file exists
        if (!(f.exists() && f.isFile())) {
            System.err.printf("%s is not a file", args[1]);
        }

        String fileName = f.getName();
        Long fileSize = f.length();

        System.out.println("Uploading file " + fileName + " of size " + fileSize + " bytes.");

        Socket sockOne = new Socket("localhost", portNumber);

        // open file for reading
        FileInputStream fis = new FileInputStream(f);
        BufferedInputStream bis = new BufferedInputStream(fis);

        // Open a connection to the server
        OutputStream os = sockOne.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);

        dos.writeUTF(fileName);
        dos.writeLong(fileSize);

        // // create a 4k buffer
        // byte[] buffer = new byte[1024 * 4];
        // int bytesRead;
        // while ((bytesRead = fis.read(buffer)) != -1) {
        // dos.write(buffer, 0, bytesRead);
        // }

        // Create a 4K buffer
        byte[] buff = new byte[4 * 1024];
        int bytesRead = 0;
        int sendBytes = 0;
        int idx = 0;

        while ((bytesRead = bis.read(buff)) > 0) {
            dos.write(buff, 0, bytesRead);
            sendBytes += bytesRead;
            System.out.printf("%d > %d Send %d of %d\n", idx, bytesRead, sendBytes, fileSize);
            idx++;
            dos.flush();
            os.flush();

        }
        sockOne.close();

    }
}
