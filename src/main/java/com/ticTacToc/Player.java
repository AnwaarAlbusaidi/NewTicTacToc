package com.ticTacToc;

public class Player {
    String name;
    Symbol playerSymbol;
    Symbol[] symbols = new Symbol[] {new Symbol('x'), new Symbol('o')};
    static int symbolIndex = 0;
    public void setName(String name) {
        this.name = name;
    }

    public Symbol pickSymbol()
    {
        System.out.println(this.name + ", your symbol is: " + symbols[symbolIndex].getPlayerSymbol());
        Symbol selectedSymbol = symbols[symbolIndex];
        symbolIndex = (symbolIndex + 1) % symbols.length;
        return selectedSymbol;
    }

    public void setPlayerSymbol(Symbol playerSymbol) {
        this.playerSymbol = playerSymbol;
    }

    public Player(String name, Symbol playerSymbol)
    {
        this.name = name;
        this.playerSymbol = playerSymbol;
    }

    public String getName() {
        return this.name;
    }

    public Symbol getSymbol() {
        return this.playerSymbol;
    }


}
