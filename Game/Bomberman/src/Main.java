import game.panel.Frame;

import java.awt.*;
import java.io.IOException;

public class Main {


    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            game.panel.Frame ex = null;

            try {
                ex = new Frame();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ex.setVisible(true);
        });
    }
}
