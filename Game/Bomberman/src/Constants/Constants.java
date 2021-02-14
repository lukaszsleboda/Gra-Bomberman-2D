package Constants;



import game.ServerConnection.Client;
import game.data.DataLoader;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Definicja klasy Constants
 */
public final class Constants
{
    private static DataLoader file;

    public static final String configFile = "LayoutConfig.properties";
    public static final String InstructionFile = "InstructionConfig.properties";
    public static final String StatisticFile = "StatisticPanelConfig.properties";
    public static final String mapFile = "GameParametersConfig.properties";

    public static int numberOfLine;

    public static int mainWindowWidth;
    public static int mainWindowHeight;

    public static int nameWindowWidth;
    public static int nameWindowHeight;

    public static int gameWindowWidth;
    public static int gameWindowHeight;


    public static int rightDisplayWidth;
    public static int rightDisplayHeight;

    public static int ServerDisplayWidth;
    public static int ServerDisplayHeight;


    public static String gameName;

    //menuPanel
    public static String startGameButtonLabel;
    public static String statisticButtonLabel;
    public static String helpButtonLabel;

    //NameSetPanel
    public static String backButtonLabel;
    public static String defaultTextLabel;

    //Server panel
    public static String offlineButtonLabel;
    public static String onlineButtonLabel;

    //InstructionPanel;
    public static int rowsNumber;
    public static int columnsNumber;
    public static String instructionText;

    public static String instructionFont;
    public static int instructionFontSize;

    //StatisticPanel
    public static int statRowsNumber;
    public static int statColumnsNumber;

    public static String statisticFont;
    public static int statisticFontSize;

    //Game
    public static String map;

    //Right Display
    public static String levelLabelText;
    public static String pointsLabelText;
    public static String lifeLabelText;

    //Clipper
    public static int clipperSpeed;
    public static int initialClipperDirection;
    public static int clipperPoints;

    //Player
    public static int numberOfLifes;
    public static int step;

    public static String bombGraphic;
    public static String clipperGraphic;
    public static String dstWallGraphic;
    public static String wallGraphic;
    public static String playerGraphic;
    public static String portalGraphic;
    public static String explosionCGraphic;
    public static String explosionLGraphic;
    public static String explosionRGraphic;
    public static String explosionDGraphic;
    public static String explosionUGraphic;
    public static String pointsPackGraphic;
    public static String completeGraphic;
    public static String logoGraphic;
    public static String playerLGraphic;
    public static String playerRGraphic;
    public static String playerDGraphic;
    public static String playerUGraphic;
    public static String spikeGraphic;

    //Dst wall
    public static int wallPoints;

    public static int packPoints;

    public static String ipAddress;

   public static int port;


    /**
     * Metoda ladujaca instrukcje z pliku
     * @return String - tekst instrukcji
     * @throws IOException wyjatek
     */
    public static String loadInstructionText() throws IOException {
        numberOfLine = Integer.parseInt(file.getPropertyValue("numberOfLines", InstructionFile));
        String tmp = "";
        instructionText = "";
        for (int i = 1; i <= Constants.numberOfLine; i++)
        {
            tmp = file.getPropertyValue("instrukcja" + i, InstructionFile);
            instructionText = instructionText + tmp;
        }
        return instructionText;
    }


    /**
     * Metoda ladujaca instrukcje z serwera
     * @return String bedacy tekstem instrukcji
     * @throws IOException wyjatek
     */
    public static String loadInstructionFromServer() throws IOException
    {
        String instruction = Client.getInstruction();
        String[] tmp = instruction.split("-");
        String readyInstruction = "";
        for (int i=0; i<14; i++)
        {
            readyInstruction += tmp[i] + '\n';
        }
        return readyInstruction;
    }


    /**
     * Metoda ladujaca wyglad mapy z pliku
     * @param level index levelu do zaladowania
     * @return String bedacy wygladem mapy
     * @throws IOException
     */
    public static String loadMapFromFile(int level) throws IOException
    {
        map = file.getPropertyValue("map" + level, mapFile);
        return map;
    }


    /**
     * Metoda ladujaca wyglad mapy z serwera
     * @param level indeks levelu do zaladowania
     * @return String bedacy wygladem mapy
     * @throws IOException wyjatek
     */
    public static String loadMapFromServer(int level) throws IOException
    {
        String map = Client.getLevel(level);
        String[] tmp = map.split("-");
        String readyMap = null;
        for (int i=0; i<15; i++)
        {
            readyMap += tmp[i] + '\n';
        }

        return readyMap;
    }


