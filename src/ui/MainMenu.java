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
import java.awt.Toolkit;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;

/**
 *
 * @author - Darío Dathaguy - Programación 2 - Número de estudiante: 220839 - Universidad ORT 
 * @author - Álvaro Nicoli - Programación 2 - Número de estudiante: 220159 - Universidad ORT
 */

public class MainMenu extends javax.swing.JFrame {
    
    private MySystem system;

    /**
     * Creates new form MainMenu
     * @param system
     */
    public MainMenu(MySystem system) {
        this.system = system;
        
        initComponents();
        setButtonIcons();
        
        this.system.toggleMusicPlaying();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        myRegisterPlayerButton = new javax.swing.JButton();
        myPlayButton = new javax.swing.JButton();
        myRankingButton = new javax.swing.JButton();
        myLogoLabel = new javax.swing.JLabel();
        myToggleMusicButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Inversiones");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/icon.png")));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        myRegisterPlayerButton.setText("Registrar Jugador");
        myRegisterPlayerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myRegisterPlayerButtonActionPerformed(evt);
            }
        });

        myPlayButton.setText("Jugar");
        myPlayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myPlayButtonActionPerformed(evt);
            }
        });

        myRankingButton.setText("Ranking");
        myRankingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myRankingButtonActionPerformed(evt);
            }
        });

        myLogoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/logo.png"))); // NOI18N

        myToggleMusicButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myToggleMusicButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(myLogoLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(myToggleMusicButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(60, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(myRegisterPlayerButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(myPlayButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(myRankingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(163, 163, 163))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(myLogoLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addComponent(myRegisterPlayerButton)
                .addGap(18, 18, 18)
                .addComponent(myPlayButton)
                .addGap(18, 18, 18)
                .addComponent(myRankingButton)
                .addGap(12, 12, 12)
                .addComponent(myToggleMusicButton)
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void setButtonIcons() {
        IconFontSwing.register(FontAwesome.getIconFont());
        
        Icon volumeOffIcon = IconFontSwing.buildIcon(FontAwesome.VOLUME_UP, 15);
        myToggleMusicButton.setIcon(volumeOffIcon);
        
        Icon playIcon = IconFontSwing.buildIcon(FontAwesome.GAMEPAD, 15);
        myPlayButton.setIcon(playIcon);
        
        Icon rankingIcon = IconFontSwing.buildIcon(FontAwesome.TROPHY, 15);
        myRankingButton.setIcon(rankingIcon);
        
        Icon registerIcon = IconFontSwing.buildIcon(FontAwesome.USER, 15);
        myRegisterPlayerButton.setIcon(registerIcon);
    }
    
    private void myRankingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myRankingButtonActionPerformed
        if (!this.system.hasPlayers()) {
            JOptionPane.showMessageDialog(this, "No hay jugadores registrados", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        java.awt.EventQueue.invokeLater(() -> {
            this.setEnabled(false);
            
            new RankingForm(this.system, args -> {
                this.setEnabled(true);
            }).setVisible(true);
        });
    }//GEN-LAST:event_myRankingButtonActionPerformed

    private void myToggleMusicButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myToggleMusicButtonActionPerformed
        Icon icon;
        
        if (this.system.getIsMusicPlaying()) {
            icon = IconFontSwing.buildIcon(FontAwesome.VOLUME_OFF, 15);
        } else {
            icon = IconFontSwing.buildIcon(FontAwesome.VOLUME_UP, 15);
        }
        
        this.system.toggleMusicPlaying();
        myToggleMusicButton.setIcon(icon);
    }//GEN-LAST:event_myToggleMusicButtonActionPerformed

    private void myRegisterPlayerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myRegisterPlayerButtonActionPerformed
        java.awt.EventQueue.invokeLater(() -> {
            this.setEnabled(false);
            
            new UserRegistrationForm(this.system, args -> {
                this.setEnabled(true);
            }).setVisible(true);
        });
    }//GEN-LAST:event_myRegisterPlayerButtonActionPerformed

    private void myPlayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myPlayButtonActionPerformed
        java.awt.EventQueue.invokeLater(() -> {
            if (this.system.getRunningGame() != null) {
                this.setVisible(false);
                
                new GameView(this.system, args -> {
                    this.setVisible(true);
                }).setVisible(true);
            } else {
                this.setEnabled(false);
                
                new GameSetupForm(this.system, args -> {
                    // GameSetupForm Delegate
                    this.setEnabled(true);
                    
                    if (args.equals("opening-game")) {
                        this.setVisible(false);
                    }
                }, args -> {
                    this.setVisible(true);
                }).setVisible(true);
            }
        });
    }//GEN-LAST:event_myPlayButtonActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.system.saveGame();
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel myLogoLabel;
    private javax.swing.JButton myPlayButton;
    private javax.swing.JButton myRankingButton;
    private javax.swing.JButton myRegisterPlayerButton;
    private javax.swing.JButton myToggleMusicButton;
    // End of variables declaration//GEN-END:variables
}
