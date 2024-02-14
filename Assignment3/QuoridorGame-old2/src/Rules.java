import java.util.Scanner;

public class Rules{//Create Tic Tac Toe rules
    private int is_over;
    private int[] result = new int[2];
    private int board_size;
    public int winner_id;
    private int row;
    private int column;
    private int now_piece_row;
    private int now_piece_column;
    private int next;
    private int first_piece;
    private boolean still_possible_over = true;
    public boolean If_Move_Valid(int[][] pieces, int this_move_row, int this_move_column) throws Exception{//Check if the player's move is valid
        if(pieces[this_move_row-1][this_move_column-1] == 0) {
            return true;
        }
        else{
            return false;
        }
    }

    public boolean If_Choice_Valid(int[][] pieces, int this_move_row, int this_move_column) throws Exception{//Check if the player's move is valid
        if(pieces[this_move_row-1][this_move_column-1] == 0) {
            return true;
        }
        else{
            return false;
        }
    }

    public boolean If_Movement_Valid(int board_length, int board_width, int[][] pieces, int[][][][] walls, int[] piece_position, char movement) throws Exception {//Let the player give their choice of next step
        now_piece_row = piece_position[0];
        now_piece_column = piece_position[1];
        switch (movement) {
            case 'W':
                if(now_piece_row + 1 < board_length){ //If out of the board
                    if(walls[now_piece_row][now_piece_column][now_piece_row + 1][now_piece_column] == 0) {//If there is a wall
                        if (pieces[now_piece_row + 1][now_piece_column] != 0) { //If there is another piece
                            if (now_piece_row + 2 < board_length){//If after jumping out of the board
                                if(walls[now_piece_row + 1][now_piece_column][now_piece_row + 2][now_piece_column] == 0){//If after jumping there is another piece
                                    return true;
                                }
                                else{
                                    System.out.println("Can't move over the wall!");
                                    return false;
                                }
                            }
                            else {
                                System.out.println("Can't move out of the board!");
                                return false;
                            }
                        }
                        else {
                            return true;
                        }
                    }
                    else {
                        System.out.println("Can't move over the wall!");
                        return false;
                    }
                }
                else {
                    System.out.println("Can't move out of the board!");
                    return false;
                }
            case 'A':
                if(now_piece_column - 1 >= 0){
                    if(walls[now_piece_row][now_piece_column][now_piece_row][now_piece_column - 1] == 0) {//If there is a wall
                        if (pieces[now_piece_row][now_piece_column - 1] != 0) { //If there is another piece
                            if (now_piece_column - 2 >= 0){//If after jumping out of the board
                                if(walls[now_piece_row][now_piece_column - 1][now_piece_row][now_piece_column - 2] == 0){//If after jumping there is another piece
                                    return true;
                                }
                                else{
                                    System.out.println("Can't move over the wall!");
                                    return false;
                                }
                            }
                            else {
                                System.out.println("Can't move out of the board!");
                                return false;
                            }
                        }
                        else {
                            return true;
                        }
                    }
                    else {
                        System.out.println("Can't move over the wall!");
                        return false;
                    }
                }
                else {
                    System.out.println("Can't move out of the board!");
                    return false;
                }
            case 'S':
                if(now_piece_row - 1 >= 0){
                    if(walls[now_piece_row][now_piece_column][now_piece_row - 1][now_piece_column] == 0) {//If there is a wall
                        if (pieces[now_piece_row - 1][now_piece_column] != 0) { //If there is another piece
                            if (now_piece_row - 2 >= 0){//If after jumping out of the board
                                if(walls[now_piece_row - 1][now_piece_column][now_piece_row - 2][now_piece_column] == 0){//If after jumping there is another piece
                                    return true;
                                }
                                else{
                                    System.out.println("Can't move over the wall!");
                                    return false;
                                }
                            }
                            else {
                                System.out.println("Can't move out of the board!");
                                return false;
                            }
                        }
                        else {
                            return true;
                        }
                    }
                    else {
                        System.out.println("Can't move over the wall!");
                        return false;
                    }
                }
                else {
                    System.out.println("Can't move out of the board!");
                    return false;
                }
            case 'D':
                if(now_piece_column + 1 < board_width){
                    if(walls[now_piece_row][now_piece_column][now_piece_row][now_piece_column + 1] == 0) {//If there is a wall
                        if (pieces[now_piece_row][now_piece_column + 1] != 0) { //If there is another piece
                            if (now_piece_column + 2 < board_width){//If after jumping out of the board
                                if(walls[now_piece_row][now_piece_column + 1][now_piece_row][now_piece_column + 2] == 0){//If after jumping there is another piece
                                    return true;
                                }
                                else{
                                    System.out.println("Can't move over the wall!");
                                    return false;
                                }
                            }
                            else {
                                System.out.println("Can't move out of the board!");
                                return false;
                            }
                        }
                        else {
                            return true;
                        }
                    }
                    else {
                        System.out.println("Can't move over the wall!");
                        return false;
                    }
                }
                else {
                    System.out.println("Can't move out of the board!");
                    return false;
                }
            default:
                System.out.println("The movement is wrong!");
                return false;
        }
    }

