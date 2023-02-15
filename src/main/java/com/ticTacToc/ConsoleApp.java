package com.ticTacToc;

import java.io.File;
import java.util.Scanner;

public class ConsoleApp {
    public static void main(String[] args) {
        // Initialize the game board with a grid of 3x3
        Board board = new Board(3);
        Scanner sc = new Scanner(System.in);
        boolean playGame = true;
        // Initialize the state manager to save and load game state
        StateManager appManager = new StateManager();
        // Initialize the User Input Handler to check the user input
        UserInputHandler inputManager = new UserInputHandler();
        //Initialize the boat player
        BotPlayer botPlayer = new BotPlayer(null, null);
        //Initialize the  player1 nd player2
        Player player1 = new Player(null, null);
        Player player2 = new Player(null, null);

        System.out.println("Are you to play with boat(computer) or with another player: (press 1 for boat and 2 for another player)");
        int userInput1 = inputManager.getUserChoice();
        //if the player one is human and one is boat(computer)
        if (userInput1 == 1) {
            System.out.print("Enter player 1 name: ");
            String name1 = inputManager.getUserChoiceString();
            player1.setName(name1);
            Symbol player1Symbol = player1.pickSymbol();
            player1.setPlayerSymbol(player1Symbol);

            player1 = new Player(name1, player1Symbol);
            System.out.println("Player name: " + player1.name);
            System.out.println("Player symbol: " + player1.playerSymbol.getPlayerSymbol());

            botPlayer.setName("Computer");
            System.out.println("Player name: " + botPlayer.name);
            Symbol boatSymbol = botPlayer.pickSymbol();
            botPlayer.setPlayerSymbol(boatSymbol);
            System.out.println("Player symbol: " + botPlayer.getSymbol());

            int turn = 1;
            while (playGame == true) {
                board.displayBoard();
                Player currentPlayer = (turn % 2 == 1) ? player1 : botPlayer;
                if (currentPlayer == player1)
                    board.MakeMove(currentPlayer);
                else if (currentPlayer == botPlayer)
                    board.BoatMakeMove(currentPlayer);
                appManager.saveBoardToFile(board);
                if (board.hasWinner() == currentPlayer.getSymbol().getPlayerSymbol()) {
                    System.out.println(currentPlayer.getName() + " is the winner");
                    File gameFile = new File("grid.json");
                    gameFile.delete();
                    File playerFile = new File("players.json");
                    gameFile.delete();
                    playGame = false;
                }
                if (board.isFullOfSymbols(player1, botPlayer) == true) {
                    System.out.println("The grid is full of only " + player1.getSymbol().getPlayerSymbol() + "and " + botPlayer.playerSymbol.getPlayerSymbol() + " symbols. No more moves can be made.");
                    File file = new File("grid.json");
                    file.delete();
                    playGame = false;
                }
                turn++;
            }
            //if the two player are human
        } else if (userInput1 == 2) {
            // get player 1 information
            System.out.print("Enter player 1 name: ");
            String name1 = inputManager.getUserChoiceString();

            Symbol player1Symbol = player1.pickSymbol();
            player1.setPlayerSymbol(player1Symbol);

            player1 = new Player(name1, player1Symbol);
            System.out.println("Player name: " + player1.name);
            System.out.println("Player symbol: " + player1.playerSymbol.getPlayerSymbol());

            // get player 2 information
            System.out.print("Enter second player name: ");
            String name2 = inputManager.getUserChoiceString();
            Symbol playerSymbol2 = player1.pickSymbol();
            player2.setPlayerSymbol(playerSymbol2);

            player2 = new Player(name2, playerSymbol2);
            System.out.println("Player 2 name: " + player2.name);
            System.out.println("Player 2 symbol: " + player2.playerSymbol.getPlayerSymbol());

            //     //start the game
            int turn = 1;
            while (playGame == true) {
                board.displayBoard();
                Player currentPlayer = (turn % 2 == 1) ? player1 : player2;
                board.MakeMove(currentPlayer);
                appManager.saveBoardToFile(board);
                if (board.hasWinner() == currentPlayer.getSymbol().getPlayerSymbol()) {
                    System.out.println(currentPlayer.getName() + " is the winner");
                    File gameFile = new File("grid.json");
                    gameFile.delete();
                    File playerFile = new File("players.json");
                    gameFile.delete();
                    playGame = false;
                }
                if (board.isFullOfSymbols(player1, player2) == true) {
                    System.out.println("The grid is full of only " + player1.getSymbol().getPlayerSymbol() + "and " + player2.playerSymbol.getPlayerSymbol() + " symbols. No more moves can be made.");
                    File file = new File("grid.json");
                    file.delete();
                    playGame = false;
                }
                turn++;
            }
        }
    } // End of class main
} // End of class ConsoleApp


