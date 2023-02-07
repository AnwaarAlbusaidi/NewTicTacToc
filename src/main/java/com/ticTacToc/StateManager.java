package com.ticTacToc;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;

public class StateManager {

    Board currentBoard = new Board();

    public void saveBoardToFile(Board currentBoard) {
        Gson gson = new Gson();
        String json = gson.toJson(currentBoard.getGrid());
        try (FileWriter writer = new FileWriter("grid.json")) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
