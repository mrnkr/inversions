/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obligatorio1;

import java.util.ArrayList;

/**
 *
 * @author - Darío Dathaguy - Programación 2 - Número de estudiante: 220839 - Universidad ORT 
 * @author - Álvaro Nicoli - Programación 2 - Número de estudiante: 220159 - Universidad ORT
 */
public class Game {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    
    private Player player1;
    private Player player2;
    private Token[][] grid;
    private boolean finished;
    private boolean canEnd;
    private ArrayList<String> history = new ArrayList<>(); // Store previous moves
    
    public Game(Player player1, Player player2, int size) {
        this.player1 = player1;
        this.player1.setColor(ANSI_RED);
        this.player1.toggleTurn();
        this.player2 = player2;
        this.player2.setColor(ANSI_BLUE);
        this.grid = new Token[size][size];
        this.prepareGrid(player1, player2);
    }
    
    /**
     * Populate the grid with the tokens
     * @param player1 - Player 1
     * @param player2 - Player 2
     */
    public void prepareGrid(Player player1, Player player2) {
        for (int i = 0; i < this.grid.length; i++) {
            this.grid[0][i] = new Token(player1, 0, i);
            this.grid[this.grid.length - 1][i] = new Token(player2, this.grid.length - 1, i);
        }
    }
    
    /**
     * Takes the String coordinates of the tokens to move and makes the corresponding move if valid
     * @param input - The user inputted move
     * @throws Exception - The move is invalid
     */
    public void inputMove(String input) throws Exception {
        String historyInput = input;
        
        input = input.replace(" ", "");
        int curY = charToInt(input.charAt(0));
        int curX = this.grid.length - Integer.parseInt(String.valueOf(input.charAt(1)));
        int newY = charToInt(input.charAt(2));
        int newX = this.grid.length - Integer.parseInt(String.valueOf(input.charAt(3)));
        
        moveToken(curX, curY, newX, newY);
        this.history.add(historyInput); // This line will only be reached if the move is valid, else an exception will have been thrown
    }
    
    /**
     * Changes the game status to finished and ends the ongoing turn
     * @param winner - The player who should be given the game
     */
    public void endGame(Player winner) {
        // End the turn of whoever is playing
        if (this.player1.isPlaying()) {
            this.player1.toggleTurn();
        } else {
            this.player2.toggleTurn();
        }
        
        if (winner != null) {
            winner.addWin(); // Add the victory to whoever won the game
            
            // Also log the loss to the other player
            if (this.player1.equals(winner)) {
                this.player2.addLoss();
            } else {
                this.player1.addLoss();
            }
        } else {
            // If the winner is null it means the game is a draw
            this.player1.addDraw();
            this.player2.addDraw();
        }
        
        this.finished = true;
    }
    
    /**
     * Will end the game with a victory of the player whose turn it is not
     */
    public void surrender() {
        if (this.player1.isPlaying()) {
            endGame(this.player2);
        } else {
            endGame(this.player1);
        }
    }
    
    /**
     * Will end the game as a draw
     */
    public void draw() {
        endGame(null);
    }
    
    /**
     * Generates a String with information on whose turn it is
     * @return - Human readable turn information
     */
    public String getTurnStatus() {
        if (this.player1.isPlaying()) {
            return "Es turno de " + this.player1.getColor() + this.player1.getAlias() + ANSI_RESET;
        } else {
            return "Es turno de " + this.player2.getColor() + this.player2.getAlias() + ANSI_RESET;
        }
    }
    
    /**
     * Returns whether the game is ongoing
     * @return - If the game is still on
     */
    public boolean isPlaying() {
        return !this.finished;
    }
    
    /**
     * Determines if the game has been won by someone
     * @return - The winner
     */
    public Player hasWinner() {
        Player retVal = checkCheck();
        
        if (retVal == null) {
            if (!stillHasTokensLeft(this.player1)) {
                retVal = this.player2;
            } 
            
            if (!stillHasTokensLeft(this.player2)) {
                retVal = this.player1;
            }
            
            if (retVal == null) {
                if (getPossibleMoveList(this.player1).length() == 0 && this.player1.isPlaying()) {
                    retVal = this.player2;
                } 
                
                if (getPossibleMoveList(this.player2).length() == 0 && this.player2.isPlaying()) {
                    retVal = this.player1;
                }
            }
        }
        
        // Makes sure the player who is losing has a chance to deffend themselves before losing
        if (!canEnd) {
            if (retVal != null) {
                retVal = null;
                canEnd = !canEnd;
            }
        } else {
            if (retVal == null) {
                canEnd = !canEnd;
            }
        }
        
        return retVal;
    }
    
