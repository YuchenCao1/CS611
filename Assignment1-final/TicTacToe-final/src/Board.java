public class Board{ //Create the board
    public int board_size;
    private int row;
    private int column;
    public Board(int board_size){
        this.board_size = board_size;
    }
    public void Reset_Board_Size(int board_size) throws Exception{//Reset size of board
        this.board_size = board_size;
    }

    public void PrintBoard(int[][] pieces) throws Exception{//Print the board
        for (column = 0; column < board_size-1; column++) {
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
        }
        if (pieces[0][board_size-1] == 0){
            System.out.printf("\n");
        }
        else if(pieces[0][board_size-1] == 1){
            System.out.printf("O\n");
        }
        else if(pieces[0][board_size-1] == 2){
            System.out.printf("X\n");
        }
        for (row = 1; row < board_size; row++){
            for (column = 0; column < board_size; column++) {
                System.out.printf("--------");
            }
            System.out.printf("\n");
            for (column = 0; column < board_size-1; column++) {
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
            }
            if (pieces[row][board_size-1] == 0){
                System.out.printf("\n");
            }
            else if(pieces[row][board_size-1] == 1){
                System.out.printf("O\n");
            }
            else if(pieces[row][board_size-1] == 2){
                System.out.printf("X\n");
            }
        }

    }
}