package com.ticTacToc;

public class Player {
    String name;
    Symbol playerSymbol;

    public Player(String name,Symbol playerSymbol)
    {
        this.name = name;
        this.playerSymbol = playerSymbol;
    }

    public void setName(String newName)
    {
        this.name = newName;
    }

    public void setSymbol(Symbol playerSymbol)
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
