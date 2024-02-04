package server;

import mvc.controller.GameController;
import mvc.controller.PlayerController;
import mvc.model.Player;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class GameServer {
    private static ArrayList<ClientHandler> connections = new ArrayList<>();
    private static int port = 1234;

    public static void main(String[] args) {
        GameController gameController = new GameController();
        gameController.createGame('N');

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                // MVC
                Player player = new Player();
                player.setGame(gameController.getGame());
                PlayerController playerController = new PlayerController(player);

                gameController.addPlayer(player);

                // Handle the client in a separate thread

                ClientHandler clientHandler = new ClientHandler(socket);
                clientHandler.setPlayerController(playerController);
                clientHandler.setGameController(gameController);

                connections.add(clientHandler);
                clientHandler.start();
            }
        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        } finally {
            for (ClientHandler connection: connections) {
                connection.disconnect();
            }
        }
    }
}