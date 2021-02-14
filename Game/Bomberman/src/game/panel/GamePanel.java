package game.panel;


import Constants.Constants;
import game.Additions.Cheat;
import game.ServerConnection.Client;
import game.Ranking.HighScoreManager;
import game.object.Portal;
import game.object.Clipper;
import game.object.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;


/**
 * Klasa panelu gry
 */

public class GamePanel extends JPanel implements ActionListener,Runnable {

    /**
     * Referencja na managera rankingu
     */
    HighScoreManager highScoreManager = new HighScoreManager();
    /**
     * Referencja do panelu bocznego
     */
    RightDisplayPanel rightDisplayPanel;
    /**
     * Zmienna okreslajaca na ktorym levelu znajduje sie gracz
     */
    private int level = 1;
    /**
     * Zmienna pomocnicza okreslajaca odleglosc miedzy elementami przy tworzeniu swiata
     */
    private final int OFFSET = 30;
    /**
     * Zmienna pomocnicza okreslajaca odleglosc miedzy elementami przy tworzeniu swiata
     */
    private final int SPACE = 30;
    /**
     * Zmienna pomocnicza określająca rodzaj kolizji (z lewą przeszkodą)
     */
    private final int LEFT_COLLISION = 1;
    /**
     * Zmienna pomocnicza określająca rodzaj kolizji (z prawą przeszkodą)
     */
    private final int RIGHT_COLLISION = 2;
    /**
     * Zmienna pomocnicza określająca rodzaj kolizji (z gorna przeszkodą)
     */
    private final int TOP_COLLISION = 3;
    /**
     * Zmienna pomocnicza określająca rodzaj kolizji (z dolna przeszkodą)
     */
    private final int BOTTOM_COLLISION = 4;
    /**
     * Zmienna informujaca czy bomba zostala postawiona
     */
    private boolean putBomb = false;
    /**
     * Zmienna informujaca czy ma wystapic eksplozja
     */
    private boolean explosion = false;
    /**
     * Zmienna okreslajaca czas bomby
     */
    private float putBombTime = 0;
    /**
     * Zmienna informujaca czy gra jest w toku
     */
    private boolean running = true;
    /**
     * Zmienna informujaca czy gra jest wstrzymywana (pauza)
     */
    private boolean paused = false;

    /**
     * Liczba klatek na sekunde
     */
    private int fps = 60;
    /**
     * Licznik klatek
     */
    private int frameCount = 0;
    private int lastSecondTime = 0;

    /**
     * Lista niezniszczalnych scian na planszy
     */
    private ArrayList<Wall> walls;
    /**
     * Lista zniszczalnych scian na planszy
     */
    private ArrayList<DestructibleWall> destructibleWalls;
    /**
     * Lista clipperow na planszy
     */
    private ArrayList<Clipper> clippers;
    /**
     * Lista wybuchow na planszy
     */
    private ArrayList<Explosion> explosions;
    /**
     * Lista wszytskich elementow swiata
     */
    public ArrayList<Actor> world = new ArrayList<>();
    /**
     * Lista paczek z punktami
     */
    private ArrayList<PointsPack> pointsPacks;



    /**
     * Obiekt reprezentujacy portal do nastepnego poziomu
     */
    private Portal area;
    /**
     * Obiekt reprezentujacy gracza
     */
    Player player = Player.getInstance();
    /**
     * Obiekt reprezentujacy bombe
     */
    private Bomb bomb;




    /**
     * Referencja do eksplozji
     */
    private Explosion explosion1;
    /**
     * Referencja do eksplozji
     */
    private Explosion explosion2;
    /**
     * Referencja do eksplozji
     */
    private Explosion explosion3;
    /**
     * Referencja do eksplozji
     */
    private Explosion explosion4;
    /**
     * Referencja do eksplozji
     */
    private Explosion explosion5;


    /**
     * Zmienne pomocnicze
     */
    private int w = 0;
    private int h = 0;

    private Thread gamethreed;

