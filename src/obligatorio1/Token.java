/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obligatorio1;

/**
 *
 * @author - Darío Dathaguy - Programación 2 - Número de estudiante: 220839 - Universidad ORT 
 * @author - Álvaro Nicoli - Programación 2 - Número de estudiante: 220159 - Universidad ORT
 */
public class Token {
    Player owner;
    boolean type = true; // true = Tower - false = Bishop
    int x;
    int y;
      
    public Token(Player player, int x, int y) {
        this.setOwner(player);
        this.x = x;
        this.y = y;
    }
    
    //Métodos Set
    public void setOwner(Player player) {
        owner = player;
    }
    
    public void setType(boolean type) {
        this.type = type;
    }

    
    //Métodos Get
    public Player getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return type ? "T" : "A";
    }
}
