public class Player {

    // Player class that has current Score and Name
    private final String name;
    private int score;
    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    public String getName() {
        return name;
    }
    public int getScore() {
        return score;
    }

    // Method to increase the Players Score
    public void getPoint() {
        this.score += 1;
    }
}
