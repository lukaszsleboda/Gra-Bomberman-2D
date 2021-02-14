package game.panel;


import Constants.Constants;
import game.ServerConnection.Client;
import game.Ranking.HighScoreManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Klasa panelu najlepszych wyników
 */
public class StatisticPanel extends JPanel
{


    /**
     * Konstruktor panelu najlepszych wyników
     * @param pick przekazanie do switcha w klasie frame
     */
    public StatisticPanel(ActionListener pick) throws IOException {
        setLayout(new BorderLayout());
        JTextArea txtArea = new JTextArea("                     Najlepsze wyniki                  ");
        txtArea.setBackground(Color.RED);
        txtArea.setFont(new Font("Arial",Font.BOLD,30));

        add(txtArea, BorderLayout.NORTH);

        JTextArea txtArea2 = new JTextArea(Constants.statRowsNumber, Constants.statColumnsNumber);
        txtArea2.setBackground(Color.lightGray);
        if (Client.isOffline) {
            HighScoreManager hm = new HighScoreManager();
            txtArea2.setText(hm.getHighscoreString());
            System.out.print("hm: ...: "+ hm.getHighscoreString());
        }
        if (!Client.isOffline)
        {
            String tmp = Constants.loadRankingFromServer();
            txtArea2.setText(tmp);
        }

            txtArea2.setFont(new Font(Constants.instructionFont, Font.PLAIN, Constants.statisticFontSize));
             add(txtArea2, BorderLayout.CENTER);


        add(backButton(pick), BorderLayout.SOUTH);
        setVisible(true);

    }

    /**
     * Guzik cofania
     * @param pick przekazanie do switcha w klasie frame
     * @return backbutton
     */
    private JButton backButton(ActionListener pick)
    {
        JButton backButton = new JButton(Constants.backButtonLabel);
        backButton.setFocusable(false);
        backButton.addActionListener(pick);
        backButton.setActionCommand("BackToMenuFromStatisticPanel");
        return backButton;
    }
}
