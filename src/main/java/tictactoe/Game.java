package tictactoe;

import java.util.Scanner;

public class Game {
    Scanner scanner = new Scanner(System.in);
    MiniMax miniMax = new MiniMax();

    public void run() {
        String chooseOption;
        do {
            System.out.print("Input command: ");
            chooseOption = scanner.nextLine();
            switch (chooseOption) {
                case "start easy easy": {
                    Board board = new Board();
                    board.draw();
                    System.out.println(MakingText.EASY);
                    easyWithEasy(board);
                    break;
                }
                case "start easy user": {
                    Board board = new Board();
                    board.draw();
                    print(MakingText.EASY);
                    easyWithUser(board);
                    break;
                }
                case "start user easy": {
                    Board board = new Board();
                    board.draw();
                    print(MakingText.EASY);
                    userWithEasy(board);
                    break;
                }
                case "start user user": {
                    Board board = new Board();
                    board.draw();
                    print(MakingText.EASY);
                    userWithUser(board);
                    break;
                }
                case "start medium user": {
                    Board board = new Board();
                    board.draw();
                    print(MakingText.MEDIUM);
                    mediumWithUser(board);
                    break;
                }
                case "start user medium": {
                    Board board = new Board();
                    board.draw();
                    print(MakingText.MEDIUM);
                    userWithMedium(board);
                    break;
                }
                case "start medium medium": {
                    Board board = new Board();
                    board.draw();
                    print(MakingText.MEDIUM);
                    mediumWitMedium(board);
                    break;
                }
                case "start hard user": {
                    Board board = new Board();
                    board.draw();
                    print(MakingText.HARD);
                    hardWsUser(board);
                    break;
                }
                case "start user hard": {
                    Board board = new Board();
                    board.draw();
                    print(MakingText.HARD);
                    userWsHard(board);
                    break;
                }
                case "start hard hard": {
                    Board board = new Board();
                    board.draw();
                    print(MakingText.HARD);
                    hardWsHard(board);
                    break;
                }
                case "exit": {
                    break;
                }
                default: {
                    System.out.println("Bad parameters!");
                    break;
                }
            }
        }
        while (!chooseOption.equals("exit"));
    }

    private void userWithMedium(Board board) {
        while (!board.hasWinner()) {
            board.placeMark();
            if (!board.hasWinner()) {
                print(MakingText.MEDIUM);
                board.smartRandom();
            }
        }
    }

    private void mediumWithUser(Board board) {
        while (!board.hasWinner()) {
            board.smartRandom();
            board.placeMark();
            if (!board.hasWinner()) {
                print(MakingText.MEDIUM);
            }
        }
    }

    private void mediumWitMedium(Board board) {
        while (!board.hasWinner()) {
            board.smartRandom();
            if (!board.hasWinner()) {
                print(MakingText.MEDIUM);
            }
        }
    }

    private void userWithUser(Board board) {
        while (!board.hasWinner()) {
            board.placeMark();
        }
    }

    private void easyWithEasy(Board board) {
        while (!board.hasWinner()) {
            board.chooseRandomCell();
            if (!board.hasWinner()) {
                print(MakingText.EASY);
            }
        }
    }

    private void easyWithUser(Board board) {
        while (!board.hasWinner()) {
            board.chooseRandomCell();
            board.placeMark();
            if (!board.hasWinner()) {
                print(MakingText.EASY);
            }
        }
    }

    private void userWithEasy(Board board) {
        while (!board.hasWinner()) {
            board.placeMark();
            if (!board.hasWinner()) {
                print(MakingText.EASY);
                board.chooseRandomCell();
            }
        }
    }

    private void hardWsUser(Board board) {
        while (!board.hasWinner()) {
            miniMax.setMarkToBoard(board, false);
            board.placeMark();
            if (!board.hasWinner()) {
                print(MakingText.HARD);
            }
        }
    }

    private void hardWsHard(Board board) {
        while (!board.hasWinner()) {
            miniMax.setMarkToBoard(board, false);
            if (!board.hasWinner()) {
                print(MakingText.HARD);
                miniMax.setMarkToBoard(board, true);
                print(MakingText.HARD);
            }
        }
    }

    private void userWsHard(Board board) {
        while (!board.hasWinner()) {
            board.placeMark();
            if (!board.hasWinner()) {
                print(MakingText.HARD);
                miniMax.setMarkToBoard(board, true);
            }
        }
    }

    private void print(String text) {
        System.out.println(text);
    }

}
