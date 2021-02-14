package game.panel;


import Constants.Constants;
import game.ServerConnection.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Klasa panelu instrukcji
 */
public class InstructionPanel extends JPanel
{

    private String InstructionText = "";

    /**``
     * Konstruktor panelu instrukcji
     * @param pick laczy guzik ze switchem w klasie frame
     */
    public InstructionPanel(ActionListener pick) throws IOException {
        setLayout(new BorderLayout());
        if (!Client.isOffline) {
            InstructionText = Constants.loadInstructionFromServer();
        }
        if (Client.isOffline) {
            InstructionText = Constants.loadInstructionText();
        }

        JTextArea txtArea = new JTextArea( Constants.rowsNumber, Constants.columnsNumber);
        txtArea.setText(InstructionText);
        txtArea.setFont(new Font(Constants.instructionFont, Font.PLAIN, Constants.instructionFontSize));
        txtArea.setBackground(Color.lightGray);

        add(txtArea, BorderLayout.CENTER);
        add(backButton(pick), BorderLayout.SOUTH);
        setVisible(true);
    }


    /**
     * Guzik cofania
     * @param pick laczy guzik ze switchem w klasie frame
     * @return backbutton
     */
    private JButton backButton(ActionListener pick)
    {
        JButton backButton = new JButton(Constants.backButtonLabel);
        backButton.setFocusable(false);
        backButton.addActionListener(pick);
        backButton.setActionCommand("BackToMenuFromHelpPanel");
        return backButton;
    }
}




