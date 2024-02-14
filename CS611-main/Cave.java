public class Cave extends Terrain {
    public Cave(){
        super(OutputColor.BLUE, (char) 0);
    }

    @Override
    public String getLegendDescription() {
        return "Cave";
    }
}
