public class Obstacle implements Legendable {
    private final char symbol;
    private final OutputColor color;
    public Obstacle(){
        symbol = '█';
        color = OutputColor.RED;
    }

    @Override
    public String getLegendDescription(){
        return "Obstacle";
    }

    @Override
    public char getSymbol() {
        return symbol;
    }

    @Override
    public OutputColor getOutputColor() {
        return color;
    }
}
