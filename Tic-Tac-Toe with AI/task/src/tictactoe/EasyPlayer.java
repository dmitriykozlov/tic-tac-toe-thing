package tictactoe;

import java.util.Random;

public class EasyPlayer extends Player {
    Random random = new Random();

    public EasyPlayer(Game game, char character) {
        super(Level.EASY, game, character);
    }

    @Override
    public void makeMove() {
        System.out.println("Making move level \"easy\"");
        int row = random.nextInt(3), col = random.nextInt(3);
        while (!game.canMakeMove(row, col, character)) {
            row = random.nextInt(3);
            col = random.nextInt(3);
        }
        game.makeMove(row, col, character);
    }
}