    public boolean If_Placement_Valid(int board_length, int board_width, int[][][][] walls, int[] this_placement) throws Exception {//Let the player give their choice of next step
        //写一个判断这个墙能不能存在的函数，首先看一下这个墙是不是一个2X1的墙，也就是给的坐标是不是连在一起的（这里如果在前面的拿到墙的坐标的地方判断也行）
        //然后也要判断墙之间有没有重叠，就是我这已经有墙了不能再放一个墙，特别要注意(3,4)(3,5)的墙和(3,5)(3,4)的墙其实是一样的
        //返回一个true或者false就行，代表这个放墙的位置有没有问题



            for (int i = 0; i < this_placement.length; i += 3) {
                if (walls[this_placement[i]][this_placement[i + 1]][this_placement[i + 2]][this_placement[i + 3]] == 1) {
                    return false;
                }
            }

            // Ensure the two blocks are adjacent in the correct orientation
        //3,4;3,5 // 4,4;4,5
            if (((Math.abs(this_placement[0] - this_placement[2]) == 1 && this_placement[1] == this_placement[3]) ||
                    (Math.abs(this_placement[1] - this_placement[3]) == 1 && this_placement[0] == this_placement[2])) &&
                    ((Math.abs(this_placement[4] - this_placement[6]) == 1 && this_placement[5] == this_placement[7]) ||
                    (Math.abs(this_placement[5] - this_placement[7]) == 1 && this_placement[4] == this_placement[6])))  {
                。没判断这两块板子是不是一个2*1的板子
                return true;
            }

            return false;
        }

        public int[] TicTacToeIf_Over(int[][] pieces, int board_length, int board_width) throws Exception{//Check if the Tic Tac Toe game is over
        // use the min(length, width) to check if the game is over.
        if(board_length <= board_width){
            board_size = board_length;
        }
        else{
            board_size = board_width;
        }

        //Check the horizontal arrangement of pieces.
        column = 0;
        for (row = 0; row < board_size; row++) {
            still_possible_over = true;
            first_piece = pieces[row][column];
            if(first_piece == 0 || first_piece == 3){
                still_possible_over = false;
            }
            next = 1;
            while(still_possible_over && next < board_size){
                if (pieces[row][column + next] != first_piece) {
                    still_possible_over = false;
                }
                next++;
            }
            if(still_possible_over){
                this.is_over = 1;
                this.result[0] = this.is_over;
                this.result[1] = first_piece;
                return result;
            }
        }

        //Check the vertical arrangement of pieces
        row = 0;
        for (column = 0; column < board_size; column++) {
            still_possible_over = true;
            first_piece = pieces[row][column];
            if(first_piece == 0 || first_piece == 3){
                still_possible_over = false;
            }
            next = 1;
            while(still_possible_over && next < board_size){
                if (pieces[row + next][column] != first_piece) {
                    still_possible_over = false;
                }
                next++;
            }
            if(still_possible_over){
                this.is_over = 1;
                this.result[0] = this.is_over;
                this.result[1] = first_piece;
                return result;
            }
        }

        //Check the diagonal arrangement of pieces
        row = 0;
        column = 0;
        still_possible_over = true;
        first_piece = pieces[row][column];
        if(first_piece == 0 || first_piece == 3){
            still_possible_over = false;
        }
        next = 1;
        while(still_possible_over && next < board_size){
            if (pieces[row + next][column + next] != first_piece) {
                still_possible_over = false;
            }
            next++;
        }
        if(still_possible_over){
            this.is_over = 1;
            this.result[0] = this.is_over;
            this.result[1] = first_piece;
            return result;
        }

        //Also check the diagonal arrangement of pieces
        row = 0;
        column = board_size - 1;
        still_possible_over = true;
        first_piece = pieces[row][column];
        if(first_piece == 0 || first_piece == 3){
            still_possible_over = false;
        }
        next = 1;
        while(still_possible_over && next < board_size){
            if (pieces[row + next][column - next] != first_piece) {
                still_possible_over = false;
            }
            next++;
        }
        if(still_possible_over){
            this.is_over = 1;
            this.result[0] = this.is_over;
            this.result[1] = first_piece;
            return result;
        }

        //Check if the chessboard is full
        this.is_over = 1;
        for (row = 0; row < board_size; row++) {
            for (column = 0; column < board_size; column++) {
                //System.out.println("pieces["+row+"]["+column+"] is "+pieces[row][column]);
                if (pieces[row][column] == 0) {
                    this.is_over = 0;
                    this.result[0] = this.is_over;
                    return this.result;
                }
            }
        }
        this.result[0] = this.is_over;
        this.result[1] = 0;
        return this.result;
    }

