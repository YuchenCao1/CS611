/**
 * This class stores a Player's (or Players')
 * game statistics such as their number of wins,
 * draws, or losses.
 */
public class GameRecord {
    private int numWins;
    private int numLosses;
    private int numDraws;

    public GameRecord() {
        this.numWins = 0;
        this.numLosses = 0;
        this.numDraws = 0;
    }

    public void addWin() { numWins++; }

    public void addLoss() { numLosses++; }

    public void addDraw() { numDraws++; }

    @Override
    public String toString() {
        return "("+this.numWins + "-" + this.numLosses + "-" + this.numDraws + ")";
    }
}
