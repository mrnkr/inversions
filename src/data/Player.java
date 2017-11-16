/*
 * The MIT License
 *
 * Copyright 2017 MrNKR.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package data;

import java.io.Serializable;

/**
 *
 * @author - Darío Dathaguy - Programación 2 - Número de estudiante: 220839 - Universidad ORT 
 * @author - Álvaro Nicoli - Programación 2 - Número de estudiante: 220159 - Universidad ORT
 */
public class Player implements Comparable<Player>, Serializable {
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
    
    public int getAge() {
        return age;
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

    @Override
    public String toString() {
        return "Alias: " + this.alias
                + "\n\tVictorias: " + this.wins
                + "\n\tEmpates: " + this.draws
                + "\n\tDerrotas: " + this.getLosses()
                + "\n\tPartidas jugadas: " + this.gamesPlayed;
    }

    @Override
    public boolean equals(Object o) {
        boolean retVal = false;
        
        if (o instanceof Player) {
            Player player = (Player) o;
            retVal = this.getAlias().equalsIgnoreCase(player.getAlias());
        }
        
        return retVal;
    }

    @Override
    public int compareTo(Player p) {
        int retVal;
        
        if (this.wins < p.getWins()) {
            retVal = 1;
        } else if (this.wins > p.getWins()) {
            retVal = -1;
        } else {
            if (this.draws < p.getDraws()) {
                retVal = 1;
            } else if (this.draws > p.getDraws()) {
                retVal = -1;
            } else {
                if (this.gamesPlayed < p.getGamesPlayed()) {
                    retVal = 1;
                } else if (this.gamesPlayed > p.getGamesPlayed()) {
                    retVal = -1;
                } else {
                    retVal = 0;
                }
            }
        }
        
        return retVal;
    }
}
