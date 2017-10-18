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
    private int age;
    private int gamesPlayed;
    private String color;
    private int wins;
    private int draws;
    private boolean isTurn;
   

    public Player(String name, String alias, int age) {
        this.name = name;
        this.alias = alias;
        this.age = age;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
    
    public void toggleTurn() {
        this.isTurn = !this.isTurn;
    }
    
    public void addWin() {
        this.gamesPlayed++;
        this.wins++;
    }
    
    public void addDraw() {
        this.gamesPlayed++;
        this.draws++;
    }
    
    public void addLoss() {
        this.gamesPlayed++;
    }
    
    // Getters
    
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
    
    public int getDraws() {
        return draws;
    }
    
    public int getLosses() {
        return this.gamesPlayed - (this.wins + this.draws);
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
        return "Alias: " + this.alias
                + "\n\tVictorias: " + this.wins
                + "\n\tEmpates: " + this.draws
                + "\n\tDerrotas: " + this.getLosses()
                + "\n\tPartidas jugadas: " + this.gamesPlayed;
    }

    //Redefine equals, 2 players are the same player if both have the same alias.
    @Override
    public boolean equals(Object o) {
        boolean retVal = false;
        
        if (o instanceof Player) {
            Player player = (Player) o;
            retVal = this.getAlias().equalsIgnoreCase(player.getAlias());
        }
        
        return retVal;
    }

    public int compareTo(Player p) {
        int retVal = 0;
        
        if (this.wins > p.getWins()) {
            retVal = 1;
        }
        
        if (this.wins > p.getWins()) {
            retVal = -1;
        }
        
        return retVal;
    }
}