    ImageIcon icon1 = new ImageIcon(Constants.playerLGraphic);
    Image playerL = icon1.getImage();
    ImageIcon icon2 = new ImageIcon(Constants.playerRGraphic);
    Image playerR = icon2.getImage();
    ImageIcon icon3 = new ImageIcon(Constants.playerDGraphic);
    Image playerD = icon3.getImage();
    ImageIcon icon4 = new ImageIcon(Constants.playerUGraphic);
    Image playerU = icon4.getImage();

    /**
     * flaga informujaca czy poziom zostal ukonczony
     */
    private boolean isCompleted = false;



    /**
     * flaga informujaca czy aktywne sa czity
     */
    private boolean cheatsActive = false;

    /**
     * Zmienna przechowujaca wyglad swiata (mapy)
     */
    private String levelData;

private CheatFrame cheatFrame;



    /**
     * Konstruktor Panelu GameScreen
     * @param pick
     */
    public GamePanel(ActionListener pick) throws IOException {
        setLayout(new BorderLayout());
        initBackButton(pick);
        add(rightDisplayPanel, BorderLayout.EAST);

        if (!Client.isOffline)
        {
            levelData = Constants.loadMapFromServer(1);
            Constants.loadGameParametersFromServer();
        }
        if (Client.isOffline)
        {
            levelData = Constants.loadMapFromFile(1);
        }
        initBoard();
    }



    /**
     * Metoda dodajaca obsluge klawiatury i rozpoczynajaca tworzenie swiata
     */
    private void initBoard()
    {
        addKeyListener(new TAdapter());
        setFocusable(true);
        initWorld();
    }


    /**
     * Metoda inicjujaca guzik powrotu
     * @param pick action listener
     */
    private void initBackButton(ActionListener pick)
    {
        rightDisplayPanel = new RightDisplayPanel();
        rightDisplayPanel.backButton.addActionListener(pick);
        rightDisplayPanel.backButton.setActionCommand("backFromGame");
        rightDisplayPanel.backButton.setEnabled(false);
        rightDisplayPanel.backButton.setOpaque(true);
    }


    /**
     * Metoda dodajaca elementy swiata do odpowiednich list
     */
    private void initWorld() {

        walls = new ArrayList<>();
        clippers = new ArrayList<>();
        destructibleWalls = new ArrayList<>();
        explosions = new ArrayList<>();
        pointsPacks = new ArrayList<>();

        int x = OFFSET;
        int y = OFFSET;

        Wall wall;
        Clipper clipper;
        DestructibleWall dstWall;

        for (int i = 0; i < levelData.length(); i++) {

            char item = levelData.charAt(i);

            switch (item) {

                case '\n':
                    y += SPACE;

                    if (this.w < x) {
                        this.w = x;
                    }

                    x = OFFSET;
                    break;

                case '#':
                    wall = new Wall(x, y);
                    walls.add(wall);
                    x += SPACE;
                    break;

                case 'P':
                    area = new Portal(x, y);
                    break;

                case ' ':
                    x += SPACE;
                    break;

                case '*':
                    dstWall = new DestructibleWall(x, y, false, false);
                    destructibleWalls.add(dstWall);
                    x += SPACE;
                    break;

                case '^':
                    dstWall = new DestructibleWall(x, y, true, false);
                    destructibleWalls.add(dstWall);
                    x += SPACE;
                    break;

                case '$':
                    dstWall = new DestructibleWall(x, y, false, true);
                    destructibleWalls.add(dstWall);
                    x += SPACE;
                    break;

                case 'c':
                    clipper = new Clipper(x, y);
                    clippers.add(clipper);
                    x += SPACE;
                    break;


                default:
                    System.out.println("Unknown Object");
                    break;
            }
            h = y;
        }
    }


    /**
     * Metoda tworzaca nowy wyjatek i ropoczynajaca petle gry
     */
    @Override
    public void addNotify() {
        super.addNotify();
        gamethreed = new Thread(this, "Game Threed");
        gamethreed.start();
    }