    /**
     * Metoda przypisujaca zmiennych dane podane przez uzytkownika w momencie laczenia sie z serwerem
     * @param port numer portu
     * @param ipAddress adres ip serwera
     */
    public static void savePortAndIP(int port,String ipAddress)
    {
        Constants.port = port;
        Constants.ipAddress = ipAddress;
    }


    /**
     * Metoda przypisujaca rozmiary okien pobrane z serwera
     * @throws IOException wyjatek
     */
    public static void loadLayoutConfigFromServer() throws IOException {
        String response = Client.getWindowsConfig();
        int[] config;
        config = Arrays.stream(response.split("-")).mapToInt(Integer::parseInt).toArray();


        mainWindowHeight=config[0];
        mainWindowWidth=config[1];


        nameWindowHeight=config[2];
        nameWindowWidth=config[3];


        gameWindowHeight=config[4];
        gameWindowWidth=config[5];


        rightDisplayHeight=config[7];
        rightDisplayWidth=config[6];


        ServerDisplayHeight=config[9];
        ServerDisplayWidth=config[8];
    }


    /**
     * Metoda ustawiajaca napisy na etykietach i guzikach pobrane z serwera
     * @throws IOException wyjatek
     */
    public static void loadLabelANDButonFromServer() throws IOException
    {
        String response = Client.getLabelsConfig();
        String[] configs = response.split("-");

        gameName = configs[0];

        startGameButtonLabel = configs[1];
        statisticButtonLabel = configs[2];
        helpButtonLabel = configs[3];

        backButtonLabel = configs[4];
        defaultTextLabel = configs[5];

        levelLabelText = configs[6];
        pointsLabelText = configs[7];
        lifeLabelText = configs[8];
    }


    /**
     * Metoda przypisujaca pparametry rozgrywki pobrane z serwera
     * @throws IOException wyjatek
     */
    public static void loadGameParametersFromServer() throws IOException
    {
        String response = Client.getGameParameters();
        int[] config;
        config = Arrays.stream(response.split("-")).mapToInt(Integer::parseInt).toArray();

        clipperSpeed = config[0];
        clipperPoints = config[1];
        initialClipperDirection = config[2];

        packPoints = config[3];

        numberOfLifes = config[4];

        wallPoints = config[5];
    }


    /**
     * Lista wynikow
     */
    static ArrayList<String> ranking;

    /**
     * Metoda ladujaca liste najlepszych graczy z serwera
     * @return String zawierajacy ciag Imie punkty dla najlepszych graczy
     * @throws IOException
     */
    public static String loadRankingFromServer() throws IOException {
        ranking = new ArrayList<>();
        String fromServer = Client.getRanking();
        ranking.addAll(Arrays.asList(fromServer.split(", ")).subList(0, 5));
        String response = "";
        for (int i=0; i<5; i++) {
            response += ranking.get(i) + '\n';
        }
        System.out.print(response);
        return response;

    }

    /**
     * Tworzy linie tekstu w celu zapisania wyniku na serwerze. Wywoluje metode odpowiedzialna za
     * zapis na serwerze
     * @param nick nazwa gracza
     * @param score wynik uzyskany przez gracza
     */
    public static void saveScoreOnServer(String nick, int score) throws IOException {
        String request = "";
        request+= nick + "-" + score;
        Client.saveScore(request);
    }

