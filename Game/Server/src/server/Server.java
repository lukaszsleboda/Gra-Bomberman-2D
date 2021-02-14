package server;

import Display.Frame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Klasa glowna serwera
 */

public class Server {

    /**
     * Port serwera
     */
    public int serverPort;


    /**
     * Metoda uruchamiajaca serwer
     * Wczytuje port serwera z pliku IPCONFIG.txt
     */
    public void runServer() {

        try {
            BufferedReader br = new BufferedReader(new FileReader("ServerResources\\IPCONFIG.txt"));
            serverPort = Integer.parseInt(br.readLine());
            ServerSocket serverSocket = new ServerSocket(serverPort);
            System.out.println("Server zostal uruchomiony");
            Frame f = new Frame(serverPort);
            f.setVisible(true);
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new ServerThread(socket)).start();
            }
        }
        catch (IOException e){
            System.out.println("Serwer nie zostal uruchomiony");
            System.err.println(e);
        }
    }

}