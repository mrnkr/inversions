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
public class Player {
    private String name;
    private String alias;
    private int gamesPlayed;
    private String color;
    private int wins;
    private int draws;
    private boolean isTurn;
   

    public Player(String name, String alias) {
        this.setName(name);
        this.setAlias(alias);
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
    
    public void toggleTurn() {
        this.isTurn = !this.isTurn;
    }
    
    public void addWin() {
        this.wins++;
    }
    
    public void addDraw() {
        this.draws++;
    }
    
    //Métodos Get
    public String getName() {
        return name;
    }

    public String getAlias() {
        return alias;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getWins() {
        return wins;
    }
    
    public String getColor() {
        return color;
    }
    
    public boolean isPlaying() {
        return isTurn;
    }

    
    //Mostrar instancias
    @Override
    public String toString() {
        return "El nombre del jugador: " + this.getName() + "\nSu alias es: " + this.getAlias()
                + "\nSu cantidad de partidas es:" + this.getGamesPlayed()
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
