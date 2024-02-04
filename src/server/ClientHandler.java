package server;

import mvc.controller.GameController;
import mvc.controller.PlayerController;
import server.command.ActivePlayerCommandInterface;
import server.command.ClientCommand;
import server.command.ServerCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ClientHandler extends Thread {
    private  Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private static Set<ClientHandler> clientHandlers = new HashSet<>();
    private PlayerController playerController;
    private GameController gameController;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            clientHandlers.add(this);
            System.out.println(clientHandlers.size() + " clients connected");
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                String message = in.readLine();
                if (message == null) {
                    break;
                }

                try {
                    ClientCommand request = (ClientCommand) ProtocolMessage.fromString(message);
                    if (!(request instanceof ActivePlayerCommandInterface)) {
                        if (!getPlayerController().getPlayer().getGame()
                                .validUserID(getPlayerController().getPlayer().getId()))
                            continue;
                    }
//                    if (request instanceof ConditionalCommandInterface) {
//                        request = new ConditionalCommand(request, );
//                    }`
                    request.setGameController(gameController);
                    request.setPlayerController(playerController);
                    request.execute();

                    ServerCommand response = request.getResponse();
                    response.setController(gameController);
                    response.setConnection(this);
                    response.execute();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                    e.printStackTrace();
                }

            }
        } catch (IOException e) {
            System.out.println("server.ClientHandler error: " + e.getMessage());
        } finally {
            disconnect();
        }
    }

    public void broadcast(String message) {
        for (ClientHandler clientHandler : clientHandlers) {
            messageTo(clientHandler, message);
        }
    }

    public void messageTo(ClientHandler recipient, String message) {
        recipient.out.println(message);
    }

    public static Set<ClientHandler> getHandlers() {
        return clientHandlers;
    }

    public void disconnect() {
        if (playerController.getPlayer() != null) {
            clientHandlers.remove(this);
        }
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("Socket close error: " + e.getMessage());
        }
    }

    public void setPlayerController(PlayerController playerController) {
        this.playerController = playerController;
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }
}
