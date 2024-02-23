import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MakeGrid{
    private static int totalScore;
    static ArrayList<ArrayList<Jewel>> grid = new ArrayList<>();
    private static ArrayList<Players> players = new ArrayList<>();


    //makeGrid method creates grid by reading lines in gameGrid.txt, every row will be added to ArrayList grid
    public static void makeGrid(String file1) throws FileNotFoundException {
        File file = new File(file1);
        Scanner read = new Scanner(file);

        while(read.hasNext()) {
            ArrayList<Jewel> rows= new ArrayList<>();
            String[] row = read.nextLine().split(" ");
            for (String column: row) {
                if (column.equals("D")) {
                    rows.add(new Diamond());
                }
                else if (column.equals("S")) {
                    rows.add(new Square());
                }
                else if (column.equals("T")) {
                    rows.add(new Triangle());
                }
                else if (column.equals("W")) {
                    rows.add(new Wildcard());
                }
                else if (column.equals("+")) {
                    rows.add(new Plus());
                }
                else if (column.equals("-")) {
                    rows.add(new Minus());
                }
                else if (column.equals("\\")) {
                    rows.add(new Backslash());
                }
                else if (column.equals("/")) {
                    rows.add(new Slash());
                }
                else if (column.equals("|")) {
                    rows.add(new VerticalBar());
                }
            }
            grid.add(rows);
        }
    }

    //users method adds users to players list by reading leaderboard.txt
    public static void users(String file1) throws FileNotFoundException {
        File file = new File(file1);
        Scanner read = new Scanner(file);
        while (read.hasNext()) {
            String[] row = read.nextLine().split(" ");
            Players user = new Players();
            user.setName(row[0]);
            user.setUserScore(Integer.parseInt(row[1]));
            players.add(user);
        }
    }

    //takeAction method reads command.txt, it calls action method for every coordinate,action methods simply find triplets,delete them.
    //after every action method, showGrid method is called to show the latest version of grid
    //monitoring txt is created in here, by calling appropriate functions
    public static void takeAction(String file1) throws IOException {
        Players user = new Players();
        File file = new File(file1);
        Scanner read = new Scanner(file);
        FileWriter leaderboardtxt = new FileWriter("leaderboard.txt",true);
        PrintWriter writer = new PrintWriter("monitoring.txt");

        writer.println("Game grid:\n");
        showGrid(writer);
        while(read.hasNext()) {
            writer.print("\nSelect coordinate or enter E to end the game: ");
            String[] command = read.nextLine().split(" ");
            setTotalScore(Jewel.getGridScore()); //in every command, the grid score before command is added to total score

            if (command[0].equals("E")) {
                writer.println("E");
                String name = read.nextLine();//if command equals E, it took user's name after E command, and set calculated score to user's score
                user.setName(name);
                user.setUserScore(getTotalScore());
                leaderboardtxt.write("\n"+user.getName()+" "+user.getUserScore());
                players.add(user);
                writer.println("\nTotal score: "+user.getUserScore()+" points");
                writer.print("\nEnter name: ");
                writer.println(user.getName());
                leaderboard(user,writer); //after sorting players, it writes rank properties to monitoring txt
            }

            else{
                int row = Integer.parseInt(command[0]);
                int column = Integer.parseInt(command[1]);
                writer.println(row+" "+column+"\n");
                try{
                    Jewel.setGridScore(-Jewel.getGridScore()); //setGridScore is used by adding values.So, whenever a new command comes, to set that action score to 0, (-) is used
                    grid.get(row).get(column).action(row, column);
                    moveBlanksUp();
                    showGrid(writer);
                    writer.println("\nScore: "+ Jewel.getGridScore()+" points");
                }catch(Exception e) {
                    writer.println("Please enter a valid coordinate");
                }
            }
        }
        writer.close();
        leaderboardtxt.close();
    }

    //leaderboard method is used to sort players
    public static void leaderboard(Players user,PrintWriter writer) {
        Collections.sort(players);
        int rank = Collections.binarySearch(players,user)+1;
        boolean andword = false; //if user is not in the top of the list or bottom, 'and' is used to print out score difference
        writer.print("\nYour rank is "+rank+"/"+players.size()+", your score is ");
        try{
            writer.print((players.get(rank-2).getUserScore()- user.getUserScore())+" points lower than "+players.get(rank-2).getName());
            andword = true;
        }catch(Exception ignored) {
        }
        try {
            players.get(rank); //if this doesn't throw exception, that means it exists, therefore 'and' can be used
            if (andword) {
                writer.print(" and ");
            }
            writer.print((user.getUserScore()-players.get(rank).getUserScore())+" points higher than "+players.get(rank).getName());

        }catch(Exception ignored) {
        }

        writer.println();
        writer.print("\nGood bye!");
    }

    //moveBlanksUp method is used to fall vertical, it is called after every coordinate command
    public static void moveBlanksUp() {
        for (int iterate = grid.size()-1;iterate>=0;iterate--) {
            for (int i = grid.size() - 1; i >= 0; i--) {
                for (int j = grid.get(0).size() - 1; j >= 0; j--) {
                    if (grid.get(i).get(j) == null && i - 1 >= 0) {
                        Jewel temp = grid.get(i).get(j);
                        grid.get(i).set(j, grid.get(i - 1).get(j));
                        grid.get(i - 1).set(j, temp);
                    }
                }
            }
        }
    }

    //showGrid function shows grid's look
    public static void showGrid(PrintWriter writer) {
        for (ArrayList<Jewel> jewels : grid) {
            for (int j = 0; j < jewels.size(); j++) {
                if (jewels.get(j)==null) {
                    writer.print("  ");
                }
                else {
                    writer.print(jewels.get(j).getGridLetter() + " ");
                }
            }
            writer.println();
        }
    }

    public static void setTotalScore(int score) {
        totalScore += score;
    }

    public static int getTotalScore() {
        return totalScore;
    }
}
