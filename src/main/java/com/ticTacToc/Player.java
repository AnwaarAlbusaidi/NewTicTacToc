package com.ticTacToc;

public class Player {
    String name;
    Symbol playerSymbol;

    public void setName(String name) {
        this.name = name;
    }

    public void setPlayerSymbol(Symbol playerSymbol) {
        this.playerSymbol = playerSymbol;
    }

    public Player(String name, Symbol playerSymbol)
    {
        this.name = name;
        this.playerSymbol = playerSymbol;
    }
    public Player(Symbol playerSymbol)
    {
        this.playerSymbol = playerSymbol;
    }

    public String getName() {
        return this.name;
    }

    public Symbol getSymbol() {
        return this.playerSymbol;
    }


}
