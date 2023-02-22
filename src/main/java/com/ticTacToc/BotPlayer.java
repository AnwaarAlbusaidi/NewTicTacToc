package com.ticTacToc;

import java.util.Random;

/**
 BotPlayer class represents a player controlled by the computer in a game of Tic Tac Toe.
 */
public class BotPlayer extends Player {
    /**
     * Constructor for creating a new BotPlayer object.
     *
     * @param name         the name of the player.
     * @param playerSymbol the symbol representing the player in the game.
     */
    public BotPlayer(String name, Symbol playerSymbol) {
        super(name, playerSymbol);
    }

    /**
     * Makes a move for the BotPlayer on the specified Board object.
     *
     * @param board the Board object on which the move is to be made.
     */
    public void BotMakeMove(Board board) {
        int row, col;
        System.out.println("Player " + this.name + " makes a move: " + this.playerSymbol.getPlayerSymbol());
        do {
            row = (int)(Math.random() * 3);
            col = (int)(Math.random() * 3);
        } while (board.check(board.getGrid()[row][col]) == true);
        board.getGrid()[row][col].setPlayerSymbol(this.playerSymbol.getPlayerSymbol());
    }
}


