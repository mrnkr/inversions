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

    public Player(String unnombre, String unalias) {
        this.setName(unnombre);
        this.setAlias(unalias);
        matches = 0;
        wins = 0;
    }

    //Métodos Set
    public void setName(String nom) {
        name = nom;
    }

    public void setAlias(String unalias) {
        alias = unalias;
    }
    
    public void setColor(String uncolor) {
        color = uncolor;
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
        boolean esigual;
        try{
        Player tmplayer = (Player) o;
        esigual = this.getAlias().equalsIgnoreCase(tmplayer.getAlias());
           }catch(ClassCastException e){
                esigual = false;
                }
        return esigual;
    }
}
