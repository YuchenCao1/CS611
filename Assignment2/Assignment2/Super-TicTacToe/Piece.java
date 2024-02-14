public class Piece{//Create pieces
    public int[][] pieces;
    private int row;
    private int column;
    public Piece(int board_length, int board_width){
        pieces = new int[board_length][board_width];
        for (row=0; row<board_length; row++){
            for (column=0; column<board_width; column++){
                pieces[row][column] = 0;
            }
        }
    }

    public void Reset_Pieces(int board_length, int board_width) throws Exception{//Reset pieces
        for (row=0; row < board_length; row++){
            for (column=0; column < board_width; column++){
                pieces[row][column] = 0;
            }
        }
    }

    public int[][] Update_Pieces(int row, int column, int piece_type) throws Exception{//Update piece(to record each move)
        pieces[row][column] = piece_type;
        return this.pieces;
    }
}
