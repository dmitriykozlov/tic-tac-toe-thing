package tictactoe;

enum State {
    NOT_FINISHED("Game not finished"), DRAW("Draw"), X_WINS("X wins"), O_WINS("O wins");

    private final String output;

    State(String output) {
        this.output = output;
    }

    @Override
    public String toString() {
        return output;
    }

    public boolean isTerminal() {
        return this != NOT_FINISHED;
    }

    public int minMaxValue() {
        if (this == X_WINS) return 10;
        if (this == O_WINS) return -10;
        return 0;
    }
}

public class Game extends Board {

    State state;
    Player playerX, playerO;


    public Game() {
        state = State.NOT_FINISHED;
    }

    public Game(String board) {
        this.board = new char[3][3];
        for (int i = 0; i < 9; i++) {
            if (board.charAt(i) == '_') {
                this.board[i / 3][i % 3] = EMPTY;
            } else {
                var c = board.charAt(i);
                this.board[i / 3][i % 3] = c;
                if (c == X) x_moves++;
                else o_moves++;
            }
        }
        checkGameState();
    }

    public void addPlayers(Player playerX, Player playerO) {
        this.playerX = playerX;
        this.playerO = playerO;
    }

    private void checkGameState() {
        state = checkBoard();
    }

    public void startGameLoop() {
        printBoard();
        while (state == State.NOT_FINISHED) {
            if (o_moves >= x_moves) {
                playerX.makeMove();
            } else {
                playerO.makeMove();
            }
            printBoard();
            checkGameState();
        }
        System.out.println(state);
    }

}
