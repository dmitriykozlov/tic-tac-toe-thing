package tictactoe;

import java.util.Random;

public class MediumPlayer extends Player {
    Random random = new Random();

    public MediumPlayer(Game game, char character) {
        super(Player.Level.MEDIUM, game, character);
    }

    private final int[][][] strikes = {
            {{0, 0}, {0, 1}}, {{0, 1}, {0, 2}},
            {{1, 0}, {1, 1}}, {{1, 1}, {1, 2}},
            {{2, 0}, {2, 1}}, {{2, 1}, {2, 2}},
            {{0, 0}, {1, 0}}, {{1, 0}, {2, 0}},
            {{0, 1}, {1, 1}}, {{1, 1}, {2, 1}},
            {{0, 2}, {1, 2}}, {{1, 2}, {2, 2}},
            {{0, 0}, {1, 1}}, {{1, 1}, {2, 2}},
            {{0, 2}, {1, 1}}, {{1, 1}, {2, 0}},
    };

    private final int[][] components = {
            {0, 2}, {0, 0},
            {1, 2}, {1, 0},
            {2, 2}, {2, 0},
            {2, 0}, {0, 0},
            {2, 1}, {0, 1},
            {2, 2}, {0, 2},
            {2, 2}, {0, 0},
            {2, 0}, {0, 2},
    };

    public int[] checkTwoInTheRow(char label) {
        for (var i = 0; i < strikes.length; i++) {
            var strike = strikes[i];
            if (game.board[strike[0][0]][strike[0][1]] == label &&
                    game.board[strike[1][0]][strike[1][1]] == label &&
                    game.board[components[i][0]][components[i][1]] == Game.EMPTY)
                return components[i];
        }
        return null;
    }

    public void makeRandomMove() {
        int row = random.nextInt(3), col = random.nextInt(3);
        while (!game.canMakeMove(row, col, character)) {
            row = random.nextInt(3);
            col = random.nextInt(3);
        }
        game.makeMove(row, col, character);
    }

    @Override
    public void makeMove() {
        System.out.println("Making move level \"medium\"");
        var component = checkTwoInTheRow(this.character);
        if (component != null) {
            game.makeMove(component, character);
            return;
        }
        component = checkTwoInTheRow(this.opponentsCharacter());
        if (component != null) {
            game.makeMove(component, character);
            return;
        }
        makeRandomMove();
    }
}
