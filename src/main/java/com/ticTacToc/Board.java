package com.ticTacToc;

import java.io.*;
import java.util.Scanner;
import com.google.gson.Gson;

public class Board {
    private Symbol[][] grid;
    private int size;

    public Board(int size) {
        this.size = size;
        grid = new Symbol[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                grid[row][col] = new Symbol();
            }
        }
    }

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
        int row;
        int col;
        System.out.println("Player " + player.name + ", enter row and column (0-" + (size - 1) + ") to place your symbol " + player.playerSymbol.getPlayerSymbol());
        System.out.print("Row: ");
        row = sc.nextInt();
        System.out.print("Column: ");
        col = sc.nextInt();
        if (check(grid[row][col]) == true) {
            System.out.println("Player " + player.name + ", enter row and column (0-" + (size - 1) + ") to place your symbol " + player.playerSymbol.getPlayerSymbol());
            System.out.print("Row: ");
            row = sc.nextInt();
            System.out.print("Column: ");
            col = sc.nextInt();
            grid[row][col].setPlayerSymbol(player.playerSymbol.getPlayerSymbol());
        } else
            grid[row][col].setPlayerSymbol(player.playerSymbol.getPlayerSymbol());
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

    public boolean isFullOfSymbols() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                char symbol = grid[row][col].getPlayerSymbol();
                if (symbol != 'x' && symbol != 'o') {
                    return false;
                }
            }
        }
        return true;
    }

    public void saveBoardToFile() {
        Gson gson = new Gson();
        String json = gson.toJson(grid);
        try (FileWriter writer = new FileWriter("grid.json")) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void readBoardFromFile() {
        Gson gson = new Gson();
        try (Reader reader = new FileReader("grid.json")) {
            Symbol[][] newGrid = gson.fromJson(reader, Symbol[][].class);
            if (newGrid.length == size) {
                grid = newGrid;
            } else {
                System.out.println("The size of the saved grid does not match the current board size");
            }
        } catch (FileNotFoundException e) {
            System.out.println("The specified file does not exist: " + "grid.json");
        } catch (IOException e) {
            System.out.println("An error occurred while reading from the file: " + "grid.json");
        }
    }
}
