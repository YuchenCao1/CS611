import java.util.Random;
public class World implements Print_World { //Create the board
    public int world_length;
    public int world_width;
    private int row;
    private int column;
    private boolean if_confirm_world;
    private int number_of_markets = 0;

    public World(int board_length, int board_width) {
        this.world_length = board_length;
        this.world_width = board_width;
    }

    public void Reset_World_Size(int board_length, int board_width) throws Exception {//Reset World Size
        this.world_length = board_length;
        this.world_width = board_width;
    }

    public String[][] Create_World(int board_length, int board_width, String[][] areas) throws Exception {//Create World
        this.world_length = board_length;
        this.world_width = board_width;
        Random random = new Random();
        for (int i = 0; i < world_length; i++) {
            for (int j = 0; j < world_width; j++) {
                double chance = random.nextDouble();
                if (chance < 0.5) {
                    areas[i][j] = "0"; // common spaces
                } else if (chance < 0.7) {
                    areas[i][j] = "2"; // inaccessible spaces
                } else {
                    areas[i][j] = "M"+number_of_markets; // market spaces
                    number_of_markets++;
                }
            }
        }
        if (areas[0][0].charAt(0) == 'M' ){
            areas[0][0] = "1" + areas[0][0];
        }
        else {
            areas[0][0] = "1";
        }
        return areas;
    }

    public int Get_Number_of_Markets() throws Exception{//Get number of markets
        return number_of_markets;
    }

    public void PrintWorld(String[][] areas) throws Exception {//Print the world
        for (column = 0; column < world_width - 1; column++) {
            if (areas[0][column] == "0") {
                System.out.printf("\t|");
            } else if (areas[0][column] == "1") {
                System.out.printf("Y");
                System.out.printf("\t|");
            } else if (areas[0][column] == "2") {
                System.out.printf("X");
                System.out.printf("\t|");
            } else if (areas[0][column].charAt(0) == 'M') {
                System.out.printf("M");
                System.out.printf("\t|");
            } else{
                System.out.printf("YM");
                System.out.printf("\t|");
            }
        }
        if (areas[0][world_width - 1] == "0") {
            System.out.printf("\n");
        } else if (areas[0][world_width - 1] == "1") {
            System.out.printf("Y\n");
        } else if (areas[0][world_width - 1] == "2") {
            System.out.printf("X\n");
        } else if (areas[0][world_width - 1].charAt(0) == 'M') {
            System.out.printf("M\n");
        } else{
            System.out.printf("YM\n");
        }

        for (row = 1; row < world_length; row++) {
            for (column = 0; column < world_width; column++) {
                System.out.printf("--------");
            }
            System.out.printf("\n");
            for (column = 0; column < world_width - 1; column++) {
                if (areas[row][column] == "0") {
                    System.out.printf("\t|");
                } else if (areas[row][column] == "1") {
                    System.out.printf("Y");
                    System.out.printf("\t|");
                } else if (areas[row][column] == "2") {
                    System.out.printf("X");
                    System.out.printf("\t|");
                } else if (areas[row][column].charAt(0) == 'M') {
                    System.out.printf("M");
                    System.out.printf("\t|");
                }else {
                    System.out.printf("YM");
                    System.out.printf("\t|");
                }
            }
            if (areas[row][world_width - 1] == "0") {
                System.out.printf("\n");
            } else if (areas[row][world_width - 1] == "1") {
                System.out.printf("Y\n");
            } else if (areas[row][world_width - 1] == "2") {
                System.out.printf("X\n");
            } else if (areas[row][world_width - 1].charAt(0) == 'M') {
                System.out.printf("M\n");
            } else{
                System.out.printf("YM\n");
            }
        }
    }

    public boolean Confirm_World() throws Exception {//Get if user confirm the current world
        Get_user_Answer user_answer1 = new Get_user_Answer();
        System.out.println("Do you need to refresh the map？(y/n)");
        if_confirm_world = !user_answer1.Get_y_or_n_Answer();
        return if_confirm_world;
    }
}