    /**
     * Metoda rysujaca swiat
     * @param g grafika
     */
    private void buildWorld(Graphics g) {

        world.clear();

        g.setColor(Color.darkGray);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        if (putBomb) {
            world.add(bomb);
        }
        if (explosion) {
            world.addAll(explosions);
        }
        world.add(area);
        world.addAll(walls);
        world.addAll(clippers);
        world.addAll(destructibleWalls);
        world.add(player);
        world.addAll(pointsPacks);


        for (int i = 0; i < world.size(); i++)
        {
            Actor item = world.get(i);

            g.drawImage(item.getImage(), scalePointX(item.x()), scalePointY(item.y()), scaleWidth(item.getImage().getHeight(this)), scaleHeight(item.getImage().getHeight(this)) ,  this);
        }
    }


    /**
     * Metoda wywolujaca rysowanie mapy gry
     *
     * @param g grafika
     */
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        buildWorld(g);
        printInfoGraphic(g);
        frameCount ++;
    }


    /**
     * Metoda rysujaca grafiki infoormacyjne
     * @param g grafika
     */
    private void printInfoGraphic(Graphics g)
    {
        if (isCompleted)
        {
            ImageIcon endG = new ImageIcon(Constants.completeGraphic);
            Image endI = endG.getImage();
            g.drawImage(endI,120,120, this);

        }
    }


    @Override
    public void actionPerformed(ActionEvent ae) {

    }




    /**
     * Metoda obslugujaca glowny watek gry GameLoop
     */
    @Override
    public void run() {

        final double GAME_HERTZ = 30.0;

        final double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;

        final int MAX_UPDATES_BEFORE_RENDER = 5;

        double lastUpdateTime = System.nanoTime();

        double lastRenderTime = System.nanoTime();


        final double TARGET_FPS = 60;
        final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;


        lastSecondTime = (int) (lastUpdateTime / 1000000000);

        while (running) {


            double now = System.nanoTime();
            int updateCount = 0;

            if (!paused) {

                while (now - lastUpdateTime > TIME_BETWEEN_UPDATES && updateCount < MAX_UPDATES_BEFORE_RENDER) {

                    try {
                        update();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    lastUpdateTime += TIME_BETWEEN_UPDATES;
                    updateCount++;

                }

                if (now - lastUpdateTime > TIME_BETWEEN_UPDATES) {
                    lastUpdateTime = now - TIME_BETWEEN_UPDATES;
                }


                repaint();
                lastRenderTime = now;


                int thisSecond = (int) (lastUpdateTime / 1000000000);
                if (thisSecond > lastSecondTime) {
                    System.out.println("FPS: " + frameCount);
                    fps = frameCount;
                    frameCount = 0;
                    lastSecondTime = thisSecond;
                }

                while (now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS && now - lastUpdateTime < TIME_BETWEEN_UPDATES) {
                    Thread.yield();

                    try {
                        Thread.sleep(1);
                    } catch (Exception e) {
                    }

                    now = System.nanoTime();
                }
            }
            System.out.print("");
        }
    }


    /**
     * Metoda aktualizujaca wyglad mapy
     */
    public void update() throws IOException
    {
            rightDisplayPanel.update(level,cheatsActive);
            moveClipper();
            movePlayer(player.getDirect());
            clipperKILLplayer();
            pickPointsPack();
            bomb();
            isCompleted();
    }


    /**
     * Metoda obslugujaca wybuch bomby
     */
    public void bomb() throws IOException {
        if (putBomb == true)
        {
            for (int i = 0; i < 3; i++)
            {

                if (lastSecondTime == putBombTime )
                {
                    bomb.bombTicking(0);
                }

                if (lastSecondTime == putBombTime +1)
                {
                    bomb.bombTicking(1);
                }
                if (lastSecondTime == putBombTime +2)
                {
                    bomb.bombTicking(2);
                    explosion = true;

                    for (int k=0; k < explosions.size(); k++)
                    {
                        explosions.get(k).destroyWall(destructibleWalls, walls, pointsPacks);
                        explosions.get(k).killClipper(clippers);
                        bombKILLplayer();
                    }
                }
                if (lastSecondTime == putBombTime +3)
                {
                    putBomb = false;
                    explosion = false;
                    explosions.clear();
                }
            }
        }
    }


    /**
     * Klasa sterowania przyciskami klawiatury
     */
    private class TAdapter extends KeyAdapter {

        /**
         * Metod wyonujaca odpowiednie czynnosci po kliknieciu guzika klawiatury
         * @param e klawisz
         */
        @Override
        public void keyPressed(KeyEvent e) {

            if (isCompleted) {
                return;
            }

            int key = e.getKeyCode();
            if (player.getDirect() == "wait") {
                switch (key) {

                    case KeyEvent.VK_P:
                        if (putBomb == false) {
                            paused = !paused;
                        }
                        break;


                    case KeyEvent.VK_LEFT:

                        if (checkWallCollision(player, LEFT_COLLISION)) {
                            return;
                        }
                        player.setDirect("l");
                        break;

                    case KeyEvent.VK_RIGHT:

                        if (checkWallCollision(player, RIGHT_COLLISION)) {
                            return;
                        }
                        player.setDirect("r");
                        break;

                    case KeyEvent.VK_UP:

                        if (checkWallCollision(player, TOP_COLLISION)) {
                            return;
                        }
                        player.setDirect("u");
                        break;

                    case KeyEvent.VK_DOWN:

                        if (checkWallCollision(player, BOTTOM_COLLISION)) {
                            return;
                        }
                        player.setDirect("d");
                        break;

                    case KeyEvent.VK_R:
                        restartLevel();
                        break;

                    case KeyEvent.VK_C:
                        cheatsActive = true;
                        cheatFrame = new CheatFrame();
                        cheatFrame.setVisible(true);
                        break;

                    case KeyEvent.VK_SPACE:
                        if (putBomb == false) {
                            bomb = new Bomb(player.x(), player.y());
                            explosion1 = new Explosion(bomb.x() - 30, bomb.y(), "left");
                            explosion2 = new Explosion(bomb.x() + 30, bomb.y(), "right");
                            explosion3 = new Explosion(bomb.x(), bomb.y() + 30, "down");
                            explosion4 = new Explosion(bomb.x(), bomb.y() - 30, "up");
                            explosion5 = new Explosion(bomb.x(),bomb.y(), "mid");
                            explosions.add(explosion1);
                            explosions.add(explosion2);
                            explosions.add(explosion3);
                            explosions.add(explosion4);
                            explosions.add(explosion5);
                            putBomb = true;
                            putBombTime = lastSecondTime;
                            break;
                        }

                    case KeyEvent.VK_1:
                        if (cheatsActive)
                        Cheat.unlimitedLifes();
                        break;

                    case KeyEvent.VK_2:
                        if (cheatsActive)
                        Cheat.showPortal(area, destructibleWalls);
                        break;

                    case  KeyEvent.VK_3:
                        if (cheatsActive)
                        Cheat.killClipers(clippers);
                        break;

                    case KeyEvent.VK_4:
                        if (cheatsActive)
                        Cheat.showPointsPacks(destructibleWalls, pointsPacks);
                        break;

                    case KeyEvent.VK_5:
                        if (cheatsActive)
                        Cheat.addPoints();
                        break;

                    case KeyEvent.VK_6:
                        if (cheatsActive)
                        Cheat.StopClipers(clippers);

                    default:
                        break;
                }
            }
        }
    }


    /**
     * Metoda sprawdzajaca kolizje ze sciana
     * @param actor obiekt ktorego zdezenie sprawdmy
     * @param type strona kolizji
     * @return czy nastapaila kolizja
     */
    private boolean checkWallCollision(Actor actor, int type) {
        switch (type) {

            case LEFT_COLLISION:
                for (int i = 0; i < walls.size(); i++) {

                    Wall wall = walls.get(i);

                    if (actor.isLeftCollision(wall))
                    {
                        return true;
                    }
                }

                for (int i = 0; i < destructibleWalls.size(); i++) {
                    DestructibleWall dstWall = destructibleWalls.get(i);
                    if (actor.isLeftCollision(dstWall))
                    {
                        return true;
                    }
                }
                return false;

            case RIGHT_COLLISION:

                for (int i = 0; i < walls.size(); i++) {

                    Wall wall = walls.get(i);

                    if (actor.isRightCollision(wall)) {
                        return true;
                    }
                }

                for (int i = 0; i < destructibleWalls.size(); i++) {
                    DestructibleWall dstWall = destructibleWalls.get(i);
                    if (actor.isRightCollision(dstWall))
                    {
                        return true;
                    }
                }
                return false;

            case TOP_COLLISION:

                for (int i = 0; i < walls.size(); i++) {

                    Wall wall = walls.get(i);

                    if (actor.isTopCollision(wall))
                    {
                        return true;
                    }
                }

                for (int i = 0; i < destructibleWalls.size(); i++) {
                    DestructibleWall dstWall = destructibleWalls.get(i);
                    if (actor.isTopCollision(dstWall))
                    {
                        return true;
                    }
                }
                return false;

            case BOTTOM_COLLISION:

                for (int i = 0; i < walls.size(); i++) {

                    Wall wall = walls.get(i);

                    if (actor.isBottomCollision(wall))
                    {
                        return true;
                    }
                }

                for (int i = 0; i < destructibleWalls.size(); i++) {
                    DestructibleWall dstWall = destructibleWalls.get(i);
                    if (actor.isBottomCollision(dstWall)) {

                        return true;
                    }
                }
                return false;

            default:
                break;
        }
        return false;
    }


    /**
     * Metoda sprawdzajaca czy level zostal ukonczony
     */
    public void isCompleted() throws IOException {
        if(area.isLevelComplete())
        {
            paused = true;
            switch (level)
            {
                case 1: // Po ukonczeniu 1
                    if (!Client.isOffline) levelData = Constants.loadMapFromServer(2);
                    if (Client.isOffline) levelData = Constants.loadMapFromFile(2);

                    world.clear();
                    clippers.clear();
                    destructibleWalls.clear();
                    walls.clear();
                    pointsPacks.clear();
                    area = null;

                    initWorld();
                    paused = false;
                    level=2;
                    break;

                case 2: // Po ukonczeniu 2
                    if (!Client.isOffline) levelData = Constants.loadMapFromServer(3);
                    if (Client.isOffline) levelData = Constants.loadMapFromFile(3);
                    world.clear();
                    clippers.clear();
                    destructibleWalls.clear();
                    walls.clear();
                    pointsPacks.clear();
                    area = null;

                    initWorld();
                    paused = false;
                    level=3;
                    break;

                case 3: // Po ukonczeniu 3
                    paused = true;
                    running = false;
                    rightDisplayPanel.backButton.setEnabled(true);
                    finishThisGame();
                    level=1;
                    restartLevel();
                    isCompleted = true;
                    break;
            }
        }
    }


    /**
     * Metoda resetujaca gre
     */
    private void restartLevel() {

        walls.clear();
        clippers.clear();
        explosions.clear();
        destructibleWalls.clear();
        world.clear();
        player.setX(60);
        player.setY(60);
        player.setPoints(0);
        initWorld();

        if (isCompleted) {
            isCompleted = false;
        }
    }


    /**
     * Metoda poruszajaca clipperem
     */
    private void moveClipper()
    {
        for (int i=0; i<clippers.size(); i++)
        {
            if (checkWallCollision(clippers.get(i), LEFT_COLLISION)) {
                clippers.get(i).setDirection(1);

            } else if (checkWallCollision(clippers.get(i), RIGHT_COLLISION)) {
                clippers.get(i).setDirection(-1);
            }
            clippers.get(i).move(clippers.get(i).getDirection(),0);
        }
    }



    /**
     * Metoda poruszajaca graczem
     * @param direction kierunek w jakim ma sie poruszac gracz
     */
    public void movePlayer(String direction)
    {
            switch (direction) {
                case "l":
                    if (player.getStepCounter()<10)
                    {

                    player.setStepCounter(player.getStepCounter()+1);
                    player.setImage(playerL);
                    player.move(-3, 0);
                    }
                    else
                    {
                        player.setDirect("wait");
                        player.setStepCounter(0);

                    }
                    break;

                case "r":
                    if (player.getStepCounter()<10)
                    {

                        player.setStepCounter(player.getStepCounter()+1);
                        player.setImage(playerR);
                        player.move(3, 0);
                    }
                    else
                    {
                        player.setDirect("wait");
                        player.setStepCounter(0);

                    }
                    break;

                case "u":
                    if (player.getStepCounter()<10)
                    {

                        player.setStepCounter(player.getStepCounter()+1);
                        player.setImage(playerU);
                        player.move(0, -3);
                    }
                    else
                    {
                        player.setDirect("wait");
                        player.setStepCounter(0);

                    }
                    break;

                case "d":
                    if (player.getStepCounter()<10)
                    {

                        player.setStepCounter(player.getStepCounter()+1);
                        player.setImage(playerD);
                        player.move(0, 3);
                    }
                    else
                    {
                        player.setDirect("wait");
                        player.setStepCounter(0);

                    }
                    break;

                case "wait":
                    break;

                default:
                    break;
            }
    }



    /**
     * Metoda konczaca rozgrywke i przygotowujaca nowa gre
     * @throws IOException wyajtek
     */
    private void finishThisGame() throws IOException
    {
        if(Client.isOffline) {
            highScoreManager.addScore(player.getName(), player.getPoints());
            player.setLifes(Constants.numberOfLifes);
        }
        if(!Client.isOffline) {
            Constants.saveScoreOnServer(player.getName(), player.getPoints());
            Constants.loadGameParametersFromServer();
        }
        player.setPoints(0);

    }

    /**
     * Metoda zbierajaca paczke z punktami
     */
    public void pickPointsPack()
    {
        for (int i = 0; i<pointsPacks.size(); i++)
        {
            if (pointsPacks.get(i).isPackPick())
            {
                pointsPacks.remove(i);
            }
        }
    }

    /**
     * Metoda: bomba zabija gracza
     */
    private void  bombKILLplayer() throws IOException {
        for (int i=0; i<explosions.size(); i++)
        {
            if (explosions.get(i).isPlayerKill(player))
            {
                paused = true;
                finishThisGame();
                rightDisplayPanel.backButton.setEnabled(true);
            }

        }
    }

    /**
     * Metoda: clipper zabija gracza
     */
    private void clipperKILLplayer() throws IOException {
        for (int i=0; i<clippers.size(); i++)
        {
            if (clippers.get(i).isPlayerKill( player))
            {
                paused = true;
                finishThisGame();
                rightDisplayPanel.backButton.setEnabled(true);
            }
        }
    }



    int wsp_x = 950;
    int wsp_y = 500;

    /**
     * Funckja pozwala na scalowanie pojedyńczej współrzędnej y
     * @param y wsp y
     * @return ypoints
     */
    public int scalePointY(int y)
    {
        float h = getHeight();
        h = h / wsp_y;
        float ypointf;
        int ypoint;
        ypointf = y* h;
    ypoint = Math.round(ypointf);
        return ypoint;
    }

    /**
     * Funckja pozwala na scalowanie pojedyńczej współrzędnej x
     * @param x wsp x
     * @return xpoint
     */
    public int scalePointX(int x)
    {
        float w = getWidth();
        w = w / wsp_x;
        float xpointf;
        int xpoint;
        xpointf = x * w;
        xpoint = Math.round(xpointf);
        return xpoint;
    }

    /**
     * Metoda odpowiedzialna za skalowanie szerokosci
     * @param width szerokosc panelu
     * @return nowa szerokosc
     */
    public int scaleWidth(int width)
    {
        int w;
        w = width*getWidth()/wsp_x;
        return w;
    }

    /**
     * Metoda odpowiedzialna za skalowanie wysokosci
     * @param height wyskosoc
     * @return niwa wysokosc
     */
    public int scaleHeight(int height)
    {
        int h;
        h = height*getHeight()/wsp_y;
        return h;
    }
}
