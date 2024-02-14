public class Grass extends Terrain {
    public Grass(){
        super(OutputColor.GREEN, (char) 0);
    }

    @Override
    public String getLegendDescription() {
        return "Grass";
    }
}