    public int[] OrderandChaosIf_Over(int[][] pieces) throws Exception{//Check if the Tic Tac Toe game is over
        //Check the horizontal arrangement of pieces
        for (row = 0; row < 6; row++){
            for(column = 0; column < 2; column++){
                if (pieces[row][column] == 1 && pieces[row][column+1] == 1 && pieces[row][column+2] == 1 && pieces[row][column+3] == 1 && pieces[row][column+4] == 1){
                    this.is_over = 1;
                    this.result[0] = this.is_over;
                    this.result[1] = 1;
                    return result;
                }
                if (pieces[row][column] == 2 && pieces[row][column+1] == 2 && pieces[row][column+2] == 2 && pieces[row][column+3] == 2 && pieces[row][column+4] == 2){
                    this.is_over = 1;
                    this.result[0] = this.is_over;
                    this.result[1] = 1;
                    return result;
                }
            }
        }

        //Check the vertical arrangement of pieces
        for (column = 0; column < 6; column++){
            for(row = 0; row < 2; row++){
                if (pieces[row][column] == 1 && pieces[row+1][column] == 1 && pieces[row+2][column] == 1 && pieces[row+3][column] == 1 && pieces[row+4][column] == 1){
                    this.is_over = 1;
                    this.result[0] = this.is_over;
                    this.result[1] = 1;
                    return result;
                }
                if (pieces[row][column] == 2 && pieces[row+1][column] == 2 && pieces[row+2][column] == 2 && pieces[row+3][column] == 2 && pieces[row+4][column] == 2){
                    this.is_over = 1;
                    this.result[0] = this.is_over;
                    this.result[1] = 1;
                    return result;
                }
            }
        }

        //Check the diagonal arrangement of pieces
        for (row = 0; row < 2; row++){
            for(column = 0; column < 2; column++){
                if (pieces[row][column] == 1 && pieces[row+1][column+1] == 1 && pieces[row+2][column+2] == 1 && pieces[row+3][column+3] == 1 && pieces[row+4][column+4] == 1){
                    this.is_over = 1;
                    this.result[0] = this.is_over;
                    this.result[1] = 1;
                    return result;
                }
                if (pieces[row][column] == 2 && pieces[row+1][column+1] == 2 && pieces[row+2][column+2] == 2 && pieces[row+3][column+3] == 2 && pieces[row+4][column+4] == 2){
                    this.is_over = 1;
                    this.result[0] = this.is_over;
                    this.result[1] = 1;
                    return result;
                }
            }
        }

        //Also check the diagonal arrangement of pieces
        for (row = 0; row < 2; row++){
            for(column = 5; column > 3; column--){
                if (pieces[row][column] == 1 && pieces[row+1][column-1] == 1 && pieces[row+2][column-2] == 1 && pieces[row+3][column-3] == 1 && pieces[row+4][column-4] == 1){
                    this.is_over = 1;
                    this.result[0] = this.is_over;
                    this.result[1] = 1;
                    return result;
                }
                if (pieces[row][column] == 2 && pieces[row+1][column-1] == 2 && pieces[row+2][column-2] == 2 && pieces[row+3][column-3] == 2 && pieces[row+4][column-4] == 2){
                    this.is_over = 1;
                    this.result[0] = this.is_over;
                    this.result[1] = 1;
                    return result;
                }
            }
        }

        //Check if the chessboard is full
        this.is_over = 1;
        for (row = 0; row < 6; row++) {
            for (column = 0; column < 6; column++) {
                //System.out.println("pieces["+row+"]["+column+"] is "+pieces[row][column]);
                if (pieces[row][column] == 0) {
                    this.is_over = 0;
                    this.result[0] = this.is_over;
                    return this.result;
                }
            }
        }
        this.result[0] = this.is_over;
        if(this.is_over == 1){
            this.result[1] = 2;
        }
        return this.result;
    }

    public int[] QuoridorIf_Over(int[] piece_position, int board_length, int now_round) throws Exception {//Check if the Tic Tac Toe game is over
        //判断输赢。利用现在的棋子的位置，和棋盘的长度，这个长度就是行的数量，这两个数据应该足够判断输赢了，不够再改一下
        //返回一个result，他是一个int数组，result[0]存储游戏是否结束（0或者1），result[1]存储如果胜利了，游戏的胜者是谁（0或者1或者2），0代表平局
        。再仔细看一下第二句话，给出了要求返回的result的要求的，不是返回0，1，2就行了的
        if (piece_position[0] == board_length - 1) {
            result[0] = 1;
            if (now_round % 2 == 0) {
                result[1] = 2;
            }
        }

            if (pieces[row] == 0) {
                return 2;
            }

            return 0;
        }


        return this.result;
    }
}