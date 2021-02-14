import Ranking.Ranking;
import server.Server;

import java.io.IOException;

public class Main {
    public Main() {
    }

    public static void main(String[] var0) throws IOException {

        Ranking.loadRanking();

        (new Server()).runServer();


    }
}
