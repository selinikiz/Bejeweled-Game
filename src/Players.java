public class Players implements Comparable<Players> {
    private String name;
    private int userScore;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setUserScore(int userscore) {
        this.userScore += userscore;
    }

    public int getUserScore() {
        return userScore;
    }


    @Override
    public int compareTo(Players player) {
        int compare=0;
        if (this.userScore > player.getUserScore()) {
            compare= -1;
        }
        else if (this.userScore< player.getUserScore()) {
            compare= 1;
        }
        return compare;
    }
}
