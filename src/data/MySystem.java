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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author - Darío Dathaguy - Programación 2 - Número de estudiante: 220839 - Universidad ORT 
 * @author - Álvaro Nicoli - Programación 2 - Número de estudiante: 220159 - Universidad ORT
 */

public class MySystem implements Serializable {
    private static final transient String SAVE_FILE = "/tmp/inversions-save-data.txt";
    
    private ArrayList<Player> playerList;
    private Game game;
    private transient boolean isMusicPlaying;
    
    /**
     * The constructor will attempt to load a saved instance before creating a new one
     */
    public MySystem() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(SAVE_FILE))) {
            MySystem sys = (MySystem) in.readObject();
            this.playerList = sys.playerList;
            this.game = sys.game;
        } catch (Exception e) {
            this.playerList = new ArrayList<>();
        }
        
        this.isMusicPlaying = true;
    }
    
    public void startNewGame(Player player1, Player player2, int gridSize) {
        this.game = new Game(player1, player2, gridSize);
    }
    
    /**
     * Returns the current game instance
     * @return - The game if there is an ongoing one - else null
     */
    public Game getRunningGame() {
        if (this.game != null) {
            if (!this.game.isPlaying()) {
                this.game = null;
            }
        }
        
        return this.game;
    }
    
    public boolean addPlayer(Player player) {
        if (this.playerList.contains(player)) {
            return false;
        }
        
        this.playerList.add(player);
        return true;
    }
    
    public ArrayList<Player> getPlayerList(){
        Collections.sort(this.playerList);
        return playerList;
    }
    
    public boolean hasPlayers(){
        return !this.getPlayerList().isEmpty();
    }
    
    public void toggleMusicPlaying() {
        this.isMusicPlaying = !this.isMusicPlaying;
    }
    
    public boolean getIsMusicPlaying() {
        return this.isMusicPlaying;
    }
    
    /**
     * Saves the instance of MySystem to a txt file
     * @return - Whether the MySystem instance could successfully be saved
     */
    public boolean saveGame() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(SAVE_FILE))) {
            out.writeObject(this);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
