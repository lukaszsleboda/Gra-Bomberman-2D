package game.panel;

import Constants.Constants;
import game.object.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Klasa panelu przypisywania nazwy graczowi
 */
public class  NameSetPanel extends JPanel
{
    /**
    * Przyciski Graj
     */
    JButton playButton;
    /**
     * Przycisk cofaania
     */
    JButton backButton;
    /**
     * Miejsce na wpisanie nazwy gracza
     */
    JTextField nameField;
    /**
     * Pole z poleceniem wpisania nazwy gracza
     */
    JLabel defaultText;

    /**
     * Zmienne definiujace wyglad i rozlad guzikow na panelu
     */
    private GridBagLayout GBL = new GridBagLayout();
    private GridBagConstraints gbc;
    private static final Insets insets = new Insets(40, 50, 10,50);
    private static final Insets insets2 = new Insets(30, 50, 5,0);
    private static final Insets insets3 = new Insets(30, 0, 5,50);


    /**
     * Konstruktor okna do wpisania nazwy gracza
     * @param pick laczy guzik ze switchem w klasie frame
     */
    public NameSetPanel(ActionListener pick)
    {
        setBackground(Color.orange);

        playButton = new JButton(Constants.startGameButtonLabel);
        backButton = new JButton(Constants.backButtonLabel);
        nameField = new JTextField(15);
        nameField.setFont(new Font("Arial", Font.BOLD, 30));
        defaultText = new JLabel(Constants.defaultTextLabel);
        defaultText.setFont(new Font("Arial", Font.BOLD, 25));

        setLayout( GBL);
        gbc = new GridBagConstraints();


        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=0.2;
        gbc.weighty=0.1;

        gbc.gridx=1;
        gbc.gridy = 0;
        gbc.insets = insets2;
        add(defaultText,gbc);

        gbc.gridx=2;
        gbc.insets=insets3;
        add(nameField,gbc);

        gbc.gridy = 1;
        gbc.insets = insets;
        add(backButton(pick),gbc);

        gbc.gridx = 1;
        add(playButton(pick),gbc);
    }

    /**
     * Guzik cofania
     * @param pick laczy guzik ze switchem w klasie frame
     * @return
     */
    private JButton backButton(ActionListener pick)
    {
        JButton backButton = new JButton(Constants.backButtonLabel);
        backButton.setFocusable(false);
        backButton.addActionListener(pick);
        backButton.setActionCommand("BackToMenuFromNameSetPanel");
        return backButton;
    }

    /**
     * Guzik deklaracji przejścia do następnego etapu "customowania" gracza
     * @param pick laczy guzik ze switchem w klasie frame
     * @return
     */
    private JButton playButton(ActionListener pick)
    {
        JButton playButton = new JButton(Constants.startGameButtonLabel);
        playButton.setFocusable(false);
        playButton.addActionListener(pick);
        playButton.setActionCommand("loadLevel");
        return playButton;
    }




}
