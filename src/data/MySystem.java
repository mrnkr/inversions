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

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author - Darío Dathaguy - Programación 2 - Número de estudiante: 220839 - Universidad ORT 
 * @author - Álvaro Nicoli - Programación 2 - Número de estudiante: 220159 - Universidad ORT
 */

public class MySystem implements Serializable {
    private static final transient String SAVE_FILE = "/tmp/inversions-save-data.txt";
    private static final transient String MUSIC_FILE = "/res/Local Forecast - Elevator.mp3";
    
    private ArrayList<Player> playerList;
    private Game game;
    
    private transient boolean isMusicPlaying;
    private transient JFXPanel myPanel;
    private transient Media media;
    private transient MediaPlayer mediaPlayer;
    
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
        
        try {
            this.myPanel = new JFXPanel();
            this.media = new Media(getClass().getResource(MUSIC_FILE).toURI().toString());
            this.mediaPlayer = new MediaPlayer(media);
            this.mediaPlayer.setAutoPlay(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        if (this.mediaPlayer == null) {
            return;
        }
        
        this.isMusicPlaying = !this.isMusicPlaying;
        
        if (this.isMusicPlaying) {
            this.mediaPlayer.play();
        } else {
            this.mediaPlayer.pause();
        }
    }
    
    public boolean getIsMusicPlaying() {
        return this.isMusicPlaying;
    }
    
    public boolean saveHistoryTxt(String route) {
        boolean retVal = false;
        FileOutputStream fos = null;
        Date now = new Date();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        
        String[] history = getRunningGame().getPrintableHistory().split("\n");
        
        try {
            File fout = new File(route);
            fos = new FileOutputStream(fout);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            
            bw.write("Jugadores:\n\n");
            bw.write(getRunningGame().getPlayer1().toString() + "\n\n" + getRunningGame().getPlayer2().toString() + "\n\n");
            bw.write("Historial del: " + df.format(now) + "\n\n");
            
            for (String item : history) {
                    bw.write(removeColorFromString(item));
                    bw.newLine();
            }
            
            bw.close();
            retVal = true;
        } catch (Exception ex) {
            // Logger.getLogger(MySystem.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fos.close();
            } catch (Exception ex) {
                // Logger.getLogger(MySystem.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return retVal;
    }
    
    public boolean saveHistoryPdf(String route) {
        boolean retVal = false;
        Date now = new Date();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        
        String[] history = getRunningGame().getPrintableHistory().split("\n");
        
        try {
            Document document = new Document();
            // step 2
            PdfWriter.getInstance(document, new FileOutputStream(route));
            // step 3
            document.open();
            // step 4
            document.add(new Paragraph("Jugadores:"));
            document.add(new Paragraph(getRunningGame().getPlayer1().toString() + "\n\n" + getRunningGame().getPlayer2().toString()+ "\n\n"));
            document.add(new Paragraph("Historial del: " + df.format(now) + "\n\n"));
            
            for (String item : history) {
                document.add(new Paragraph(removeColorFromString(item)));
            }
            
            // step 5
            document.close();
            retVal = true;
        } catch (Exception ex) {
            Logger.getLogger(MySystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return retVal;
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
    
    /**
     * Removes the color escape characters from a String
     * @param text - The text to remove the escape chars from
     * @return - The clean String
     */
    private String removeColorFromString(String text) {
        String retVal = text.replace(Game.ANSI_RESET, "");
        retVal = retVal.replace(Game.ANSI_RED, "");
        retVal = retVal.replace(Game.ANSI_BLUE, "");
        retVal = retVal.replace(Game.ANSI_GREEN, "");
        
        return retVal;
    }
}
