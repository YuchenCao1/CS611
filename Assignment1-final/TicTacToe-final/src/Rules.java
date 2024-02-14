public class Rules{//Create Tic Tac Toe rules
    private int is_over;
    private int[] result = new int[2];
    public int winner_id;
    private int row;
    private int column;
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
    public int[] TicTacToeIf_Over(int[][] pieces, int board_size) throws Exception{//Check if the Tic Tac Toe game is over

        //Check the horizontal arrangement of pieces
        column = 0;
        for (row = 0; row < board_size; row++) {
            still_possible_over = true;
            first_piece = pieces[row][column];
            if(first_piece == 0){
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
            if(first_piece == 0){
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
        if(first_piece == 0){
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
        if(first_piece == 0){
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
}