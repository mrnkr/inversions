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
package ui;

import data.Game;
import data.MySystem;
import data.Player;
import javax.swing.DefaultListModel;
import helpers.FrameDelegateInterface;
import helpers.Utils;

/**
 *
 * @author - Darío Dathaguy - Programación 2 - Número de estudiante: 220839 - Universidad ORT 
 * @author - Álvaro Nicoli - Programación 2 - Número de estudiante: 220159 - Universidad ORT
 */

public class GameDataForm extends javax.swing.JFrame {
    private MySystem system;
    private FrameDelegateInterface delegate;
    
    /**
     * Creates new form GameDataForm
     * @param possibleMoves
     * @param system - The system instance
     * @param delegate
     */
    public GameDataForm(boolean possibleMoves, MySystem system, FrameDelegateInterface delegate) {
        this.system = system;
        this.delegate = delegate;
        
        initComponents();
        
        if (possibleMoves) {
            this.setTitle("Ayuda - Movimientos Posibles");
            populateListWithPossibleMoves();
        } else {
            this.setTitle("Historial de jugadas");
            populateListWithHistory();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(400, 256));
        setSize(new java.awt.Dimension(300, 180));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        getContentPane().add(jScrollPane1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.delegate.onFrameClosing(null);
    }//GEN-LAST:event_formWindowClosing

    private void populateListWithHistory() {
        DefaultListModel model = new DefaultListModel();
        String[] history = this.system.getRunningGame().getPrintableHistory().split("\n");
        
        for (String item : history) {
            model.addElement(Utils.removeColorFromString(item));
        }
        
        this.jList1.setModel(model);
    }
    
    private void populateListWithPossibleMoves() {
        DefaultListModel model = new DefaultListModel();
        Player nowPlaying = this.system.getRunningGame().getPlayer1().isPlaying() ? 
                this.system.getRunningGame().getPlayer1() : 
                this.system.getRunningGame().getPlayer2();
        
        String[] moves = this.system.getRunningGame().getPossibleMoveList(nowPlaying).split("\n");
        
        for (String item : moves) {
            model.addElement(Utils.removeColorFromString(item));
        }
        
        this.jList1.setModel(model);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
