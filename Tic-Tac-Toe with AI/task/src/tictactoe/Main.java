package tictactoe;


import java.util.*;

class BadParameters extends Exception {
    public BadParameters() {
        super("Bad parameters!");
    }
}

public class Main {

    private static final Set<String> allowedLevels = new HashSet<>(Arrays.asList("easy", "user", "medium", "hard"));
    private static final String boardRegexp = "[OX_]{9}";

    public static void handleStart(String[] command) throws BadParameters {
        if (command.length < 3 || !allowedLevels.contains(command[1])
                || !allowedLevels.contains(command[2])
                || (command.length == 4 && !command[3].matches(boardRegexp)))
            throw new BadParameters();
        var game = command.length == 4 ? new Game(command[3]) : new Game();
        var playerX = Player.createPlayer(command[1], game, Game.X);
        var playerO = Player.createPlayer(command[2], game, Game.O);
        game.addPlayers(playerX, playerO);
        game.startGameLoop();
    }

    public static void handleExit() throws BadParameters {
        System.exit(0);
    }

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Input command: ");
                var command = scanner.nextLine().split(" ");
                switch (command[0]) {
                    case "start":
                        handleStart(command);
                        break;
                    case "exit":
                        handleExit();
                    default:
                        throw new BadParameters();
                }
            } catch (BadParameters e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
