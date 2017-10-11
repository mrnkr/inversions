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
public class Chip {
    Player owner;
    int positionX;
    int positionY;
      
    public Chip(Player unplayer, int posicionx, int posiciony) {
        this.setOwner(unplayer);
    
        
    }
    
    //Métodos Set
    public void setOwner(Player unplayer) {
        owner = unplayer;
    }

    
    //Métodos Get
    public Player getOwner() {
        return owner;
    }


}
