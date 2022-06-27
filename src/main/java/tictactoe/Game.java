package tictactoe;

import java.util.Scanner;

public class Game {

    Scanner scanner = new Scanner(System.in);
    MiniMax miniMax = new MiniMax();
    Board board = new Board();

    public void run() {
        String chooseOption;
        do {
            System.out.print("Input command: ");
            chooseOption = scanner.nextLine();
            switch (chooseOption) {
                case "start easy easy": {
                    board.draw();
                    System.out.println(MakingText.EASY);
                    easyWithEasy();
                    break;
                }
                case "start easy user": {
                    board.draw();
                    print(MakingText.EASY);
                    easyWithUser();
                    break;
                }
                case "start user easy": {
                    board.draw();
                    print(MakingText.EASY);
                    userWithEasy();
                    break;
                }
                case "start user user": {
                    board.draw();
                    print(MakingText.EASY);
                    userWithUser();
                    break;
                }
                case "start medium user": {
                    board.draw();
                    print(MakingText.MEDIUM);
                    mediumWithUser();
                    break;
                }
                case "start user medium": {
                    board.draw();
                    print(MakingText.MEDIUM);
                    userWithMedium();
                    break;
                }
                case "start medium medium": {
                    board.draw();
                    print(MakingText.MEDIUM);
                    mediumWitMedium();
                    break;
                }
                case "start hard user": {
                    board.draw();
                    print(MakingText.HARD);
                    hardWsUser();
                    break;
                }
                case "start user hard": {
                    board.draw();
                    print(MakingText.HARD);
                    userWsHard();
                    break;
                }
                case "start hard hard": {
                    hardWsHard();
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

    private void userWithMedium() {
        while (!board.hasWinner()) {
            board.placeMark();
            if (!board.hasWinner()) {
                print(MakingText.MEDIUM);
                board.smartRandom();
            }
        }
    }

    private void mediumWithUser() {
        while (!board.hasWinner()) {
            board.smartRandom();
            board.placeMark();
            if (!board.hasWinner()) {
                print(MakingText.MEDIUM);
            }
        }
    }

    private void mediumWitMedium() {
        while (!board.hasWinner()) {
            board.smartRandom();
            if (!board.hasWinner()) {
                print(MakingText.MEDIUM);
            }
        }
    }

    private void userWithUser() {
        while (!board.hasWinner()) {
            board.placeMark();
        }
    }

    private void easyWithEasy() {
        while (!board.hasWinner()) {
            board.chooseRandomCell();
            if (!board.hasWinner()) {
                print(MakingText.EASY);
            }
        }
    }

    private void easyWithUser() {
        while (!board.hasWinner()) {
            board.chooseRandomCell();
            board.placeMark();
            if (!board.hasWinner()) {
                print(MakingText.EASY);
            }
        }
    }

    private void userWithEasy() {
        while (!board.hasWinner()) {
            board.placeMark();
            if (!board.hasWinner()) {
                print(MakingText.EASY);
                board.chooseRandomCell();
            }
        }
    }

    private void hardWsUser() {
        while (!board.hasWinner()) {
            miniMax.setMarkToBoard(board, false);
            board.placeMark();
            if (!board.hasWinner()) {
                print(MakingText.HARD);
            }
        }
    }

    private void hardWsHard() {
        while (!board.hasWinner()) {
            miniMax.setMarkToBoard(board, false);
            if (!board.hasWinner()) {
                print(MakingText.HARD);
            }
        }
    }

    private void userWsHard() {
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
