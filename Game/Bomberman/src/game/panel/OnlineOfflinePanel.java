package game.panel;

import Constants.Constants;
import game.object.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


/**
 * Klasa panelu sluzacego do wyboru gry online badz offline
 */
public class OnlineOfflinePanel extends JPanel
{

    /**
     * Guzik do gry online
     */
    JButton onlineButton;
    /**
     * Guzik do gry offline
     */
    JButton offlineButton;
    /**
     * staly tekst z poleceniem poddania socketu servera
     */
    JLabel serverlabel;
    /**
     * Pole na wpisanie socketu servera
     */
    JTextField serverTextField;


    /**
     * Wysokosc panelu
     */
    public static final int HEIGHT = Constants.ServerDisplayHeight;
    /**
     * Szerokosc panelu
     */
    public static final int WIDTH = Constants.ServerDisplayWidth;


    /**
     * Wyglad i rozklad przyciskow
     */
    private Dimension serverWindowSize;
    private Dimension labelSize;
    private GridBagLayout GBL = new GridBagLayout();
    private GridBagConstraints gbc;
    private static final Insets insets = new Insets(40, 50, 10,50);
    private static final Insets insets2 = new Insets(30, 50, 5,0);
    private static final Insets insets3 = new Insets(30, 0, 5,50);

    /**
     * Konstruktor panelu gry online lub offline
     *
     * @param pick przekkazania do switcha w klasie rame
     */
    public OnlineOfflinePanel(ActionListener pick)
    {


        serverWindowSize = new Dimension(WIDTH,HEIGHT);
        setPreferredSize(serverWindowSize);

        setBackground(Color.orange);

        serverlabel = new JLabel("Port:             ");
        serverlabel.setHorizontalAlignment(SwingConstants.CENTER);
        serverlabel.setFont(new Font("Arial", Font.BOLD, 30));

        serverTextField = new JTextField(15);
        serverTextField.setFont(new Font("Arial", Font.BOLD, 25));
        serverTextField.setText("50002");

        onlineButton = new JButton(Constants.onlineButtonLabel);
        offlineButton = new JButton(Constants.offlineButtonLabel);

        setLayout( GBL);
        gbc = new GridBagConstraints();
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=0.2;
        gbc.weighty=0.1;

        gbc.gridx=1;
        gbc.gridy = 0;
        gbc.insets = insets2;
        add(serverlabel,gbc);

        gbc.gridx=2;
        gbc.insets=insets3;
        add(serverTextField,gbc);

        gbc.gridy = 1;
        gbc.insets = insets;
        add(onlineButton(pick),gbc);

        gbc.gridx = 1;
        add(offlineButton(pick),gbc);

        setVisible(true);
    }

    /**
    * Guzik gry online
     */
    private JButton onlineButton(ActionListener pick)
    {
        JButton onlineButton = new JButton(Constants.onlineButtonLabel);
        onlineButton.setFocusable(false);
        onlineButton.addActionListener(pick);
        onlineButton.setActionCommand("online");
        return onlineButton;
    }

    /**
     * Guzik gry offline
     */
    private JButton offlineButton(ActionListener pick)
    {
        JButton offlineButton = new JButton(Constants.offlineButtonLabel);
        offlineButton.setFocusable(false);
        offlineButton.addActionListener(pick);
        offlineButton.setActionCommand("offline");
        return offlineButton;
    }

}
