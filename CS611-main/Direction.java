public enum Direction{
    LEFT("left"),
    RIGHT("right"),
    UP("up"),
    DOWN("down");

    private String value;
    Direction(final String value){
        this.value = value;
    }

    @Override
    public String toString(){
        return value;
    }
}