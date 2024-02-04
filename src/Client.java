
import mvc.view.ClientPlayerUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 1234;
        String name;

        if (args.length > 0) {
            name = args[0];
        } else {
            name = "Unknown player";
        }

        try (Socket socket = new Socket(hostname, port)) {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Start a new thread to read incoming messages
            new Thread(() -> {
                try {
                    while (true) {
                        String message = in.readLine();
                        if (message == null) {
                            break;
                        }

                        String messageType = message.split("\\|")[0];
                        if (messageType.equals("WELCOME"))
                            out.println("REQUEST_GAME|N");

                        ClientPlayerUI.parseServerMessage(message);
                    }
                } catch (IOException e) {
                    System.out.println("Read error: " + e.getMessage());
                }
            }).start();

            out.println("ANNOUNCE|" + name + "|N");

            // Read user input and send messages to the server
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String message = ClientPlayerUI.parseCommandFrom(userInput.readLine());

                if (message.equalsIgnoreCase("exit")) break;

                out.println(message);
            }
        } catch (UnknownHostException e) {
            System.out.println("Server not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        }
    }
}
