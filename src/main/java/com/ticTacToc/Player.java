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

    public void makeMove(Board board) {
        String input;
        int row, col;
        System.out.println("Player " + this.name + ", enter row and column (0-" + (board.getSize() - 1) + ") separated by space or 'q' to quit: " + this.playerSymbol.getPlayerSymbol());
        input =  inputManger.getUserChoiceString();
        if (input.equals("q")) {
            System.out.println("Player " + this.name + " has exited the game");
            System.exit(0);
        }
            String[] inputArray = input.split(" ");
            row = Integer.parseInt(inputArray[0]);
            col = Integer.parseInt(inputArray[1]);

            if (row < 0 || row >= board.getSize() || col < 0 || col >= board.getSize()) {
                System.out.println("Invalid position! Please enter a position within the range of the board.");
            } else if (board.check(board.getGrid()[row][col])) {
                 System.out.println("Player " + this.name + ", enter row and column (0-" + (board.getSize() - 1) + ") separated by space: " + this.playerSymbol.getPlayerSymbol());
                input =  inputManger.getUserChoiceString();
                String[] inputArray2 = input.split(" ");
                row = Integer.parseInt(inputArray2[0]);
                col = Integer.parseInt(inputArray2[1]);
                board.getGrid()[row][col].setPlayerSymbol(this.playerSymbol.getPlayerSymbol());
            } else {
                board.getGrid()[row][col] = this.playerSymbol;
            }
        }

    /**
     An object of UserInputHandler class to get user input.
     */
    UserInputHandler inputManger = new UserInputHandler();
}
