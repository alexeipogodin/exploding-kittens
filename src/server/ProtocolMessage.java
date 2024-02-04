package server;

import server.command.ClientCommand;
import server.command.Command;
import server.command.client.*;
import server.command.ServerCommand;
import server.command.server.NullCommand;
import server.command.server.WelcomeCommand;

public class ProtocolMessage {

    public static Command fromString(String message) throws Exception {
        String[] parts = message.split("\\Q" + Protocol.SEPARATOR + "\\E");
        String messageType = parts[0];

        Command cmd;
        if (messageType.equals("ANNOUNCE")) {
            cmd = new AnnounceCommand(parts[1], parts[2].charAt(0));
        } else if (messageType.equals("START")) {
            cmd = new StartCommand();
        } else if (messageType.equals("CHAT")) {
            cmd = new ChatCommand(parts[1], Integer.parseInt(parts[2]));
        } else if (messageType.equals("REQUEST_GAME")) {
            // TODO: convert string to char properly
            cmd = new RequestGameCommand(parts[1].charAt(0));
        } else if (messageType.equals("DRAW")) {
            cmd = new DrawCommand();
        } else if (messageType.equals("PLAY")) {
            if (parts.length < 4)
                cmd = new PlayCommand(parts[1], -1);
            else
                cmd = new PlayCommand(parts[1],
                        (parts[3].isBlank()) ? -1 : Integer.parseInt(parts[3]));
        } else {
            throw new Exception("Invalid command received");
        }

        return cmd;
    }
}
