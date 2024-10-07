package workshop.day04;

import java.io.Console;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class FileClient {

    public static void main(String[] args) throws UnknownHostException, IOException {

        Socket sockOne = new Socket("local host", 5000);

        System.out.println(">>>> connected to server");

        Console cons = System.console();

    }
}
