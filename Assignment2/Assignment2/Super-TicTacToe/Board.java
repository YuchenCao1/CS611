public class Board implements Print_Board{ //Create the board
    public int board_length;
    public int board_width;
    private int row;
    private int column;
    private int main_row;
    private int main_column;
    private int row_divider;
    private int column_divider;
    private int sub_row;
    private int sub_column;
    public Board(int board_length, int board_width){
        this.board_length = board_length;
        this.board_width = board_width;
    }
    public void Reset_Board_Size(int board_length, int board_width) throws Exception{//Reset size of board
        this.board_length = board_length;
        this.board_width = board_width;
    }

    public void PrintBoard(int[][] pieces) throws Exception{//Print the board
        for (column = 0; column < board_width-1; column++) {
            if (pieces[0][column] == 0){
                System.out.printf("\t|");
            }
            else if(pieces[0][column] == 1){
                System.out.printf("O");
                System.out.printf("\t|");
            }
            else if(pieces[0][column] == 2){
                System.out.printf("X");
                System.out.printf("\t|");
            }
            else if(pieces[0][column] == 3){
                System.out.printf("D");
                System.out.printf("\t|");
            }
        }
        if (pieces[0][board_width-1] == 0){
            System.out.printf("\n");
        }
        else if(pieces[0][board_width-1] == 1){
            System.out.printf("O\n");
        }
        else if(pieces[0][board_width-1] == 2){
            System.out.printf("X\n");
        }
        else if(pieces[0][board_width-1] == 3){
            System.out.printf("D\n");
        }
        for (row = 1; row < board_length; row++){
            for (column = 0; column < board_width; column++) {
                System.out.printf("--------");
            }
            System.out.printf("\n");
            for (column = 0; column < board_width-1; column++) {
                if (pieces[row][column] == 0){
                    System.out.printf("\t|");
                }
                else if(pieces[row][column] == 1){
                    System.out.printf("O");
                    System.out.printf("\t|");
                }
                else if(pieces[row][column] == 2){
                    System.out.printf("X");
                    System.out.printf("\t|");
                }
                else if(pieces[row][column] == 3){
                    System.out.printf("D");
                    System.out.printf("\t|");
                }
            }
            if (pieces[row][board_width-1] == 0){
                System.out.printf("\n");
            }
            else if(pieces[row][board_width-1] == 1){
                System.out.printf("O\n");
            }
            else if(pieces[row][board_width-1] == 2){
                System.out.printf("X\n");
            }
            else if(pieces[row][board_width-1] == 3){
                System.out.printf("D\n");
            }
        }
    }
    public void Print_SuperTicTacToe_Board(int main_board_length, int main_board_width, int sub_board_length, int sub_board_width, Piece[] sub_pieces) throws Exception{//Print the board
        for (sub_column = 0; sub_column < sub_board_width-1; sub_column++) {
            if (sub_pieces[0].pieces[0][sub_column] == 0){
                System.out.printf("\t|");
            }
            else if(sub_pieces[0].pieces[0][sub_column] == 1){
                System.out.printf("O");
                System.out.printf("\t|");
            }
            else if(sub_pieces[0].pieces[0][sub_column] == 2){
                System.out.printf("X");
                System.out.printf("\t|");
            }
        }
        if(sub_pieces[0].pieces[0][sub_board_width-1] == 1){
            System.out.printf("O");
        }
        else if(sub_pieces[0].pieces[0][sub_board_width-1] == 2){
            System.out.printf("X");
        }

        for(main_column = 1; main_column < main_board_width; main_column++) {
            System.out.printf("\t||\t");
            for (sub_column = 0; sub_column < sub_board_width - 1; sub_column++) {
                if (sub_pieces[main_column].pieces[0][sub_column] == 0) {
                    System.out.printf("\t|");
                } else if (sub_pieces[main_column].pieces[0][sub_column] == 1) {
                    System.out.printf("O");
                    System.out.printf("\t|");
                } else if (sub_pieces[main_column].pieces[0][sub_column] == 2) {
                    System.out.printf("X");
                    System.out.printf("\t|");
                }
            }
            if (sub_pieces[main_column].pieces[0][sub_board_width - 1] == 1) {
                System.out.printf("O");
            } else if (sub_pieces[main_column].pieces[0][sub_board_width - 1] == 2) {
                System.out.printf("X");
            }
        }


        for (sub_row = 1; sub_row < sub_board_length; sub_row++){
            System.out.printf("\n");
            for (sub_column = 0; sub_column < sub_board_width; sub_column++) {
                System.out.printf("--------");
            }
            for(main_column = 1;main_column<main_board_width;main_column++) {
                System.out.printf("||\t");
                for (sub_column = 0; sub_column < sub_board_width; sub_column++) {
                    System.out.printf("--------");
                }
            }
            System.out.printf("\n");

            for (sub_column = 0; sub_column < sub_board_width - 1; sub_column++) {
                if (sub_pieces[0].pieces[sub_row][sub_column] == 0) {
                    System.out.printf("\t|");
                } else if (sub_pieces[0].pieces[sub_row][sub_column] == 1) {
                    System.out.printf("O");
                    System.out.printf("\t|");
                } else if (sub_pieces[0].pieces[sub_row][sub_column] == 2) {
                    System.out.printf("X");
                    System.out.printf("\t|");
                }
            }
            if (sub_pieces[0].pieces[sub_row][sub_board_width - 1] == 1) {
                System.out.printf("O");
            } else if (sub_pieces[0].pieces[sub_row][sub_board_width - 1] == 2) {
                System.out.printf("X");
            }

            for(main_column = 1; main_column < main_board_width; main_column++) {
                System.out.printf("\t||\t");
                for (sub_column = 0; sub_column < sub_board_width - 1; sub_column++) {
                    if (sub_pieces[main_column].pieces[sub_row][sub_column] == 0) {
                        System.out.printf("\t|");
                    } else if (sub_pieces[main_column].pieces[sub_row][sub_column] == 1) {
                        System.out.printf("O");
                        System.out.printf("\t|");
                    } else if (sub_pieces[main_column].pieces[sub_row][sub_column] == 2) {
                        System.out.printf("X");
                        System.out.printf("\t|");
                    }
                }
                if (sub_pieces[main_column].pieces[sub_row][sub_board_width - 1] == 1) {
                    System.out.printf("O");
                } else if (sub_pieces[main_column].pieces[sub_row][sub_board_width - 1] == 2) {
                    System.out.printf("X");
                }
            }
        }


        for(main_row = 1; main_row < main_board_length; main_row++){
            System.out.print("\n");
            for(row_divider = 0; row_divider < main_board_width; row_divider++) {
                for (column_divider = 0; column_divider < sub_board_width; column_divider++) {
                    System.out.printf("==========");
                }
            }
            System.out.print("\n");
            for (sub_column = 0; sub_column < sub_board_width-1; sub_column++) {
                if (sub_pieces[main_row * 3].pieces[0][sub_column] == 0){
                    System.out.printf("\t|");
                }
                else if(sub_pieces[main_row * main_board_width].pieces[0][sub_column] == 1){
                    System.out.printf("O");
                    System.out.printf("\t|");
                }
                else if(sub_pieces[main_row * main_board_width].pieces[0][sub_column] == 2){
                    System.out.printf("X");
                    System.out.printf("\t|");
                }
            }
            if(sub_pieces[main_row * main_board_width].pieces[0][sub_board_width-1] == 1){
                System.out.printf("O");
            }
            else if(sub_pieces[main_row * main_board_width].pieces[0][sub_board_width-1] == 2){
                System.out.printf("X");
            }

            for(main_column = 1; main_column < main_board_width; main_column++) {
                System.out.printf("\t||\t");
                for (sub_column = 0; sub_column < sub_board_width - 1; sub_column++) {
                    if (sub_pieces[main_row * main_board_width + main_column].pieces[0][sub_column] == 0) {
                        System.out.printf("\t|");
                    } else if (sub_pieces[main_row * main_board_width + main_column].pieces[0][sub_column] == 1) {
                        System.out.printf("O");
                        System.out.printf("\t|");
                    } else if (sub_pieces[main_row * main_board_width + main_column].pieces[0][sub_column] == 2) {
                        System.out.printf("X");
                        System.out.printf("\t|");
                    }
                }
                if (sub_pieces[main_row * main_board_width + main_column].pieces[0][sub_board_width - 1] == 1) {
                    System.out.printf("O");
                } else if (sub_pieces[main_row * main_board_width + main_column].pieces[0][sub_board_width - 1] == 2) {
                    System.out.printf("X");
                }
            }


            for (sub_row = 1; sub_row < sub_board_length; sub_row++) {
                System.out.printf("\n");
                for (sub_column = 0; sub_column < sub_board_width; sub_column++) {
                    System.out.printf("--------");
                }
                for (main_column = 1; main_column < main_board_width; main_column++) {
                    System.out.printf("||\t");
                    for (sub_column = 0; sub_column < sub_board_width; sub_column++) {
                        System.out.printf("--------");
                    }
                }
                System.out.printf("\n");

                for (sub_column = 0; sub_column < sub_board_width - 1; sub_column++) {
                    if (sub_pieces[main_row * main_board_width].pieces[sub_row][sub_column] == 0) {
                        System.out.printf("\t|");
                    } else if (sub_pieces[main_row * main_board_width].pieces[sub_row][sub_column] == 1) {
                        System.out.printf("O");
                        System.out.printf("\t|");
                    } else if (sub_pieces[main_row * main_board_width].pieces[sub_row][sub_column] == 2) {
                        System.out.printf("X");
                        System.out.printf("\t|");
                    }
                }
                if (sub_pieces[main_row * main_board_width].pieces[sub_row][sub_board_width - 1] == 1) {
                    System.out.printf("O");
                } else if (sub_pieces[main_row * main_board_width].pieces[sub_row][sub_board_width - 1] == 2) {
                    System.out.printf("X");
                }

                for (main_column = 1; main_column < main_board_width; main_column++) {
                    System.out.printf("\t||\t");
                    for (sub_column = 0; sub_column < sub_board_width - 1; sub_column++) {
                        if (sub_pieces[main_row * main_board_width + main_column].pieces[sub_row][sub_column] == 0) {
                            System.out.printf("\t|");
                        }
                        else if (sub_pieces[main_row * main_board_width + main_column].pieces[sub_row][sub_column] == 1) {
                            System.out.printf("O");
                            System.out.printf("\t|");
                        }
                        else if (sub_pieces[main_row * main_board_width + main_column].pieces[sub_row][sub_column] == 2) {
                            System.out.printf("X");
                            System.out.printf("\t|");
                        }
                    }
                    if (sub_pieces[main_row * main_board_width + main_column].pieces[sub_row][sub_board_width - 1] == 1) {
                        System.out.printf("O");
                    }
                    else if (sub_pieces[main_row * main_board_width + main_column].pieces[sub_row][sub_board_width - 1] == 2) {
                        System.out.printf("X");
                    }
                }
            }
        }
    }
}