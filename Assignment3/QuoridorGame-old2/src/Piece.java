public class Piece{//Create pieces
    public int[][] pieces;
    private int row;
    private int column;
    private int now_piece_row;
    private int now_piece_column;
    private int[] piece_position = new int[2];
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

    public void Reset_QuoridorGame_Pieces(int board_length, int board_width) throws Exception{//Reset pieces
        for (row=0; row < board_length; row++){
            for (column=0; column < board_width; column++){
                pieces[row][column] = 0;
            }
        }
        pieces[0][board_width/2] = 2;
        pieces[board_length - 1][board_width/2] = 1;
    }


    public int[] Get_Piece_Position(int board_length, int board_width, int[][] pieces ,int now_round) throws Exception {//Let the player give their choice of next step
        if (now_round % 2 == 1) {
            for (row = 0; row < board_length; row++) {
                for (column = 0; column < board_width; column++) {
                    if (pieces[row][column] == 1) {
                        now_piece_row = row;
                        now_piece_column = column;
                        row = board_length;
                        column = board_width;
                    }
                }
            }
        }
        else {
            for (row = 0; row < board_length; row++) {
                for (column = 0; column < board_width; column++) {
                    if (pieces[row][column] == 2) {
                        now_piece_row = row;
                        now_piece_column = column;
                        row = board_length;
                        column = board_width;
                    }
                }
            }
        }
        piece_position[0] = now_piece_row;
        piece_position[1] = now_piece_column;
        return piece_position;
    }

    public int[][] Update_Pieces(int row, int column, int piece_type) throws Exception{//Update piece(to record each move)
        pieces[row][column] = piece_type;
        return this.pieces;
    }

    public int[][] Move_Pieces(int[] piece_position, char movement) throws Exception{//Move a piece to a new position.
        //把现在位置在(piece_position[0],piece_position[1])的棋子根据movement的类型（W/A/S/D）移动一步，如果对面的棋子刚好挡住就跳过去,返回现在的棋盘
        //不需要考虑能不能走这个问题，因为是判断了这一步是合理的才会到这个函数
        int x = piece_position[1];
        int y = now_piece_row;

        switch (movement) {
            case 'W':
                if ( this.pieces[x-1][y] != 0) {
                    。这里所有的判断都有点问题，能不能走的问题前面的函数已经判断过了，只要到这一个函数了就一定是可以走的了，所以不需要判断x - 1 >= 0这种
                    。这个this.pieces[x-2][y] == 0也没什么必要其实，毕竟对面只有一个棋子，this.pieces[x-1][y] != 0就一定说明this.pieces[x-2][y] == 0了
                    x -= 2;
                } else {
                    x -= 1;
                }
                break;
            case 'A':
                if (y - 1 >= 0 && this.pieces[x][y-1] != 0 && y - 2 >= 0 && this.pieces[x][y-2] == 0) {
                    y -= 2;
                } else {
                    y -= 1;
                }
                break;
            case 'S':
                if (x + 1 < 8 && this.pieces[x+1][y] != 0 && x + 2 < 8 && this.pieces[x+2][y] == 0) {
                    x += 2;
                } else {
                    x += 1;
                }
                break;
            case 'D':
                if (y + 1 < 8 && this.pieces[x][y+1] != 0 && y + 2 < 8 && this.pieces[x][y+2] == 0) {
                    y += 2;
                } else {
                    y += 1;
                }
                break;
            default:
                System.out.println("Invalid movement character");
                break;
        }

        this.pieces[x][y] = this.pieces[now_piece_column][now_piece_row];
        this.pieces[now_piece_column][now_piece_row] = 0;

        return this.pieces;
    }
}
