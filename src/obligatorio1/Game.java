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
    
    Player player1;
    Player player2;
    String status; // Whose turn it is
    Token[][] grid;
    private ArrayList<String> history = new ArrayList<>(); // Store previous moves
    
    //Constructor
    public Game(Player player1, Player player2, int size) {
        this.setPlayer1(player1);
        this.player1.setColor(ANSI_RED);
        this.setPlayer2(player2);
        this.player2.setColor(ANSI_BLUE);
        this.grid = new Token[size][size];
        this.prepareGrid(player1, player2);
        this.setStatus("Player 1's Turn");
        
    }
    
    public void prepareGrid(Player player1, Player player2) {
        for (int i = 0; i < this.grid.length; i++) {
            this.grid[0][i] = new Token(player1, 0, i);
            this.grid[this.grid.length - 1][i] = new Token(player2, this.grid.length - 1, i);
        }
    }
    
    public void setPlayer1(Player p1) {
        this.player1 = p1;
    }

    public void setPlayer2(Player p2) {
        this.player2 = p2;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public String getStatus() {
        return status;
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
                String retValWithoutColor = retVal.replace(ANSI_RESET, "");
                retValWithoutColor = retValWithoutColor.replace(ANSI_RED, "");
                retValWithoutColor = retValWithoutColor.replace(ANSI_BLUE, "");
                retValWithoutColor = retValWithoutColor.replace(ANSI_GREEN, "");
                
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
    
    public void moveToken(int curX, int curY, int newX, int newY) {
        // Validate move
        Token aux = this.grid[curX][curY];
        this.grid[curX][curY] = null;
        this.grid[newX][newY] = aux;
    }
}
