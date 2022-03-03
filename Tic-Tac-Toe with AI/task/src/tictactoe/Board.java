package tictactoe;

public class Board {
    final static char EMPTY = ' ';
    final static char X = 'X';
    final static char O = 'O';
    private static final int[][][] strikes = {{{0, 0}, {0, 1}, {0, 2}}, {{1, 0}, {1, 1}, {1, 2}}, {{2, 0}, {2, 1}, {2, 2}}, {{0, 0}, {1, 0}, {2, 0}}, {{0, 1}, {1, 1}, {2, 1}}, {{0, 2}, {1, 2}, {2, 2}}, {{0, 0}, {1, 1}, {2, 2}}, {{0, 2}, {1, 1}, {2, 0}},};

    char[][] board;
    protected int x_moves = 0;
    protected int o_moves = 0;

    public Board() {
        this.board = new char[][]{{EMPTY, EMPTY, EMPTY}, {EMPTY, EMPTY, EMPTY}, {EMPTY, EMPTY, EMPTY}};
    }

    public Board(Board board) {
        this.board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.board[i][j] = board.board[i][j];
            }
        }
        this.x_moves = board.x_moves;
        this.o_moves = board.o_moves;
    }

    public State checkBoard() {
        for (var strike : strikes) {
            if (board[strike[0][0]][strike[0][1]] == board[strike[1][0]][strike[1][1]] &&
                    board[strike[1][0]][strike[1][1]] == board[strike[2][0]][strike[2][1]]) {
                if (board[strike[0][0]][strike[0][1]] == X) {
                    return State.X_WINS;
                } else if (board[strike[0][0]][strike[0][1]] == O) {
                    return State.O_WINS;
                }
            }
        }
        if (x_moves + o_moves == 9) {
            return State.DRAW;
        }
        return State.NOT_FINISHED;
    }

    public boolean isEmpty(int row, int col) {
        return board[row][col] == EMPTY;
    }

    public boolean isEmpty(int[] coordinates) {
        return board[coordinates[0]][coordinates[1]] == EMPTY;
    }

    public boolean canMakeMove(int row, int col, char player) {
        boolean rightTurn = x_moves == o_moves ? player == X : player == O;
        return isEmpty(row, col) && rightTurn;
    }

    public boolean canMakeMove(int[] coordinates, char player) {
        boolean rightTurn = x_moves == o_moves ? player == X : player == O;
        return isEmpty(coordinates) && rightTurn;
    }

    public void makeMove(int row, int col, char player) {
        if (canMakeMove(row, col, player)) {
            board[row][col] = player;
            if (player == X) x_moves++;
            else o_moves++;
        }
    }

    public void makeMove(int[] coordinates, char player) {
        if (canMakeMove(coordinates, player)) {
            board[coordinates[0]][coordinates[1]] = player;
            if (player == X) x_moves++;
            else o_moves++;
        }
    }

    public void undoMove(int row, int col, char player) {
        // TODO: add board validation before undoing
        if (board[row][col] == player) {
            board[row][col] = EMPTY;
            if (player == X) x_moves--;
            else o_moves--;
        }
    }

    public void printBoard() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.println("| " + board[i][0] + " " + board[i][1] + " " + board[i][2] + " |");
        }
        System.out.println("---------");
    }

}
