/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obligatorio1;

import java.util.ArrayList;

/**
 *
 * @author Dario
 */
public class Match {
    Player player1;
    Player player2;
    String status;
    int type;
    int[][] grid = new int[type][type];
    private  ArrayList<String> history;
    //Constructor
    public Match(Player p1, Player p2, int aType) {
        this.setPlayer1(p1);
        this.setType(aType);
        this.setStatus("Player1 Turn");
        
    }
     //Métodos Set
    public void setPlayer1(Player p1) {
        player1 = p1;
    }

    public void setPlayer2(Player p2) {
        player2 = p2;
    }
    
    public void setType(int aType) {
        type = aType;
    }
    
    public void setStatus(String aStatus) {
        status = aStatus;
    }
    //Métodos Get
    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public String getStatus() {
        return status;
    }
 
}
