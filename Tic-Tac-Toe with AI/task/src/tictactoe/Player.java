package tictactoe;

public abstract class Player {
    enum Level {
        EASY("easy"),
        MEDIUM("medium"),
        HARD("hard"),
        USER("user");

        private final String output;

        Level(String output) {
            this.output = output;
        }

        @Override
        public String toString() {
            return output;
        }
    }

    protected Level level;
    protected Game game;
    protected char character;


    public Player(Level level, Game game, char character) {
        this.level = level;
        this.game = game;
        this.character = character;
    }

    public abstract void makeMove();

    protected char opponentsCharacter() {
        if (this.character == 'X') return 'O';
        else return 'X';
    }

    public static Player createPlayer(String level, Game game, char character) {
        switch (Level.valueOf(level.toUpperCase())) {
            case EASY:
                return new EasyPlayer(game, character);
            case MEDIUM:
                return new MediumPlayer(game, character);
            case HARD:
                return new HardPlayer(game, character);
            case USER:
                return new UserPlayer(game, character);
            default:
                return null;
        }
    }
}

