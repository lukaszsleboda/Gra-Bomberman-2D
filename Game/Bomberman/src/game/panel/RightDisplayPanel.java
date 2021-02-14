package game.panel;

import Constants.Constants;
import game.object.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Definicja klasy panelu wyswietlajacego informacje w trakcie gry
 */
public class RightDisplayPanel extends JPanel implements ActionListener
{
    /**
     * Etykieta wyswietlajaca imie gracza
     */
    JLabel nameLabel;
    /**
     * Etykieta wyswietlajaca staly tekst Level
     */
    JLabel levelLabel;
    /**
     * Etykieta wyswietlajaca numer levelu
     */
    JLabel levelLabel2;
    /**
     * Etykieta wyswietlajaca staly tekst punkty
     */
    JLabel pointsLabel;
    /**
     * Etykieta wyswietlajaca aktualne punkty
     */
    JLabel pointsLabel2;
    /**
     * Etykieta wyswietlajaca staly napis zycia
     */
    JLabel lifeLabel;
    /**
     * Etykieta wyswietlajaca pozostle zycia
     */
    JLabel lifeLabel2;
    /**
     * Etykieta informujaca czy czity sa aktywne
     */
    JLabel cheatsInfo;
    /**
     * Guzik powrotu
     */
    JButton backButton;

    /**
     * Wysokosc panelu
     */
    public static final int HEIGHT = Constants.rightDisplayHeight;
    /**
     * Szerokosc panelu
     */
    public static final int WIDTH = Constants.rightDisplayWidth;

    /**
     * Referencja na obiekt gracza
     */
    Player player = Player.getInstance();

    /**
     * Wymar panelu
     */
    private Dimension rightDisplaySize;


    /**
     * Wyglad i rozklad elementow w panelu
     */
    private GridBagLayout GBL = new GridBagLayout();
    private GridBagConstraints gbc;
    private static final Insets insets = new Insets(40, 5, 10,5);
    private static final Insets insets2 = new Insets(0, 5, 0,5);
    private static final Insets insets3 = new Insets(90,5,0,5);

    /**
     * Konstruktor prawego wyswietlacza
     */
    public RightDisplayPanel()
    {


        rightDisplaySize = new Dimension(WIDTH,HEIGHT);
        setPreferredSize(rightDisplaySize);

        setBackground(Color.gray);

        nameLabel = new JLabel();
        levelLabel = new JLabel();
        levelLabel2 = new JLabel();
        pointsLabel = new JLabel();
        pointsLabel2 = new JLabel();
        lifeLabel = new JLabel();
        lifeLabel2 = new JLabel();
        backButton = new JButton();
        cheatsInfo = new JLabel();

        backButton.setText("powrot do menu");

        nameLabel.setText(player.getName());
        nameLabel.setBackground(Color.lightGray);

        levelLabel.setText(Constants.levelLabelText);
        levelLabel.setBackground(Color.lightGray);

        pointsLabel.setText(Constants.pointsLabelText);
        pointsLabel.setBackground(Color.lightGray);

        lifeLabel.setText(Constants.lifeLabelText);
        lifeLabel.setBackground(Color.lightGray);

        levelLabel2.setBackground(Color.lightGray);


        pointsLabel2.setBackground(Color.lightGray);

        lifeLabel2.setBackground(Color.lightGray);

        setLayout( GBL);
        gbc = new GridBagConstraints();
        gbc.fill=GridBagConstraints.BOTH;

        gbc.insets=insets;
        gbc.gridy=0;
        nameLabel.setOpaque(true);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 30));
        this.add(nameLabel,gbc);

        gbc.insets=insets;
        gbc.gridy=1;
        levelLabel.setOpaque(true);
        levelLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(levelLabel,gbc);

        gbc.insets=insets2;
        gbc.gridy=2;
        levelLabel2.setOpaque(true);
        levelLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(levelLabel2,gbc);

        gbc.insets=insets;
        gbc.gridy=3;
        pointsLabel.setOpaque(true);
        pointsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(pointsLabel,gbc);

        gbc.insets=insets2;
        gbc.gridy=4;
        pointsLabel2.setOpaque(true);
        pointsLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(pointsLabel2,gbc);

        gbc.insets=insets;
        gbc.gridy=5;
        lifeLabel.setOpaque(true);
        lifeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(lifeLabel,gbc);

        gbc.insets=insets2;
        gbc.gridy=6;
        lifeLabel2.setOpaque(true);
        lifeLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(lifeLabel2,gbc);

        gbc.gridy=7;
        gbc.insets = insets;
        this.add(backButton,gbc);

        gbc.gridy=8;
        gbc.insets = insets3;
        cheatsInfo.setOpaque(true);
        cheatsInfo.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(cheatsInfo,gbc);


        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {

    }


    /**
     * Metoda aktualizujaca dane panelu
     */
    public void update(Integer level, boolean cheats)
    {
        nameLabel.setText(player.getName());
        pointsLabel2.setText(Integer.toString(player.getPoints()));
        lifeLabel2.setText(Integer.toString(player.getLifes()));
        levelLabel2.setText(Integer.toString(level));
        cheatsInfo.setText("Cheats active: " + cheats);
    }
}
