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

import data.MySystem;
import data.Player;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import helpers.FrameDelegateInterface;

/**
 *
 * @author - Darío Dathaguy - Programación 2 - Número de estudiante: 220839 - Universidad ORT 
 * @author - Álvaro Nicoli - Programación 2 - Número de estudiante: 220159 - Universidad ORT
 */

public class RankingForm extends javax.swing.JFrame {
    private MySystem system;
    private FrameDelegateInterface delegate;

    /**
     * Creates new form RankingForm
     * @param system
     * @param delegate
     */
    public RankingForm(MySystem system, FrameDelegateInterface delegate) {
        this.system = system;
        this.delegate = delegate;
        
        initComponents();
        populateTable();
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
        myRankingTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ranking");
        setAlwaysOnTop(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        myRankingTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Posición", "Alias", "Victorias", "Empates", "Derrotas"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(myRankingTable);
        if (myRankingTable.getColumnModel().getColumnCount() > 0) {
            myRankingTable.getColumnModel().getColumn(0).setResizable(false);
            myRankingTable.getColumnModel().getColumn(1).setResizable(false);
            myRankingTable.getColumnModel().getColumn(2).setResizable(false);
            myRankingTable.getColumnModel().getColumn(3).setResizable(false);
            myRankingTable.getColumnModel().getColumn(4).setResizable(false);
        }

        getContentPane().add(jScrollPane1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.delegate.onFrameClosing(null);
    }//GEN-LAST:event_formWindowClosing

    private void populateTable() {
        DefaultTableModel model = (DefaultTableModel) myRankingTable.getModel();
        ArrayList<Player> playerList = this.system.getPlayerList();
       
        for (int i = 0; i < playerList.size(); i++){
            int position = i+1;
            String alias = playerList.get(i).getAlias();
            int wins = playerList.get(i).getWins();
            int draws = playerList.get(i).getDraws();
            int losses = playerList.get(i).getLosses();
            
            Object[] data = {position, alias, wins, draws, losses};
            model.addRow(data);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable myRankingTable;
    // End of variables declaration//GEN-END:variables
}
