package com.ticTacToc;

import java.util.Scanner;

public class ConsoleApp {
    public static void main(String[] args) {

        Board board = new Board(3);
        Scanner sc = new Scanner(System.in);
        boolean playGame = true;
        // get player 1 information
        System.out.print("Enter player 1 name: ");
        String name1 = sc.nextLine();

        System.out.print("Enter player 1 symbol: ");
        char symbol = sc.nextLine().charAt(0);
        Symbol player1Symbol = new Symbol();
        player1Symbol.setPlayerSymbol(symbol);

        Player player1 = new Player(name1, player1Symbol);
        System.out.println("Player name: " + player1.name);
        System.out.println("Player symbol: " + player1.playerSymbol.getPlayerSymbol());

        // get player 2 information
        System.out.print("Enter second player name: ");
        String name2 = sc.nextLine();
        System.out.print("Enter second player symbol: ");
        char symbol2 = sc.nextLine().charAt(0);
        Symbol playerSymbol2 = new Symbol();
        playerSymbol2.setPlayerSymbol(symbol2);
        //check if player 2 enter the same symbol as Player 1
        while (player1Symbol.isSameSymbol(playerSymbol2)) {
            System.out.println("Error: player symbols are the same. Enter another symbol:");
            symbol2 = sc.nextLine().charAt(0);
            playerSymbol2.setPlayerSymbol(symbol2);
        }
        Player player2 = new Player(name2, playerSymbol2);
        System.out.println("Player 2 name: " + player2.name);
        System.out.println("Player 2 symbol: " + player2.playerSymbol.getPlayerSymbol());

        int turn = 1;
        System.out.println(" press 1 for resume game and 2 for new the game: ");
        int userInput = Integer.parseInt(sc.nextLine());
        while (playGame == true){
     if(userInput == 1)
     {
         System.out.println("Current Board:");
         board.readBoardFromFile();
         board.displayBoard();
         Player currentPlayer = (turn % 2 == 1) ? player1 : player2;
         board.putToGrid(currentPlayer);
         board.saveBoardToFile();
         if(board.hasWinner() == currentPlayer.getSymbol().getPlayerSymbol())
         {
             System.out.println(currentPlayer.getName() + " is the winner");
             playGame = false;
         }
         if(board.isFullOfSymbols() == true)
         {
             System.out.println("The grid is full of only X and O symbols. No more moves can be made.");
             playGame = false;
         }
         turn++;
     }
     else if( userInput == 2)
            {
                System.out.println("Current Board:");
                board.displayBoard();
                Player currentPlayer = (turn % 2 == 1) ? player1 : player2;
                board.putToGrid(currentPlayer);
                board.saveBoardToFile();
                if (board.hasWinner() == currentPlayer.getSymbol().getPlayerSymbol()) {
                    System.out.println(currentPlayer.getName() + " is the winner");
                    playGame = false;
                }
                if (board.isFullOfSymbols() == true) {
                    System.out.println("The grid is full of only X and O symbols. No more moves can be made.");
                    playGame = false;
                }
                turn++;
            }
        }
    }
}


