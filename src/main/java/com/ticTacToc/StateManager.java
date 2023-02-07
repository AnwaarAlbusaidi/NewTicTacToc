package com.ticTacToc;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;

public class StateManager {

    Board currentBoard = new Board();
    /**
     Saves the current state of the Tic Tac Toe game board to a file named "grid.json".
     @param currentBoard The current state of the Tic Tac Toe game board to be saved.
     */
    public void saveBoardToFile(Board currentBoard) {
        Gson gson = new Gson();
        String json = gson.toJson(currentBoard.getGrid());
        try (FileWriter writer = new FileWriter("grid.json")) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     This method reads the saved Board state from a JSON file and returns a new Board instance.
     @return the Board instance from the saved state in the file, or {@code null} if an IOException occurs during file reading.
     */
    public Board readBoardFromFile() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader("grid.json")) {
            Symbol[][] grid = gson.fromJson(reader, Symbol[][].class);
            int size = grid.length;
            return new Board(grid, size);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     Writes player information to a JSON file.
     @param player1 First player
     @param player2 Second player
     */
    public void writeToJsonFile(Player player1, Player player2) {
        Gson gson = new Gson();
        try {
            FileWriter writer = new FileWriter("players.json");
            gson.toJson(player1, writer);
            gson.toJson(player2, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     Reads the players' information from a JSON file.
     @return The array of players read from the file.
     */
    public Player[] readFromJsonFile() {
        Gson gson = new Gson();
        Player[] players = new Player[2];
        try {
            FileReader reader = new FileReader("players.json");
            JsonReader jsonReader = new JsonReader(reader);
            jsonReader.setLenient(true);
            players[0] = gson.fromJson(jsonReader, Player.class);
            players[1] = gson.fromJson(jsonReader, Player.class);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return players;
    }
}
