package com.ticTacToc;

public class Symbol {

    char playerSymbol;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Symbol symbol = (Symbol) obj;
        return this.playerSymbol == symbol.playerSymbol;
    }

    public Symbol(){
        this.playerSymbol = playerSymbol;
    }
    public char getPlayerSymbol() {
        return playerSymbol;
    }

    public void setPlayerSymbol(char playerSymbol) {
        this.playerSymbol = playerSymbol;
    }

    public boolean isSameSymbol(Symbol otherSymbol) {
        return this.playerSymbol == otherSymbol.playerSymbol;
    }

    public char getSymbol() {
        return  playerSymbol;
    }
}
