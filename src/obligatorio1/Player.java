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
public class Player {
    private String name;
    private String alias;
    private int matches;
    private String color;
    private int wins;
   

    //Constructores
    public Player() {
        this.setName("Sin nombre");
        this.setAlias("none");
        this.setColor("Sin color");
        matches = 0;
        wins = 0;
        
    }

    public Player(String name, String alias) {
        this.setName(name);
        this.setAlias(alias);
        matches = 0;
        wins = 0;
    }

    //Métodos Set
    public void setName(String name) {
        this.name = name;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
    
    //Métodos Get
    public String getName() {
        return name;
    }

    public String getAlias() {
        return alias;
    }

    public int getMatches() {
        return matches;
    }

    public int getWins() {
        return wins;
    }
    
    public String GetColor() {
        return color;
    }

    
    //Mostrar instancias
    @Override
    public String toString() {
        return "El nombre del jugador: " + this.getName() + "\nSu alias es: " + this.getAlias()
                + "\nSu cantidad de partidas es:" + this.getMatches()
                + "\nSu cantidad de victorias es: " + this.getWins();
                
    }
    
    //Redefinir equals.  Un árticulo es igual a otro si tienen el mismo código
    @Override
    public boolean equals(Object o) {
        boolean retVal;
        try{
        Player tmplayer = (Player) o;
        retVal = this.getAlias().equalsIgnoreCase(tmplayer.getAlias());
           }catch(ClassCastException e){
                retVal = false;
                }
        return retVal;
    }
}