    /**
     * Returns true if player that is passed still has tokens left in the grid
     * @param player - The player
     * @return - Still has tokens?
     */
    public boolean stillHasTokensLeft(Player player) {
        boolean retVal = false;
        
        for (int i = 0; i < this.grid.length && !retVal; i++) {
            for (int j = 0; j < this.grid[i].length && !retVal; j++) {
                try {
                    if (this.grid[i][j].getOwner().equals(player)) {
                        retVal = true;
                    }
                } catch (Exception e) {
                    // Will enter here when there is no token in the 
                }
            }
        }
        
        return retVal;
    }
    
    /**
     * Check if someone is in a check position
     * @return - The player in check position
     */
    public Player checkCheck() {
        Player retVal = null;
        
        if (grid.length == 5) {
            try {
                if (grid[4][2].getOwner().equals(this.player1)) {
                    retVal = this.player1;
                }
            } catch (Exception e) {

            }

            try {
                if (grid[0][2].getOwner().equals(this.player2)) {
                    retVal = this.player2;
                }
            } catch (Exception e) {

            }
        } else {
            try {
                if (grid[2][1].getOwner().equals(this.player1)) {
                    retVal = this.player1;
                }
            } catch (Exception e) {

            }

            try {
                if (grid[0][1].getOwner().equals(this.player2)) {
                    retVal = this.player2;
                }
            } catch (Exception e) {

            }
        }

        return retVal;
    }
    
