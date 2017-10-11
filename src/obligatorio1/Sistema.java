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
 *         - Álvaro Nicoli - Programación 2 - Número de estudiante: ?????? - Universidad ORT
 */
public class Sistema {
private  ArrayList<Player> playerlist;

 //Métodos set y get
    public void setPlayerList(ArrayList<Player> lista){
        playerlist = lista;
    }
    
    public  ArrayList<Player> getPlayerList(){
        return playerlist;
    }
    
     //Métodos hay, estos indican si hay Jugadores
    public boolean hasPlayers(){
        return !this.getPlayerList().isEmpty();
    }
}
