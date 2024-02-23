import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        MakeGrid.makeGrid(args[0]);
        MakeGrid.users("leaderboard.txt");
        MakeGrid.takeAction(args[1]);
    }
}
