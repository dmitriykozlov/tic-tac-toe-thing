package tictactoe;

public class HardPlayer extends Player {

    public HardPlayer(Game game, char character) {
        super(Level.HARD, game, character);
    }

    @Override
    public void makeMove() {
        System.out.println("Making move level \"hard\"");
        var copyBoard = new Board(game);
        var bestMove = findBestMove(copyBoard);
        game.makeMove(bestMove, character);
    }

    static private int minmax(Board board, int depth, char turn) {
        var state = board.checkBoard();
        if (state.isTerminal()) {
            return state.minMaxValue();
        }
        int bestScore;
        if (turn == Game.X) {
            bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.canMakeMove(i, j, turn)) {
                        board.makeMove(i, j, turn);
                        var score = minmax(board, depth + 1, Game.O);
                        board.undoMove(i, j, turn);
                        bestScore = Math.max(bestScore, score);
                    }
                }
            }
        } else {
            bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.canMakeMove(i, j, turn)) {
                        board.makeMove(i, j, turn);
                        var score = minmax(board, depth + 1, Game.X);
                        board.undoMove(i, j, turn);
                        bestScore = Math.min(bestScore, score);
                    }
                }
            }
        }
        return bestScore;
    }

    private int[] findBestMove(Board board) {
        var bestScore = character == Game.X ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        var bestMove = new int[]{-1, -1};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.canMakeMove(i, j, character)) {
                    board.makeMove(i, j, character);
                    var score = minmax(board, 0, opponentsCharacter());
                    if (character == Game.X) {
                        if (bestScore < score) {
                            bestScore = score;
                            bestMove = new int[]{i, j};
                        }
                    } else {
                        if (bestScore > score) {
                            bestScore = score;
                            bestMove = new int[]{i, j};
                        }
                    }
                    board.undoMove(i, j, character);
                }
            }
        }

        return bestMove;
    }
}
