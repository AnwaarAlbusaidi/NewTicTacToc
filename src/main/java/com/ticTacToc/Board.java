package com.ticTacToc;

public class Board {
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

    public int getSize() {
        return size;
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
    /**
     The grid representing the Tic Tac Toe board.
     */
    Symbol[][] grid;
    /**
     The size of the Tic Tac Toe board.
     */
    int size;
}
