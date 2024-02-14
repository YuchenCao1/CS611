public class Piece{//Create pieces
    public int[][] pieces;
    private int row;
    private int column;
    public Piece(int boardsize){
        pieces = new int[boardsize][boardsize];
        for (row=0; row < boardsize; row++){
            for (column=0; column < boardsize; column++){
                pieces[row][column] = 0;
            }
        }
    }

    public void Reset_Pieces(int boardsize) throws Exception{//Reset pieces
        for (row=0; row < boardsize; row++){
            for (column=0; column < boardsize; column++){
                pieces[row][column] = 0;
            }
        }
    }

    public int[][] Update_Pieces(int row, int column, int piece_type) throws Exception{//Update piece(to record each move)
        pieces[row][column] = piece_type;
        return this.pieces;
    }
}
