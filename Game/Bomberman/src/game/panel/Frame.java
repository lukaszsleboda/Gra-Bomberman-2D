package game.panel;

import Constants.Constants;
import game.ServerConnection.Client;
import game.Ranking.HighScoreManager;
import game.object.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Klasa glownego okna programu i wszystkich jego wersji
 */
public class Frame extends JFrame implements ActionListener
{

    private Logo logo;
    /**
     * referencja do game screen
     */
    private GamePanel gamePanel;

    /**
     * referencja do name set panel
     */
    private NameSetPanel nameSetPanel;

    /**
     * referencja do help panel
     */
    private InstructionPanel instructionPanel;

    /**
     * referencja do statistic panel
     */
    private StatisticPanel statisticPanel;

    /**
    * referencja do online offline panel
     */
    private OnlineOfflinePanel onlineOfflinePanel;

    /**
     * referencja do panelu najwyzszych wynikow
     */
    private HighScoreManager highScoreManager;

    /**
     * referencja do gracza
     */
    Player player = Player.getInstance();

    /**
     * Panel z przyciskami na gorze ekranu (menu)
     */
    JPanel menuPanel;

    /**
     * Wymiar okna glownego
     */
    private Dimension windowSize;

    /**
     * Przycisk w menuPanelu rozpoczynajacy gre
     */
    private JButton startGameButton;

    /**
     * Przycisk w menu panelu wyswietlajacy panel ze statystykami
     */
    private JButton statisticButton;

    /**
     * Przycisk w panelu wyswietlajacy panel z instukcja
     */
    private JButton helpButton;

    /**
     * Konstruktor klasy Frame, głównego okna programu
     */
    public Frame() throws IOException {
        super(Constants.gameName);

        setFrameSize("nameWindow");


        onlineOfflinePanel = new OnlineOfflinePanel(this);
        highScoreManager = new HighScoreManager();
        logo = new Logo();

        add(onlineOfflinePanel);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }


    /**
     * Metoda tworząca gorny panel z przyciskami
     */
    private void createMenu()
    {
        menuPanel = new JPanel();

        menuPanel.setBackground(Color.blue);

        startGameButton = new JButton(Constants.startGameButtonLabel);
        statisticButton = new JButton(Constants.statisticButtonLabel);
        helpButton = new JButton(Constants.helpButtonLabel);

        startGameButton.addActionListener(this);
        statisticButton.addActionListener(this);
        helpButton.addActionListener(this);

        startGameButton.setActionCommand("play");
        statisticButton.setActionCommand("statistic");
        helpButton.setActionCommand("help");

        menuPanel.add(startGameButton);
        menuPanel.add(statisticButton);
        menuPanel.add(helpButton);
    }


    /**
     * Metoda nasluchujaca akcji, na podstawie ktorej przełacza okno,
     * z ktorego zostalo wydane polecenie na okno ządane
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        switch (command)
        {
            case "play":
                setFrameSize("nameWindow");

                nameSetPanel = new NameSetPanel(this);

                this.remove(menuPanel);
                this.remove(logo);
                this.add(nameSetPanel);

                setLocationRelativeTo(null);
                this.repaint();
                this.revalidate();
                setResizable(false);
                setVisible(true);

                break;


            case "statistic":
                try {
                    statisticPanel = new StatisticPanel(this);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                setFrameSize(("statWindow"));

                this.remove(menuPanel);
                this.remove(logo);
                this.add(statisticPanel);

                setLocationRelativeTo(null);
                setResizable(false);
                this.revalidate();
                this.repaint();

                break;


            case "help":
                try {
                    instructionPanel =  new InstructionPanel(this);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                setFrameSize("instWindow");

                this.remove(menuPanel);
                this.remove(logo);
                this.add(instructionPanel);

                setLocationRelativeTo(null);
                setResizable(false);
                this.revalidate();
                this.repaint();

                break;


            case "loadLevel":
                try {
                    gamePanel = new GamePanel(this);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                setFrameSize("mainWindow");

                remove(nameSetPanel);
                add(gamePanel);

                player.setName(nameSetPanel.nameField.getText());

                setVisible(false);
                setLocationRelativeTo(null);
                setResizable(true);
                this.revalidate();
                setVisible(true);

                break;


            case "BackToMenuFromNameSetPanel":
                setFrameSize("mainWindow");

                this.remove(nameSetPanel);
                this.add(menuPanel,BorderLayout.NORTH);
                this.add(logo);

                setResizable(false);
                setLocationRelativeTo(null);
                this.revalidate();
                this.repaint();

                break;


            case "BackToMenuFromHelpPanel":
                setFrameSize("mainWindow");

                this.remove(instructionPanel);
                this.add(menuPanel,BorderLayout.NORTH);
                this.add(logo);

                setLocationRelativeTo(null);
                setResizable(false);
                this.revalidate();
                this.repaint();

                break;


            case "BackToMenuFromStatisticPanel":
                setFrameSize("mainWindow");

                this.remove(statisticPanel);
                this.add(menuPanel,BorderLayout.NORTH);
                this.add(logo);

                setLocationRelativeTo(null);
                setResizable(false);
                this.revalidate();
                this.repaint();

                break;


            case "online":
                Constants.savePortAndIP( Integer.parseInt(onlineOfflinePanel.serverTextField.getText()), "localhost");
                try {
                    Constants.loadLayoutConfigFromServer();
                    Constants.loadLabelANDButonFromServer();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    System.out.println("Connection could not be established");
                }

                if(!Client.checkIfOffline()) {

                    System.out.println("Connection established");
                    setFrameSize("mainWindow");

                    createMenu();

                    remove(onlineOfflinePanel);
                    add(menuPanel, BorderLayout.NORTH);

                    setLocationRelativeTo(null);
                    setResizable(false);
                    this.revalidate();
                    this.repaint();
                }
                break;


            case "offline":
                setFrameSize("mainWindow");
                remove(onlineOfflinePanel);
                createMenu();
                add(logo);
                add(menuPanel, BorderLayout.NORTH);

                setLocationRelativeTo(null);
                setResizable(false);
                this.revalidate();
                this.repaint();

                break;


            case "backFromGame":
                setFrameSize("MainWindow");

                remove(gamePanel.rightDisplayPanel);
                remove(gamePanel);

                add(menuPanel, BorderLayout.NORTH);
                add(logo);
                setLocationRelativeTo(null);
                setResizable(false);
                this.revalidate();
                this.repaint();
                break;


            default:
                throw new IllegalStateException("Unexpected value: " + command);
        }
    }




    /**
     * Metoda przypisująca kolejnym oknom właściwe wymiary
     * @param type klucz ktoremu odpowiada rozmiar okna
     */
    private void setFrameSize(String type)
    {

        if (type == "mainWindow")
        {
            windowSize = new Dimension(Constants.mainWindowWidth,Constants.mainWindowHeight);
            setSize(windowSize);
        }
        else if (type == "nameWindow")
        {
            windowSize = new Dimension(Constants.nameWindowWidth,Constants.nameWindowHeight);
            setSize(windowSize);
        }
        else if (type == "gameWindow")
        {
            windowSize = new Dimension(Constants.gameWindowWidth,Constants.gameWindowHeight);
            setSize(windowSize);
        }
        else if (type == "instWindow")
        {
            windowSize = new Dimension(950, 550);
            setSize(windowSize);
        }
        else if (type == "statWindow")
        {
            windowSize = new Dimension(600, 400);
            setSize(windowSize);
        }


    }


}