    /**
     * Will list all possible moves for a player
     * @param player - the player to check for possible moves
     * @return - Moves
     */
    public String getPossibleMoveList(Player player) {
        String retVal = "";
        
        ArrayList<String> regularMoves = new ArrayList<>();
        ArrayList<String> defensiveMoves = new ArrayList<>();
        ArrayList<String> offensiveMoves = new ArrayList<>();
        
        String move = "";
        
        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[i].length; j++) {
                if (this.grid[i][j] != null) {
                    if (this.grid[i][j].getOwner().equals(player)) {
                        for (int m = 0; m < this.grid.length; m++) {
                            for (int n = 0; n < this.grid[m].length; n++) {
                                if (this.isMoveValid(this.grid[i][j], i, j, m, n)) {
                                    int val1 = j + 65;
                                    int val2 = n + 65;
                                    char a = (char) val1;
                                    char b = (char) val2;
                                    move = a + String.valueOf(this.grid.length - i) + " " + b + String.valueOf(this.grid.length - m) + "\n".toUpperCase();
                                    regularMoves.add(move);
                                    String aux = move.trim().substring(2);

                                    if (this.player1.isPlaying()) {
                                        if (this.grid.length == 3) {

                                            if (aux.equals(" B1")) {
                                                offensiveMoves.add(move);
                                                regularMoves.remove(move);
                                            }
                                            if (aux.equals(" B3")) {
                                                defensiveMoves.add(move);
                                                regularMoves.remove(move);
                                            }
                                        }
                                        if (this.grid.length == 5) {
                                            if (aux.equals(" C1")) {
                                                offensiveMoves.add(move);
                                                regularMoves.remove(move);
                                            }
                                            if (aux.equals(" C5")) {
                                                defensiveMoves.add(move);
                                                regularMoves.remove(move);
                                            }
                                        }
                                        if (this.player2.isPlaying()) {
                                            if (this.grid.length == 3) {
                                                if (aux.equals(" B3")) {
                                                    offensiveMoves.add(move);
                                                    regularMoves.remove(move);
                                                }
                                                if (aux.equals(" B1")) {
                                                    defensiveMoves.add(move);
                                                    regularMoves.remove(move);
                                                }
                                            }
                                            if (this.grid.length == 5) {
                                                if (aux.equals(" C5")) {
                                                    offensiveMoves.add(move);
                                                    regularMoves.remove(move);
                                                }
                                                if (aux.equals(" C1")) {
                                                    defensiveMoves.add(move);
                                                    regularMoves.remove(move);
                                                }
                                            }

                                        }
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }

        if (!defensiveMoves.isEmpty()) {
            retVal += "Movimientos de defensa\n";
            for (int i = 0; i < defensiveMoves.size(); i++) {
                retVal += defensiveMoves.get(i);
            }
        }
        if (!offensiveMoves.isEmpty()) {
            retVal += "Movimientos de ataque\n";
            for (int i = 0; i < offensiveMoves.size(); i++) {
                retVal += offensiveMoves.get(i);
            }
        }
        if (!regularMoves.isEmpty()) {
            retVal += "Movimientos normales\n";
            for (int i = 0; i < regularMoves.size(); i++) {
                retVal += regularMoves.get(i);
            }
        }
        
        return retVal;
    }
 
    /**
     * Generates a String object to present the grid to the user by means of the terminal
     * @param rotate - If the grid should be rotated
     * @return - The beautiful grid
     */
    public String getPrintableGrid(boolean rotate) {
        String retVal = ""; // Value to be returned
        Token [][] grid = rotate ? rotateGrid() : this.grid;
        boolean addingElements = false; // Whether the row being added to retVal contains Tokens or only divider characters
        
        for (int i = 0; i < grid.length; i++) {
            // Determines whether the row being added contains part of a goal
            // If i == 4 the behavior will always be the same, if i == 0 or 1 the behavior is the same in case of the divider
            boolean hasGoal = i == 0 || (i == 1 && !addingElements) || i == grid.length - 1;
            
            for (int j = 0; j < grid[i].length; j++) {
                // Determines whether the horizontal position corresponds to that of the goal
                boolean isGoal = hasGoal && j >= (grid[i].length / 2) && j < ((grid[i].length / 2) + 2);
                
                if (addingElements) {
                    if (j == 0) {
                        if (rotate) {
                            retVal += i + 1 + " ";
                        } else {
                            retVal += grid.length - i + " ";
                        }
                    }
                    
                    retVal += (isGoal ? (ANSI_GREEN + "*" + ANSI_RESET) : "|") + (grid[i][j] != null ? (grid[i][j] + ANSI_RESET) : " ");
                } else {
                    if (j == 0) {
                        retVal += "  ";
                    }
                    
                    retVal += isGoal ? ((j == (grid[i].length / 2) + 1) ? (ANSI_GREEN + "*" + ANSI_RESET + "-") : (ANSI_GREEN + "**" + ANSI_RESET)) : "+-";
                }
            }
            
            if (addingElements) {
                // Finishes a line with Tokens
                retVal += "|\n";
                
                // Subtracts one to the vertical index in order to add elements correctly
                if (i == grid.length - 1) {
                    i--;
                }
            } else {
                // Finishes a divider line
                retVal += "+\n";
                
                // Remove escape characters from retVal and store the clean String in memory
                // Doing this in order for java not to get confused when calculating the length of the String
                // Since it counts the color escape chars as regular chars
                String retValWithoutColor = removeColorFromString(retVal);
                
                // Subtracts one to the vertical index in order to add the last divider line
                if ((this.grid.length == 5 && retValWithoutColor.length() < (11 * 11 + 22)) || (this.grid.length == 3 && retValWithoutColor.length() < (7 * 7 + 14))) {
                    i--;
                }
            }
            
            // Change addingElements to its opposite value
            addingElements = !addingElements;
        }
        
        if (rotate) {
            retVal += grid.length == 5 ? "   E D C B A\n" : "   C B A\n";
        } else {
            retVal += grid.length == 5 ? "   A B C D E\n" : "   A B C\n";
        }
        
        return retVal;
    }
    
    public Token[][] rotateGrid() {
        Token[][] retVal = new Token[this.grid.length][this.grid.length];
        
        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[i].length; j++) {
                retVal[this.grid.length - i - 1][this.grid[i].length - j - 1] = this.grid[i][j];
            }
        }
        
        return retVal;
    }
    
    /**
     * Checks if a move is valid and commits it to the grid
     * @param curX - Current X axis position of the token to move
     * @param curY - Current Y axis position of the token to move
     * @param newX - New X axis position of the token to move
     * @param newY - New Y axis position of the token to move
     * @throws Exception - If the move is invalid or the any position passed is invalid
     */
    private void moveToken(int curX, int curY, int newX, int newY) throws Exception {
        Token aux = this.grid[curX][curY];
        
        if (isMoveValid(aux, curX, curY, newX, newY)) {
            aux.setPosition(newX, newY);
            
            this.grid[curX][curY] = null;
            this.grid[newX][newY] = aux;
            
            endRound();
        } else {
            throw new Exception("Invalid Move Exception");
        }
    }
    
    /**
     * Validates a move
     * @param token - The token to move
     * @param curX - Current X axis position of the token to move
     * @param curY - Current Y axis position of the token to move
     * @param newX - New X axis position of the token to move
     * @param newY - New Y axis position of the token to move
     * @return - If the move is valid
     */
    private boolean isMoveValid(Token token, int curX, int curY, int newX, int newY) {
        boolean retVal;
        boolean notMoving = curX == newX && curY == newY;
        boolean isMoveDiagonal = curX != newX && curY != newY && Math.abs(newX-curX) == Math.abs(newY-curY);
        
        if (removeColorFromString(token.toString()).equals("T")) {
            // Make sure move is either vertical or horizontal and that the token didnt go over any other token
            retVal = (!isMoveDiagonal && checkLine(curX, curY, newX, newY));
        } else {
            // Make sure move is diagonal and that the token didnt go over any other token
            retVal = (isMoveDiagonal && checkDiagonal(curX, curY, newX, newY));
        }
        
        // If the move is valid and the token that is being moved belongs to the player that is playing return true
        return (!notMoving && retVal && isDestinationValid(newX, newY) && token.getOwner().isPlaying());
    }
    
    /**
     * Verifies a token does not go over others as it moves though a horizontal or vertical line
     * @param curX - Current X axis position of the token to move
     * @param curY - Current Y axis position of the token to move
     * @param newX - New X axis position of the token to move
     * @param newY - New Y axis position of the token to move
     * @return - The line to go over is clean
     */
    private boolean checkLine(int curX, int curY, int newX, int newY) {
        boolean lineIsEmpty = true;
        boolean horizontal = curX == newX; // Used to determine whether to check a horizontal line
        boolean vertical = curY == newY; // Used to determine whether to check a vertical line
        
        int curPos = curX == newX ? curY : curX;
        int newPos = curX == newX ? newY : newX;
        
        if (curPos > newPos) {
            int aux = newPos;
            newPos = curPos;
            curPos = aux;
        }
        
        for (int i = curPos + 1; i < newPos && lineIsEmpty; i++) {
            if (horizontal) {
                lineIsEmpty = lineIsEmpty ? this.grid[curX][i] == null : lineIsEmpty;
            } else if (vertical) {
                lineIsEmpty = lineIsEmpty ? this.grid[i][curY] == null : lineIsEmpty;
            } else {
                // This will happen when the move was being evaluated
                // as a line only because it wasn't diagonal
                // even if it wasn't a straight line either
                lineIsEmpty = false;
            }
        }
        
        return lineIsEmpty;
    }
    
    /**
     * Verifies a token does not go over others as it moves through a diagonal line
     * @param curX - Current X axis position of the token to move
     * @param curY - Current Y axis position of the token to move
     * @param newX - New X axis position of the token to move
     * @param newY - New Y axis position of the token to move
     * @return - The line to go over is clean
     */
    private boolean checkDiagonal(int curX, int curY, int newX, int newY) {
        boolean diagonalIsEmpty = true;
        Token origin = this.grid[curX][curY];
        
        while (curX != newX && curY != newY) {
            if (!origin.equals(this.grid[curX][curY])) {
                diagonalIsEmpty = diagonalIsEmpty ? this.grid[curX][curY] == null : diagonalIsEmpty;
            }
            
            if (curX > newX) {
                curX--;
            } else {
                curX++;
            }
            
            if (curY > newY) {
                curY--;
            } else {
                curY++;
            }
        }
        
        return diagonalIsEmpty;
    }
    
    /**
     * Will determine whether the destination of a token is valid
     * Any destination is valid if there is no token there
     * A token can overtake another if such token does not belong to the same player and it is in goal
     * @param newX - Destination X pos
     * @param newY - Destination Y pos
     * @return - Move has valid destination?
     */
    private boolean isDestinationValid(int newX, int newY) {
        boolean retVal = true;
        
        if (this.grid[newX][newY] != null) {
            // If the owner is playing then the move is not valid
            // You cannot remove your own tokens
            if (this.grid[newX][newY].getOwner().isPlaying()) {
                retVal = false;
            } else {
                // To remove the other player's tokens, those have to be in your goal
                if (this.player1.isPlaying()) {
                    // Check the goal up top
                    // If the destination is not the goal then the destination is invalid
                    retVal = !(newX != 0 || newY != (this.grid.length - 1) / 2);
                } else {
                    // Check the goal down below
                    // If the destionation is not the goal then the destination is invalid
                    retVal = !(newX != this.grid.length - 1 || newY != (this.grid.length - 1) / 2);
                }
            }
        }
        
        return retVal;
    }
    
    public String getPrintableHistory() {
        String retVal = "";

        for (int i = history.size() - 1; i >= 0; i--) {
            retVal += history.get(i) + "\n";
        }

        return retVal;
    }
    
    /**
     * Finishes a player's turn and starts the other's
     */
    private void endRound() {
        this.player1.toggleTurn();
        this.player2.toggleTurn();
    }
    
    /**
     * Transforms the part of the coordinates input as char into a workable int
     * @param c - the letter coordinate
     * @return - The index to use in the array
     */
    private int charToInt(char c) {
        return ((int) c) - 65;
    }
    
    /**
     * Removes the color escape characters from a String
     * @param text - The text to remove the escape chars from
     * @return - The clean String
     */
    private String removeColorFromString(String text) {
        String retVal = text.replace(ANSI_RESET, "");
        retVal = retVal.replace(ANSI_RED, "");
        retVal = retVal.replace(ANSI_BLUE, "");
        retVal = retVal.replace(ANSI_GREEN, "");
        
        return retVal;
    }
}
