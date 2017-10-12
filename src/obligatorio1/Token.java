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
    boolean type = false; // true = Tower - false = Bishop
    int x;
    int y;
      
    public Token(Player player, int x, int y) {
        this.setOwner(player);
        this.setPosition(x, y);
    }
    
    //Métodos Set
    public void setOwner(Player player) {
        owner = player;
    }
    
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        this.type = !this.type;
    }

    
    //Métodos Get
    public Player getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return this.owner.getColor() + (type ? "T" : "A");
    }
}
