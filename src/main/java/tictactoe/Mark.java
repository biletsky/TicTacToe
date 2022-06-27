package tictactoe;

public enum Mark {

    X('X'),
    O('O'),
    BLANK(' ');

    private final char mark;

    Mark(char mark) {
        this.mark = mark;
    }

    public boolean isMarked() {
        return this != BLANK;
    }

    public char getMark() {
        return this.mark;
    }

    @Override
    public String toString() {
        return String.valueOf(mark);
    }

}
