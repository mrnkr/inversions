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
    Player player1;
    Player player2;
    String status; // Whose turn it is
    Token[][] grid;
    private ArrayList<String> history = new ArrayList<>(); // Store previous moves
    
    //Constructor
    public Game(Player player1, Player player2, int size) {
        this.setPlayer1(player1);
        this.setPlayer2(player2);
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
 
    public String getPrintableGrid() {
        String retVal = "";
        
        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[i].length; j++) {
                if ((i == 0 || i == this.grid.length - 1) && j == (this.grid.length - 1) / 2) {
                    retVal += "***\n*" + (this.grid[i][j] != null ? this.grid[i][j] : " ") + "*\n***";
                } else {
                    retVal += "+-+\n|" + (this.grid[i][j] != null ? this.grid[i][j] : " ") + "|\n+-+";
                }
            }
        }
        
        return retVal;
    }
}
