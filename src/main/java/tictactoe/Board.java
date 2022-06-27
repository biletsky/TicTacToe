package tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static tictactoe.Mark.*;

public class Board {
    private final Mark[][] board;
    private Mark winningMark;
    private final int BOARD_WIDTH = 3;
    private boolean crossTurn, gameOver;
    private int row;
    private int col;
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();
    public Board() {
        board = new Mark[BOARD_WIDTH][BOARD_WIDTH];
        crossTurn = true;
        gameOver = false;
        winningMark = BLANK;
        initialiseDesk();
    }

    private void initialiseDesk() {
        for (int row = 0; row < BOARD_WIDTH; row++) {
            for (int col = 0; col < BOARD_WIDTH; col++) {
                board[row][col] = BLANK;
            }
        }
    }

    public void placeMark() {
        if (!hasWinner()) {
            while (true) {
                try {
                    System.out.print("Enter the coordinates: ");
                    String coordinates = scanner.nextLine();
                    row = Integer.parseInt(coordinates.substring(0, 1)) - 1;
                    col = Integer.parseInt(coordinates.substring(2, 3)) - 1;
                    if (board[row][col].isMarked()) {
                        System.out.println("This cell is occupied! Choose another one!");
                        continue;
                    }
                    setMarkAt(row, col);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Bad parameters!");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Coordinates should be from 1 to 3!");
                }
            }
        }
    }

    protected boolean hasWinner() {
        int sum = 0;
        for (Mark[] marks : boardWinnerCombination()) {
            for (Mark mark : marks) {
                sum += mark.getMark();
                if (sum == 264 || sum == 237) {
                    return true;
                }
                else if(checkAvailableCell() < 1) {
                    return true;
                }
            }
            sum = 0;
        }
        return false;
    }

    private void togglePlayer() {
        crossTurn = !crossTurn;
    }

    public Mark getMarkAt(int row, int column) {
        return board[row][column];
    }

    public boolean isTileMarked(int row, int column) {
        return board[row][column].isMarked();
    }

    public void setMarkAt(int row, int column, Mark newMark) {
        board[row][column] = newMark;
    }

    protected void setMarkAt(int row, int col) {
        board[row][col] = crossTurn ? X : O;
        togglePlayer();
        draw();
    }

    public void draw() {
        StringBuilder strBldr = new StringBuilder();
        strBldr.append("---------").append("\n");
        for (Mark[] row : board) {
            strBldr.append("| ");
            for (Mark tile : row) {
                strBldr.append(tile).append(' ');
            }
            strBldr.append("|").append("\n");
        }
        strBldr.append("---------");
        System.out.println(strBldr);
        winnerText();
    }

    public boolean isCrossTurn() {
        return crossTurn;
    }

    public int getWidth() {
        return BOARD_WIDTH;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public Mark getWinningMark() {
        return winningMark;
    }

    protected void chooseRandomCell() {
        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        }
        while (board[row][col].isMarked());
        setMarkAt(row, col);
    }

    private List<Mark[]> boardWinnerCombination() {
        List<Mark[]> list = new ArrayList<>();
        list.add(new Mark[]{board[0][0], board[0][1], board[0][2]});
        list.add(new Mark[]{board[1][0], board[1][1], board[1][2]});
        list.add(new Mark[]{board[2][0], board[2][1], board[2][2]});
        list.add(new Mark[]{board[0][0], board[1][1], board[2][2]});
        list.add(new Mark[]{board[0][2], board[1][1], board[2][0]});
        list.add(new Mark[]{board[0][0], board[1][0], board[2][0]});
        list.add(new Mark[]{board[0][1], board[1][1], board[2][1]});
        list.add(new Mark[]{board[0][2], board[1][2], board[2][2]});
        return list;
    }

    private int countOfWinnerCombination(Mark mark) {
        int count = 0;
        int sum = 0;
        if (mark == X) {
            for (Mark[] marks : boardWinnerCombination()) {
                for (Mark symbol : marks) {
                    sum += symbol.getMark();
                    if (sum == 208) {
                        count++;
                    }
                }
                sum = 0;
            }
        } else {
            for (Mark[] marks : boardWinnerCombination()) {
                for (Mark symbol : marks) {
                    sum += symbol.getMark();
                    if (sum == 190) {
                        count++;
                    }
                }
                sum = 0;
            }
        }
        return count;
    }

    private Mark checkPotentialWinner(Mark symbol) {
        int rowSum = 0;
        if (symbol == X) {
            for (Mark[] marks : boardWinnerCombination()) {
                for (Mark mark : marks) {
                    rowSum += mark.getMark();
                }
                if (rowSum == 208) {
                    return X;
                } else rowSum = 0;
            }
        } else {
            for (Mark[] marks : boardWinnerCombination()) {
                for (Mark mark : marks) {
                    rowSum += mark.getMark();
                }
                if (rowSum == 190) {
                    return O;
                } else rowSum = 0;

            }
        }
        return BLANK;
    }

    private void winnerText(){
        int rowSum = 0;
        for (Mark[] marks : boardWinnerCombination()) {
            for (Mark mark : marks) {
                rowSum += mark.getMark();
            }
            if (rowSum == 264) {
                System.out.println("X wins");
            } else if (rowSum == 237) {
                System.out.println("O wins");
            }
            rowSum = 0;
        }
        if(checkAvailableCell() < 1){
            System.out.println("Game over!");
        }
    }

    protected Mark opponentMark() {
        Mark mark;
        togglePlayer();
        mark = crossTurn ? X : O;
        togglePlayer();
        return mark;
    }

    protected void smartRandom() {
        Mark mark = crossTurn ? X : O;
        int check = countOfWinnerCombination(opponentMark());
        if (checkPotentialWinner(mark).equals(mark)) {
            for (int row = 0; row < BOARD_WIDTH; row++) {
                for (int col = 0; col < BOARD_WIDTH; col++) {
                    if (!board[row][col].isMarked()) {
                        setMarkAt(row, col, mark);
                        if (!hasWinner()) {
                            setMarkAt(row, col, BLANK);
                        } else {
                            break;
                        }
                    }
                }
                if (hasWinner()) {
                    break;
                }
            }
            draw();
        } else if (checkPotentialWinner(opponentMark()).equals(opponentMark())) {
            boolean twoOptionWinner = false;
            for (int row = 0; row < BOARD_WIDTH; row++) {
                for (int col = 0; col < BOARD_WIDTH; col++) {
                    if (!board[row][col].isMarked()) {
                        setMarkAt(row, col, mark);
                        if (check > 1) {
                            if (countOfWinnerCombination(opponentMark()) > 1) {
                                setMarkAt(row, col, BLANK);
                            } else {
                                twoOptionWinner = true;
                                break;
                            }
                        } else if (checkPotentialWinner(opponentMark()).equals(opponentMark()) && checkAvailableCell() > 0) {
                            setMarkAt(row, col, BLANK);
                        } else {
                            break;
                        }
                    }
                }
                if (twoOptionWinner) {
                    break;
                } else if (!checkPotentialWinner(opponentMark()).equals(opponentMark()) && countOfWinnerCombination(opponentMark()) < 2) {
                    break;
                }
            }
            togglePlayer();
            draw();
        } else {
            chooseRandomCell();
        }
    }

    protected int checkAvailableCell() {
        int count = 0;
        for (Mark[] marks : board ) {
            for (Mark mark : marks) {
                if (mark.equals(BLANK)) {
                    count++;
                }
            }
        }
        return count;
    }

}
