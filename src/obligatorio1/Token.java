/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obligatorio1;

/**
 *
 * @author Dario
 */
public class Token {
    Player owner;
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

    
    //Métodos Get
    public Player getOwner() {
        return owner;
    }


}
