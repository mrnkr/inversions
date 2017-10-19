/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obligatorio1;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author - Darío Dathaguy - Programación 2 - Número de estudiante: 220839 - Universidad ORT 
 * @author - Álvaro Nicoli - Programación 2 - Número de estudiante: 220159 - Universidad ORT
 */

public class MySystem {
    private  ArrayList<Player> playerlist = new ArrayList<>();
    
    public void setPlayerList(ArrayList<Player> players){
        this.playerlist = players;
    }
    
    public  ArrayList<Player> getPlayerList(){
        Collections.sort(this.playerlist);
        return playerlist;
    }
    
    public boolean hasPlayers(){
        return !this.getPlayerList().isEmpty();
    }
}
