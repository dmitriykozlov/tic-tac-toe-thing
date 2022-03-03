package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class UserPlayer extends Player {
    Scanner scanner = new Scanner(System.in);

    public UserPlayer(Game game, char character) {
        super(Level.USER, game, character);
    }

    @Override
    public void makeMove() {
        System.out.print("Enter the coordinates: ");
        var input = scanner.nextLine();
        if (!input.matches("\\d \\d")) {
            System.out.println("You should enter numbers!");
            makeMove();
            return;
        }
        var coordinates = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).map(n -> n - 1).toArray();
        if (Arrays.stream(coordinates).anyMatch(num -> num < 0 || num > 2)) {
            System.out.println("Coordinates should be from 1 to 3!");
            makeMove();
            return;
        }
        if (!game.canMakeMove(coordinates, character)) {
            System.out.println("This cell is occupied! Choose another one!");
            makeMove();
            return;
        }
        game.makeMove(coordinates, character);
    }
}
