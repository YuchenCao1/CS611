public class Wall{//Create Walls
    public int[][][][] walls;
    public int walls_number;
    private int first_row;
    private int first_column;
    private int second_row;
    private int second_column;
    private int[] this_placement;

    public Wall(int board_length, int board_width) throws Exception{
        walls = new int[board_length][board_width][board_length][board_width];
        walls_number = 10;
        for (first_row = 0; first_row < board_length; first_row++){
            for (first_column = 0; first_column < board_width; first_column++){
                for (second_row = 0; second_row < board_length; second_row++) {
                    for (second_column = 0; second_column < board_width; second_column++) {
                        walls[first_row][first_column][second_row][second_column] =0;
                    }
                }
            }
        }
    }

    public void Reset_Walls(int board_length, int board_width) throws Exception{//Reset walls
        walls_number = 10;
        for (first_row = 0; first_row < board_length; first_row++){
            for (first_column = 0; first_column < board_width; first_column++){
                for (second_row = 0; second_row < board_length; second_row++) {
                    for (second_column = 0; second_column < board_width; second_column++) {
                        walls[first_row][first_column][second_row][second_column] =0;
                    }
                }
            }
        }
    }


    public int[][][][] Add_Walls(int[] this_placement) throws Exception{//Update piece(to record each move)
        walls[this_placement[0]][this_placement[1]][this_placement[2]][this_placement[3]] = 1;
        walls[this_placement[2]][this_placement[3]][this_placement[0]][this_placement[1]] = 1;
        walls[this_placement[6]][this_placement[7]][this_placement[4]][this_placement[5]] = 1;
        walls[this_placement[4]][this_placement[5]][this_placement[6]][this_placement[7]] = 1;
        return this.walls;
    }



}
