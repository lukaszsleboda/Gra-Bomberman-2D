package Display;

import javax.swing.*;
import java.awt.*;
import java.net.UnknownHostException;

/**
 * Klasa glownego okna serwera
 */
public class Frame extends JFrame
{
    /**
     * Referencja do panelu z informacjami serwera
     */
    ServerPanel serverPanel;
    /**
     * Wymiar okna
     */
    private Dimension windowSize;

    /**
     * Konstruktor glownego okna serwera
     * @param port port serwera
     * @throws UnknownHostException wyjatek
     */
    public Frame(int port) throws UnknownHostException
        {
        super("BombermanServer");

        serverPanel=new ServerPanel(port);
        add(serverPanel);

        windowSize = new Dimension(350, 250);
        setSize(windowSize);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        this.revalidate();
        setVisible(true);
        }
}
