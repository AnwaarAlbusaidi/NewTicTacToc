package com.ticTacToc;
import com.google.gson.Gson;
import java.io.*;
import java.util.Scanner;

public class Board {
    private Symbol[][] grid;
    private int size;
    UserInputHandler inputManger = new UserInputHandler();
    public Board(){

    }

    public Board(Symbol[][] grid, int size) {
        this.grid = grid;
        this.size = size;
    }

    public Board(Symbol[][] grid) {
        this.grid = grid;
    }

    public void setGrid(Symbol[][] grid) {
        this.grid = grid;
    }

    public int getSize() {
        return size;
    }

    public Symbol[][] getGrid() {
        return grid;
    }

    public Board(int size) {
        this.size = size;
        grid = new Symbol[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                grid[row][col] = new Symbol();
            }
        }
    }


//    public void saveBoardToFile() {
//        Gson gson = new Gson();
//        String json = gson.toJson(grid);
//        try (FileWriter writer = new FileWriter("grid.json")) {
//            writer.write(json);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void readBoardFromFile() {
//        Gson gson = new Gson();
//        try (Reader reader = new FileReader("grid.json")) {
//            Symbol[][] newGrid = gson.fromJson(reader, Symbol[][].class);
//            setGrid(newGrid);
//        } catch (FileNotFoundException e) {
//            System.out.println("The specified file does not exist: " + "grid.json");
//        } catch (IOException e) {
//            System.out.println("An error occurred while reading from the file: " + "grid.json");
//        }
//    }

    public void displayBoard() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                System.out.print(grid[row][col].getPlayerSymbol() + " ");
            }
            System.out.println();
        }
    }

    public void putToGrid(Player player) {
        Scanner sc = new Scanner(System.in);
        String input;
        int row, col;
        System.out.println("Player " + player.name + ", enter row and column (0-" + (size - 1) + ") separated by space or 'q' to quit: " + player.playerSymbol.getPlayerSymbol());
        input =  inputManger.getUserChoiceString();
        //input = sc.nextLine();
        if (input.equals("q")) {
            System.out.println("Player " + player.name + " has exited the game");
            System.exit(0);
        }
        try {
            String[] inputArray = input.split(" ");
            row = Integer.parseInt(inputArray[0]);
            col = Integer.parseInt(inputArray[1]);
            if (check(grid[row][col]) == true) {
                System.out.println("Player " + player.name + ", enter row and column (0-" + (size - 1) + ") separated by space: " + player.playerSymbol.getPlayerSymbol());
              //  input = sc.nextLine();
                input =  inputManger.getUserChoiceString();
                String[] inputArray2 = input.split(" ");
                row = Integer.parseInt(inputArray2[0]);
                col = Integer.parseInt(inputArray2[1]);
                grid[row][col].setPlayerSymbol(player.playerSymbol.getPlayerSymbol());
            } else {
                grid[row][col].setPlayerSymbol(player.playerSymbol.getPlayerSymbol());
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid row and column separated by space.");{
            }

        }
    }


    public boolean check(Symbol y) {
        if (y.getPlayerSymbol() == 'x' || y.getPlayerSymbol() == 'o') {
            System.out.println("Someone has already made a move at this position! Try again.");
            return true;
        } else
            return false;
    }

    public char hasWinner() {
        //Symbol d = new Symbol('D');
        for (int i = 0; i < 3; i++) // to check the row
        {
            if (grid[i][0].equals(grid[i][1]) && grid[i][1].equals(grid[i][2]))
                return grid[i][0].playerSymbol;
        }
        for (int j = 0; j < 3; j++) // to check the column
        {
            if (grid[0][j].equals(grid[1][j]) && grid[1][j].equals(grid[2][j]))
                return grid[0][j].playerSymbol;
        }

        if (grid[0][0].equals(grid[1][1]) && grid[1][1].equals(grid[2][2])) // check dislodge
            return grid[0][0].playerSymbol;

        if (grid[0][2].equals(grid[1][1]) && grid[1][1].equals(grid[2][0])) // check dislodge
            return grid[1][1].playerSymbol;

        return 'D';
    }

    public boolean isFullOfSymbols(Player player1, Player player2) {
        char player1Symbol = player1.playerSymbol.getPlayerSymbol();
        char player2Symbol = player2.playerSymbol.getPlayerSymbol();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                char symbol = grid[row][col].getPlayerSymbol();
                if (symbol != player1Symbol && symbol != player2Symbol) {
                    return false;
                }
            }
        }
        return true;
    }



}
