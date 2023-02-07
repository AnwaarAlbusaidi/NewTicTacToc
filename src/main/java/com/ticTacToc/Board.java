package com.ticTacToc;
import java.util.Scanner;

public class Board {
    /**
     The grid representing the Tic Tac Toe board.
     */
    private Symbol[][] grid;
    /**
     The size of the Tic Tac Toe board.
     */
    private int size;
    /**
     An object of UserInputHandler class to get user input.
     */
    UserInputHandler inputManger = new UserInputHandler();

    public Board(){
    }
    /**
     Constructor to initialize the grid and size of the Tic Tac Toe board.
     @param grid the Tic Tac Toe grid
     @param size the size of the grid
     */
    public Board(Symbol[][] grid, int size) {
        this.grid = grid;
        this.size = size;
    }
    public Symbol[][] getGrid() {
        return grid;
    }
    /**
     Constructor for Board class
     @param size an integer that represents the size of the board
     */
    public Board(int size) {
        this.size = size;
        grid = new Symbol[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                grid[row][col] = new Symbol();
            }
        }
    }
    /**
     Displays the Tic Tac Toe board
     */
    public void displayBoard() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                System.out.print(grid[row][col].getPlayerSymbol() + " ");
            }
            System.out.println();
        }
    }
    /**
     The method allows a player to place their symbol on the game grid.
     The player is prompted to enter the row and column where they want to place their symbol.
     If the input is invalid or if the chosen location is already occupied, the player is prompted again.
     If the player enters "q", the game is exited.
     @param player the current player
     */
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
    /**
     This method checks for the winner of the tic-tac-toe game.
     It checks the rows, columns, and diagonals of the grid to see if a winning pattern has been formed.
     @return the winning symbol of the player. If there is no winner, it returns 'D' for draw.
     */
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

    /**
     Checks if the grid is full of symbols.
     @param player1 the first player
     @param player2 the second player
     @return true if the grid is full of symbols, false otherwise
     */
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
