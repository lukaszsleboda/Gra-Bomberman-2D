package game.panel;

import javax.swing.*;
import java.awt.*;

/**
 * Klasa panelu z czitami
 */
public class CheatPanel extends JPanel {

    private JLabel title = new JLabel("Cheats Activated");

    private JLabel c1 = new JLabel("1 -> Unlimited lifes");

    private JLabel c2 = new JLabel("2 -> Show portal");

    private JLabel c3 = new JLabel("3 -> Kill clippers");

    private JLabel c4 = new JLabel("4 -> Show points packs");

    private JLabel c5 = new JLabel("5 -> Add points");

    private JLabel c6 = new JLabel("6 -> Stop clippers");




    private GridBagConstraints gbc;
    /**
     * Odleglosc miedzy elementami w panelu
     */
    private static final Insets insets1 = new Insets(3, 5, 20,5);
    /**
     * Layout
     */
    private GridBagLayout GBL = new GridBagLayout();

    /**
     * Konstruktor panelu z informacjami o czitach do gry
     */
    public CheatPanel()
    {
        setBackground(Color.getHSBColor(1,0,0));

        title.setFont(new Font("Jokerman", Font.BOLD, 24));
        title.setForeground(Color.orange);
        title.setHorizontalAlignment(SwingConstants.CENTER);


        c1.setFont(new Font("Chiller", Font.BOLD, 23));
        c1.setForeground(Color.gray);
        c1.setHorizontalAlignment(SwingConstants.CENTER);
        c2.setFont(new Font("Chiller", Font.BOLD, 23));
        c2.setForeground(Color.gray);
        c2.setHorizontalAlignment(SwingConstants.CENTER);
        c3.setFont(new Font("Chiller", Font.BOLD, 23));
        c3.setForeground(Color.gray);
        c3.setHorizontalAlignment(SwingConstants.CENTER);
        c4.setFont(new Font("Chiller", Font.BOLD, 23));
        c4.setForeground(Color.gray);
        c4.setHorizontalAlignment(SwingConstants.CENTER);
        c5.setFont(new Font("Chiller", Font.BOLD, 23));
        c5.setForeground(Color.gray);
        c5.setHorizontalAlignment(SwingConstants.CENTER);
        c6.setFont(new Font("Chiller", Font.BOLD, 23));
        c6.setForeground(Color.gray);
        c6.setHorizontalAlignment(SwingConstants.CENTER);


        setLayout(GBL);

        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx=1;
        gbc.gridy = 0;
        gbc.insets = insets1;
        add(title,gbc);
        gbc.gridy=1;
        add(c1,gbc);
        gbc.gridy=2;
        add(c2,gbc);
        gbc.gridy=3;
        add(c3,gbc);
        gbc.gridy=4;
        add(c4,gbc);
        gbc.gridy=5;
        add(c5,gbc);
        gbc.gridy=6;
        add(c6,gbc);


        setVisible(true);
    }


}
