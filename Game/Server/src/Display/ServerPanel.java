package Display;

import javax.swing.*;
import java.awt.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ServerPanel extends JPanel
{
    /**
     * Etykieta wyświetlająca nazwe naszego serwera
     */
    JLabel title;
    /**
     * Etykieta weświetlająca ip
     */
    JLabel ipLbl;
    /**
     * Etykieta weświetlająca port
     */
    JLabel portLbl;


    private GridBagConstraints gbc;
    /**
     * Odleglosc miedzy elementami w panelu
     */
    private static final Insets insets1 = new Insets(3, 5, 20,5);
    /**
     * Odleglosc miedzy elementami w panelu
     */
    private static final Insets insets2 = new Insets(15, 5, 20,5);
    /**
     *
     */
    private GridBagLayout GBL = new GridBagLayout();

    /**
     * Konstruktor panelu wyswietlajacego informacje servera
     * @param port numer portu serwera
     * @throws UnknownHostException wyjatek
     */
    public ServerPanel(int port) throws UnknownHostException {
        setBackground(Color.getHSBColor(1,0,0));

        ipLbl = new JLabel("IP: " + InetAddress.getLocalHost());
        portLbl = new JLabel("Port: " + port);
        title = new JLabel("Bomberman Server");

        title.setFont(new Font("Jokerman", Font.BOLD, 24));
        ipLbl.setFont(new Font("Chiller", Font.BOLD, 23));
        portLbl.setFont(new Font("Chiller", Font.BOLD, 23));

        title.setForeground(Color.red);
        ipLbl.setForeground(Color.gray);
        portLbl.setForeground(Color.gray);

        portLbl.setHorizontalAlignment(SwingConstants.CENTER);
        ipLbl.setHorizontalAlignment(SwingConstants.CENTER);
        title.setHorizontalAlignment(SwingConstants.CENTER);

        setLayout(GBL);

        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

            gbc.gridx=1;
            gbc.insets = insets1;
        add(title,gbc);

            gbc.gridy=1;
            gbc.insets=insets2;
        add(ipLbl,gbc);

            gbc.insets = insets1;
            gbc.gridy=2;
        add(portLbl,gbc);

        setVisible(true);
    }

}
