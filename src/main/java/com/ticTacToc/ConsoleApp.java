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
        BoatPlayer boatPlayer = new BoatPlayer() ;
        //Initialize the  player1 nd player2
        Player player1 = new Player(null, null);
        Player player2 = new Player(null, null);

        System.out.println("Are you to play with boat(computer) or with another player: (press 1 for boat and 2 for another player)");
        int userInput1 = Integer.parseInt(sc.nextLine());
        if (userInput1 == 1) {
            System.out.print("Enter player 1 name: ");
            String name1 = inputManager.getUserChoiceString();

            System.out.print("Enter player 1 symbol (x or o): ");
            char symbol = inputManager.getUserChoiceString().charAt(0);
            Symbol player1Symbol = new Symbol();
            player1Symbol.setPlayerSymbol(symbol);

            player1 = new Player(name1, player1Symbol);
            System.out.println("Player name: " + player1.name);
            System.out.println("Player symbol: " + player1.playerSymbol.getPlayerSymbol());
            boatPlayer.setName("Computer");
            Symbol boatSymbol = new Symbol();
            if (player1.playerSymbol.getSymbol() == 'x')
                boatPlayer.playerSymbol.setPlayerSymbol('o');
            else if (player1.playerSymbol.getSymbol() == 'o') {
                boatPlayer.playerSymbol.setPlayerSymbol('x');
            }
            System.out.println("Player name: " + boatPlayer.name);
            System.out.println("Player symbol: " + boatPlayer.playerSymbol.getPlayerSymbol());

            int turn = 1;
            while (playGame == true) {
                board.displayBoard();
                Player currentPlayer = (turn % 2 == 1) ? player1 : boatPlayer;
                if (currentPlayer == player1)
                    board.MakeMove(currentPlayer);
                else
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
                if (board.isFullOfSymbols(player1, player2) == true) {
                    System.out.println("The grid is full of only " + player1.getSymbol().getPlayerSymbol() + "and " + player2.playerSymbol.getPlayerSymbol() + " symbols. No more moves can be made.");
                    File file = new File("grid.json");
                    file.delete();
                    playGame = false;
                }
                turn++;
            }
        } else if (userInput1 == 2) {
            // get player 1 information
            System.out.print("Enter player 1 name: ");
            String name1 = inputManager.getUserChoiceString();

            System.out.print("Enter player 1 symbol: ");
            char symbol = inputManager.getUserChoiceString().charAt(0);
            Symbol player1Symbol = new Symbol();
            player1Symbol.setPlayerSymbol(symbol);

            player1 = new Player(name1, player1Symbol);
            System.out.println("Player name: " + player1.name);
            System.out.println("Player symbol: " + player1.playerSymbol.getPlayerSymbol());

            // get player 2 information
            System.out.print("Enter second player name: ");
            String name2 = inputManager.getUserChoiceString();
            System.out.print("Enter second player symbol: ");
            char symbol2 = inputManager.getUserChoiceString().charAt(0);
            Symbol playerSymbol2 = new Symbol();
            playerSymbol2.setPlayerSymbol(symbol2);
            //check if player 2 enter the same symbol as Player 1
            while (player1Symbol.isSameSymbol(playerSymbol2)) {
                System.out.println("Error: player symbols are the same. Enter another symbol:");
                symbol2 = inputManager.getUserChoiceString().charAt(0);
                playerSymbol2.setPlayerSymbol(symbol2);
            }
            player2 = new Player(name2, playerSymbol2);
            System.out.println("Player 2 name: " + player2.name);
            System.out.println("Player 2 symbol: " + player2.playerSymbol.getPlayerSymbol());
        }

//
//        System.out.println(" press 1 for resume game and 2 for new the game: ");
//        int userInput = Integer.parseInt(sc.nextLine());
//     if(userInput == 1) {
//         // Load saved game state
//         System.out.println("Current Board:");
//         Player[] players = appManager.readFromJsonFile();
//         System.out.println("Player 1 name: " + players[0].name);
//         System.out.println("Player 1 symbol: " + players[0].playerSymbol.getPlayerSymbol());
//         System.out.println("Player 2 name: " + players[1].name);
//         System.out.println("Player 2 symbol: " + players[1].playerSymbol.getPlayerSymbol());
//         player1 = new Player(players[0].name, players[0].playerSymbol);
//         player2 = new Player(players[1].name, players[1].playerSymbol);
//         board = appManager.readBoardFromFile();
//     }
//     else if( userInput == 2) //new game
//     {
//         System.out.println("Current Board:");
//         appManager.writeToJsonFile(player1, player2);
//     }


//     //start the game
//        int turn = 1;
//     while (playGame == true){
//                board.displayBoard();
//                Player currentPlayer = (turn % 2 == 1) ? player1 : player2;
//                board.MakeMove(currentPlayer);
//                appManager.saveBoardToFile(board);
//                if (board.hasWinner() == currentPlayer.getSymbol().getPlayerSymbol()) {
//                    System.out.println(currentPlayer.getName() + " is the winner");
//                    File gameFile = new File("grid.json");
//                    gameFile.delete();
//                    File playerFile = new File("players.json");
//                    gameFile.delete();
//                    playGame = false;
//                }
//                if (board.isFullOfSymbols(player1,player2) == true) {
//                    System.out.println("The grid is full of only " + player1.getSymbol().getPlayerSymbol() + "and " +player2.playerSymbol.getPlayerSymbol() + " symbols. No more moves can be made.");
//                    File file = new File("grid.json");
//                    file.delete();
//                    playGame = false;
//                }
//                turn++;
//            }
//        }
    }
    }


