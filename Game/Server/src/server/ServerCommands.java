package server;


import Ranking.Ranking;

import java.io.IOException;
/**
 * Klasa odpwiedziana za wywolywanie odpowiednich metod w zaleznosci od prosby klienta
 */
public class ServerCommands {
    /**
     * W zaleznosci od zadania klienta wywoluje odpowiednia metode
     * @param command  zadanie klienta
     * @return odpowiedź serwera
     */
    public static String serverAction(String command) throws IOException {
        String serverMessage = null;
        String[] commands = command.split("-");
        switch (commands[0]) {
            case "getWindowsConfig":
                serverMessage = ServerConstants.loadWindowsConfig();
                break;
            case "getLabelsConfig":
                serverMessage = ServerConstants.loadLabelANDButtonConfig();
                break;
            case "getLevel":
                serverMessage = ServerConstants.loadLevel(Integer.parseInt(commands[1]));
                break;
            case "getGameParameters":
                serverMessage = ServerConstants.loadGameParameters();
                break;
            case "getInstruction":
                serverMessage = ServerConstants.loadInstruction();
                break;
            case "loadRanking":
                //serverMessage = Ranking.loadRanking();
                break;
            case "getRanking":
                serverMessage = Ranking.getRanking();
                break;
            case "saveScore":
                Ranking.saveScore(commands[1],commands[2]);
                serverMessage = "Score saved";
                break;
            default:
                serverMessage = "Wrong command";
        }
        return serverMessage;
    }
}
