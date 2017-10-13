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
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_BLUE = "\u001B[34m";
    
    private Player player1;
    private Player player2;
    private Token[][] grid;
    private boolean finished;
    private ArrayList<String> history;
    
    public Game(Player player1, Player player2, int size) {
        this.player1 = player1;
        this.player1.setColor(ANSI_RED);
        this.player1.toggleTurn();
        this.player2 = player2;
        this.player2.setColor(ANSI_BLUE);
        this.grid = new Token[size][size];
        this.prepareGrid(player1, player2);
        this.history = new ArrayList<>();
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
        input = input.replace(" ", "");
        int curY = charToInt(input.charAt(0));
        int curX = this.grid.length - Integer.parseInt(String.valueOf(input.charAt(1)));
        int newY = charToInt(input.charAt(4));
        int newX = this.grid.length - Integer.parseInt(String.valueOf(input.charAt(5)));
        
        moveToken(curX, curY, newX, newY);
    }
    
    /**
     * Makes the player whose turn is ongoing to forfeit the game
     * Remember to ask if opponent accepts before use
     */
    public void endGame() {
        if (this.player1.isPlaying()) {
            this.player2.addWin();
        } else {
            this.player1.addWin();
        }
        
        this.finished = true;
    }
    
     /**
     * Makes the player whose turn is ongoing to make a draw call on the game
     * Remember to ask if opponent accepts before use
     */
    public void draw() {
        player1.addDraw();
        player2.addDraw();
        this.finished = true;
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
     * Returns true if player that is passed still has tokens left in the grid
     * @param player - The player
     * @return - Still has tokens?
     */
    public boolean stillHasTokensLeft(Player player) {
        boolean retVal = false;
        
        for (int i = 0; i < this.grid.length && !retVal; i++) {
            for (int j = 0; j < this.grid[i].length && !retVal; j++) {
                if (this.grid[i][j].getOwner().equals(player)) {
                    retVal = true;
                }
            }
        }
        
        return retVal;
    }
 
    /**
     * Generates a String object to present the grid to the user by means of the terminal
     * @return - The beautiful grid
     */
    public String getPrintableGrid() {
        String retVal = ""; // Value to be returned
        boolean addingElements = false; // Whether the row being added to retVal contains Tokens or only divider characters
        
        for (int i = 0; i < this.grid.length; i++) {
            // Determines whether the row being added contains part of a goal
            // If i == 4 the behavior will always be the same, if i == 0 or 1 the behavior is the same in case of the divider
            boolean hasGoal = i == 0 || (i == 1 && !addingElements) || i == this.grid.length - 1;
            
            for (int j = 0; j < this.grid[i].length; j++) {
                // Determines whether the horizontal position corresponds to that of the goal
                boolean isGoal = hasGoal && j >= (this.grid[i].length / 2) && j < ((this.grid[i].length / 2) + 2);
                
                if (addingElements) {
                    if (j == 0) {
                        retVal += this.grid.length - i + " ";
                    }
                    
                    retVal += (isGoal ? (ANSI_GREEN + "*" + ANSI_RESET) : "|") + (this.grid[i][j] != null ? (this.grid[i][j] + ANSI_RESET) : " ");
                } else {
                    if (j == 0) {
                        retVal += "  ";
                    }
                    
                    retVal += isGoal ? ((j == (this.grid[i].length / 2) + 1) ? (ANSI_GREEN + "*" + ANSI_RESET + "-") : (ANSI_GREEN + "**" + ANSI_RESET)) : "+-";
                }
            }
            
            if (addingElements) {
                // Finishes a line with Tokens
                retVal += "|\n";
                
                // Subtracts one to the vertical index in order to add elements correctly
                if (i == this.grid.length - 1) {
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
        
        retVal += this.grid.length == 5 ? "   A B C D E\n" : "   A B C\n";
        
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
        boolean isMoveDiagonal = curX != newX && curY != newY;
        
        if (removeColorFromString(token.toString()).equals("T")) {
            // Make sure move is either vertical or horizontal and that the token didnt go over any other token
            retVal = (!isMoveDiagonal && checkLine(curX, curY, newX, newY));
        } else {
            // Make sure move is diagonal and that the token didnt go over any other token
            retVal = (isMoveDiagonal && checkDiagonal(curX, curY, newX, newY));
        }
        
        // If the move is valid and the token that is being moved belongs to the player that is playing return true
        return (retVal && token.getOwner().isPlaying());
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
        boolean horizontal = curX == newX;
        
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
            } else {
                lineIsEmpty = lineIsEmpty ? this.grid[i][curY] == null : lineIsEmpty;
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
        
        if (curX > newX) {
            int aux = newX;
            newX = curX;
            curX = aux;
        }
        
        if (curY > newY) {
            int aux = newY;
            newY = curY;
            curY = aux;
        }
        
        for (int i = curX, j = curY; i < newX && j < newY && diagonalIsEmpty; i++, j++) {
            // Added this if clause to fix problems with moves to the bottom right
            if (!this.grid[i][j].equals(this.grid[curX][curY])) {
                diagonalIsEmpty = diagonalIsEmpty ? this.grid[i][j] == null : diagonalIsEmpty;
            }
        }
        
        return diagonalIsEmpty;
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
    
    /**
     * Check if the player who is moving is in check status.
     * @return - The boolean with the answer
     */
    private boolean checkCheck() {
        boolean retVal = false;
        if(this.player1.isPlaying()){
            if (grid.length == 5){
               if (grid [0][2].getOwner().equals(player2)){
                retVal = true;
               }
            }else{
               if (grid [0][1].getOwner().equals(player2))
                   retVal = true; 
            }
        }else{
           if(this.player2.isPlaying()){
            if (grid.length == 5){
               if (grid [4][2].getOwner().equals(player2)){
                retVal = true;
               }
            }else{
               if (grid [2][1].getOwner().equals(player2))
                   retVal = true; 
            }
           }
        }
        return retVal;
    }
    
      public ArrayList<String> getHistory(){
          return this.history;
      }
      
      public String printableHistory(){
          String retVal = "";
          
          for(int i = history.size()-1; i>0; i= i-2){
              retVal += history.get(i) + " - " + history.get(i-1) + "\n" ;
          }  
          return retVal;
      }
     
      public String hasMoves(){
         String retVal = "";
         if (player1.isPlaying()){
               for (int i=0; i<this.grid.length; i++){
           
         
          for (int j=0; j<this.grid[i].length; j++){
          
           if(this.grid[i][j].getOwner().equals(player1)){
                  for (int m=0; i<this.grid.length; i++){
           
         
          for (int n=0; j<this.grid[i].length; j++){
               if(this.isMoveValid(this.grid[i][j], i, j, m, n)){
                    retVal += String.valueOf(i) + String.valueOf(j) + "-" + String.valueOf(m) + String.valueOf(n);
                }
              
          }
                  }    
           }
          }
         
               }
         }
          return retVal;
      }
    }
      
