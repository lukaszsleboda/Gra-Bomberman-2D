package game.panel;

import javax.swing.*;
import java.awt.*;

/**
 * Glowna ramka wysietlajaca informacje o czitach
 */
public class CheatFrame extends JFrame {

    private Dimension d = new Dimension(400,400);

    private CheatPanel cheatPanel = new CheatPanel();


    /**
     * Konstruktor ramki czitow
     */
    public CheatFrame()
    {
        super("Bomberman Cheats");
        setSize(d);

        add(cheatPanel);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

}
