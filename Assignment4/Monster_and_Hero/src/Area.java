public class Area{//Create pieces
    private String[][] areas;
    private int row;
    private int column;
    private int now_piece_row;
    private int now_piece_column;
    private int[] piece_position = new int[2];
    public Area(int world_length, int world_width){
        areas = new String[world_length][world_width];
        for (row=0; row<world_length; row++){
            for (column=0; column<world_width; column++){
                areas[row][column] = "0";
            }
        }
    }

    public void Reset_Pieces(int world_length, int world_width) throws Exception{//Reset pieces
        for (row=0; row < world_length; row++){
            for (column=0; column < world_width; column++){
                areas[row][column] = "0";
            }
        }
    }





    public String[][] Move_Pieces(int[] piece_position, char movement) throws Exception{//Move a piece to a new position.
        int x = piece_position[0];
        int y = piece_position[1];

        switch (movement) {
            case 'W':
                if (this.areas[x-1][y] != "0") {
                    x -= 2;
                } else {
                    x -= 1;
                }
                break;
            case 'A':
                if (this.areas[x][y-1] != "0") {
                    y -= 2;
                } else {
                    y -= 1;
                }
                break;
            case 'S':
                if (this.areas[x+1][y] != "0") {
                    x += 2;
                } else {
                    x += 1;
                }
                break;
            case 'D':
                if (this.areas[x][y+1] != "0") {
                    y += 2;
                } else {
                    y += 1;
                }
                break;
            default:
                System.out.println("Invalid movement character");
                break;
        }
        this.areas[x][y] = "1";
        this.areas[piece_position[0]][piece_position[1]] = "0";

        return this.areas;
    }


    public int[] Get_Player_Position(int world_length, int world_width, String[][] areas) throws Exception {//Let the player give their choice of next step
        for (int row = 0; row < world_length; row++) {
            for (int column = 0; column < world_width; column++) {
                if (areas[row][column].charAt(0) == '1') {
                    piece_position[0] = row;
                    piece_position[1] = column;
                    return piece_position;
                }
            }
        }
        return piece_position;
    }

    public String[][] Get_Areas(){
        return areas;
    }
    public void Set_Areas(String[][] areas){
        this.areas = areas;
    }

    public String[][] Update_Areas(int[] player_position, char round_choice, String[][] areas){
        if (round_choice == 'W' || round_choice == 'w') {
            if(areas[player_position[0]][player_position[1]] == "1"){
                if(areas[player_position[0] - 1][player_position[1]] == "0"){
                    areas[player_position[0]][player_position[1]] = "0";
                    areas[player_position[0] - 1][player_position[1]] = "1";
                }
                else {
                    areas[player_position[0]][player_position[1]] = "0";
                    areas[player_position[0] - 1][player_position[1]] = "1" + areas[player_position[0] - 1][player_position[1]];
                }
            }
            else{
                if(areas[player_position[0] - 1][player_position[1]] == "0"){
                    areas[player_position[0]][player_position[1]] = "M" + areas[player_position[0]][player_position[1]].charAt(2);
                    areas[player_position[0] - 1][player_position[1]] = "1";
                }
                else {
                    areas[player_position[0]][player_position[1]] = "M" + areas[player_position[0]][player_position[1]].charAt(2);
                    areas[player_position[0] - 1][player_position[1]] = "1" + areas[player_position[0] - 1][player_position[1]];
                }
            }
        }

        if (round_choice == 'A' || round_choice == 'a') {
            if(areas[player_position[0]][player_position[1]] == "1"){
                if(areas[player_position[0]][player_position[1] - 1] == "0"){
                    areas[player_position[0]][player_position[1]] = "0";
                    areas[player_position[0]][player_position[1] - 1] = "1";
                }
                else {
                    areas[player_position[0]][player_position[1]] = "0";
                    areas[player_position[0]][player_position[1] - 1] = "1" + areas[player_position[0]][player_position[1] - 1];
                }
            }
            else{
                if(areas[player_position[0]][player_position[1] - 1] == "0"){
                    areas[player_position[0]][player_position[1]] = "M" + areas[player_position[0]][player_position[1]].charAt(2);
                    areas[player_position[0]][player_position[1] - 1] = "1";
                }
                else {
                    areas[player_position[0]][player_position[1]] = "M" + areas[player_position[0]][player_position[1]].charAt(2);
                    areas[player_position[0]][player_position[1] - 1] = "1" + areas[player_position[0]][player_position[1] - 1];
                }
            }
        }

        if (round_choice == 'S' || round_choice == 's') {
            if(areas[player_position[0]][player_position[1]] == "1"){
                if(areas[player_position[0] + 1][player_position[1]] == "0"){
                    areas[player_position[0]][player_position[1]] = "0";
                    areas[player_position[0] + 1][player_position[1]] = "1";
                }
                else {
                    areas[player_position[0]][player_position[1]] = "0";
                    areas[player_position[0] + 1][player_position[1]] = "1" + areas[player_position[0] + 1][player_position[1]];
                }
            }
            else{
                if(areas[player_position[0] + 1][player_position[1]] == "0"){
                    areas[player_position[0]][player_position[1]] = "M" + areas[player_position[0]][player_position[1]].charAt(2);
                    areas[player_position[0] + 1][player_position[1]] = "1";
                }
                else {
                    areas[player_position[0]][player_position[1]] = "M" + areas[player_position[0]][player_position[1]].charAt(2);
                    areas[player_position[0] + 1][player_position[1]] = "1" + areas[player_position[0] + 1][player_position[1]];
                }
            }
        }

        if (round_choice == 'D' || round_choice == 'd') {
            if(areas[player_position[0]][player_position[1]] == "1"){
                if(areas[player_position[0]][player_position[1] + 1] == "0"){
                    areas[player_position[0]][player_position[1]] = "0";
                    areas[player_position[0]][player_position[1] + 1] = "1";
                }
                else {
                    areas[player_position[0]][player_position[1]] = "0";
                    areas[player_position[0]][player_position[1] + 1] = "1" + areas[player_position[0]][player_position[1] + 1];
                }
            }
            else{
                if(areas[player_position[0]][player_position[1] + 1] == "0"){
                    areas[player_position[0]][player_position[1]] = "M" + areas[player_position[0]][player_position[1]].charAt(2);
                    areas[player_position[0]][player_position[1] + 1] = "1";
                }
                else {
                    areas[player_position[0]][player_position[1]] = "M" + areas[player_position[0]][player_position[1]].charAt(2);
                    areas[player_position[0]][player_position[1] + 1] = "1" + areas[player_position[0]][player_position[1] + 1];
                }
            }
        }
        return areas;
    }
}