    /**
     * Metoda pprzypisujaca zmiennym dane pobrane z pliku
     * @throws IOException wyjatek
     */
    public static void loadData() throws IOException {

            file = new DataLoader();

            mainWindowWidth = Integer.parseInt(file.getPropertyValue("MWIDTH", configFile));
            mainWindowHeight = Integer.parseInt(file.getPropertyValue("MHEIGHT", configFile));

            nameWindowWidth = Integer.parseInt(file.getPropertyValue("NWIDTH", configFile));
            nameWindowHeight = Integer.parseInt(file.getPropertyValue("NHEIGHT", configFile));

            gameWindowWidth = Integer.parseInt(file.getPropertyValue("GWIDTH", configFile));
            gameWindowHeight = Integer.parseInt(file.getPropertyValue("GHEIGHT", configFile));


            rightDisplayWidth = Integer.parseInt(file.getPropertyValue("RWIDTH",configFile));
            rightDisplayHeight = Integer.parseInt(file.getPropertyValue("RHEIGHT",configFile));

            ServerDisplayWidth = Integer.parseInt(file.getPropertyValue("SWIDTH",configFile));
            ServerDisplayHeight = Integer.parseInt(file.getPropertyValue("SHEIGHT",configFile));


            gameName = file.getPropertyValue("gameName", configFile);

            startGameButtonLabel = file.getPropertyValue("startButtonLabel",configFile);
            helpButtonLabel = file.getPropertyValue("helpButtonLabel",configFile);
            statisticButtonLabel = file.getPropertyValue("statisticButtonLabel", configFile);

            backButtonLabel = file.getPropertyValue("backButtonLabel",configFile);
            defaultTextLabel = file.getPropertyValue("defaultTextLabel",configFile);



            rowsNumber = Integer.parseInt(file.getPropertyValue("rowsNumber",InstructionFile));
            columnsNumber = Integer.parseInt(file.getPropertyValue("columnsNumber",InstructionFile));
            instructionFont = file.getPropertyValue("instructionFont",InstructionFile);
            instructionFontSize = Integer.parseInt(file.getPropertyValue("instructionFontSize",InstructionFile));


            statisticFont = file.getPropertyValue("statisticFont",StatisticFile);
            statisticFontSize = Integer.parseInt(file.getPropertyValue("statisticFontSize",StatisticFile));
            statRowsNumber = Integer.parseInt(file.getPropertyValue("statRowsNumber",StatisticFile));
            columnsNumber = Integer.parseInt(file.getPropertyValue("statColumnsNumber", StatisticFile));


            levelLabelText = file.getPropertyValue("levelLabelText", configFile);
            pointsLabelText = file.getPropertyValue("pointsLabelText", configFile);
            lifeLabelText = file.getPropertyValue("lifeLabelText", configFile);

            clipperSpeed = Integer.parseInt(file.getPropertyValue("clipperSpeed",mapFile));
            numberOfLifes = Integer.parseInt(file.getPropertyValue("numberOfLifes",mapFile));
            initialClipperDirection = Integer.parseInt(file.getPropertyValue("initialClipperDirection",mapFile));
            clipperPoints = Integer.parseInt(file.getPropertyValue("clipperPoints",mapFile));
            packPoints = Integer.parseInt(file.getPropertyValue("packPoints",mapFile));

            bombGraphic = file.getPropertyValue("bombGraphic",mapFile);
            clipperGraphic = file.getPropertyValue("clipperGraphic",mapFile);
            dstWallGraphic = file.getPropertyValue("dstWallGraphic", mapFile);
            wallGraphic = file.getPropertyValue("wallGraphic",mapFile);
            playerGraphic = file.getPropertyValue("playerGraphic",mapFile);
            portalGraphic=file.getPropertyValue("portalGraphic", mapFile);
            explosionCGraphic=file.getPropertyValue("explosionCGraphic",mapFile);
            explosionLGraphic=file.getPropertyValue("explosionLGraphic",mapFile);
            explosionRGraphic=file.getPropertyValue("explosionRGraphic",mapFile);
            explosionUGraphic=file.getPropertyValue("explosionUGraphic",mapFile);
            explosionDGraphic=file.getPropertyValue("explosionDGraphic",mapFile);
            pointsPackGraphic=file.getPropertyValue("pointsPackGraphic",mapFile);
            completeGraphic=file.getPropertyValue("endGameGraphic", mapFile);
            logoGraphic=file.getPropertyValue("logoGraphic",mapFile);
            playerLGraphic=file.getPropertyValue("playerL",mapFile);
            playerRGraphic=file.getPropertyValue("playerR",mapFile);
            playerDGraphic=file.getPropertyValue("playerD",mapFile);
            playerUGraphic=file.getPropertyValue("playerU",mapFile);
            spikeGraphic=file.getPropertyValue("spikeGraphic",mapFile);

            step = Integer.parseInt(file.getPropertyValue("step",mapFile));

            wallPoints = Integer.parseInt(file.getPropertyValue("wallPoints", mapFile));

            offlineButtonLabel = file.getPropertyValue("offlineButtonLabel", configFile);
            onlineButtonLabel = file.getPropertyValue("onlineButtonLabel",configFile);
    }

    static
    {
        try {
            loadData();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
