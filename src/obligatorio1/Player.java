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
public class Player implements Comparable<Player> {
    private String name;
    private String alias;
    private int gamesPlayed;
    private String color;
    private int wins;
    private int draws;
    private int edad;
    private boolean isTurn;
   

    public Player(String name, String alias, int edad) {
        this.setName(name);
        this.setAlias(alias);
        this.setEdad(edad);
    }

    //Métodos Set
    public void setName(String name) {
        this.name = name;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
    
     public void setEdad(int edad) {
        this.edad = edad;
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
    
    public void addGamesPlayed() {
        this.gamesPlayed++;
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
    
    public int getEdad() {
        return edad;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getWins() {
        return wins;
    }
    
    public int getDraws() {
        return draws;
    }
    
    public String getColor() {
        return color;
    }
    
    public boolean isPlaying() {
        return isTurn;
    }

    
    //Show players
    @Override
    public String toString() {
            return "Alias: " + this.getAlias() + " Wins: " + this.getWins()
                + " Draws " + this.getDraws()
                + " Losses " + (this.getGamesPlayed()-(this.getWins()+this.getDraws()))
                + " Total games " + this.getGamesPlayed();
    }
    
    //Redefine equals, 2 players are the same player if both have the same alias.
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
    
    
    public int compareTo(Player p) {
       int retVal=0;
        if (this.getWins() > p.getWins()) {
                retVal = 1;
            }
            if (this.getWins() > p.getWins()) {
                retVal = -1;
            }
          return retVal;
        }

        
    


    
    
   
}
