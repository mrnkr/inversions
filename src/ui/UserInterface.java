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
import java.util.Scanner;

/**
 *
 * @author - Darío Dathaguy - Programación 2 - Número de estudiante: 220839 - Universidad ORT 
 * @author - Álvaro Nicoli - Programación 2 - Número de estudiante: 220159 - Universidad ORT
 */
public class UserInterface {
    public static final String MENU_PROMPT = "╔═════════════════════════╦═══════════╗\n" +
                                             "║     * Inveriones *      ║ Salir - 0 ║\n" +
                                             "╠═════════════════════════╩═══════════╣\n" +
                                             "║ 1. Registrar jugador                ║\n" +
                                             "║ 2. Jugar                            ║\n" +
                                             "║ 3. Ranking                          ║\n" +
                                             "╚═════════════════════════════════════╝\n\n";
    
    private MySystem system;
    
    public UserInterface(MySystem system) {
        this.system = system;
    }
    
    public void menu(int option) {
        switch(option) {
            case 1: // Create Player
                createPlayer();
                break;
            case 2: // Play
                if (!this.system.hasPlayers()) {
                    System.out.println("No hay jugadores registrados...");
                    break;
                }
                
                Player player1 = selectPlayer(null);
                Player player2 = selectPlayer(player1);
                int gridSize = inputGridSize();
                
                playGame(player1, player2, gridSize);
                break;
            case 3: // Ranking
                ranking();
                break;
            case 0: // Exit
                break;
        }
    }
    
    /**
     * Lets the user create a Player and stores it in the list
     */
    public void createPlayer() {
        Player player = new Player(inputString("Como te llamas? >> "), inputString("Elige un alias >> "), inputInt("Ingresa tu edad >> ", 1, 120));
        
        while (this.system.getPlayerList().contains(player)) {
            player.setAlias(inputString("Tu alias ya está en uso... Elige otro >> "));
        }
        
        this.system.getPlayerList().add(player);
    }
    
    /**
     * Lets the user choose a player from the list to play as
     * @param alreadyChosen - The player that was chosen as player1 so that player2 is not the same - Pass null if selecting the first player
     * @return - The player chosen by the user
     */
    public Player selectPlayer(Player alreadyChosen) {
        int selected = Integer.MIN_VALUE;
        
        for (int i = 0; i < this.system.getPlayerList().size(); i++) {
            if (i != this.system.getPlayerList().indexOf(alreadyChosen)) {
                System.out.print((i + 1) + " - " + this.system.getPlayerList().get(i).getAlias() + "\n");
            }
        }
        
        do {
           selected = inputInt("Selecciona un jugador >> ", 1, system.getPlayerList().size());
           
           if (this.system.getPlayerList().get(selected - 1).equals(alreadyChosen)) {
               System.out.println("No puedes elegir el mismo jugador dos veces!");
           }
        } while (this.system.getPlayerList().get(selected - 1).equals(alreadyChosen));
        
        return system.getPlayerList().get(selected - 1);
    }
    
    /**
     * Prompts the user to input the size of the grid - Makes sure it is either 3 or 5
     * @return - The selected size
     */
    public int inputGridSize() {
        int retVal = 0;
        
        do {
            retVal = inputInt("Seleccione el tamano de tablero (3 o 5) >> ", 3, 5);

            if (retVal != 3 && retVal != 5) {
                System.out.println("Error, intente nuevamente");
            }
        } while (retVal != 3 && retVal != 5);
        
        return retVal;
    }
    
    /**
     * Starts a game in the instance of MySystem and plays until its end
     * @param player1
     * @param player2
     * @param gridSize 
     */
    public void playGame(Player player1, Player player2, int gridSize) {
        this.system.startNewGame(player1, player2, gridSize);
        Game game = this.system.getRunningGame(); // Will reference the same object that is in MySystem
        boolean rotateGrid = false;

        while (game.isPlaying()) {
            System.out.println("\n\n");
            System.out.println(game.getPrintableGrid(rotateGrid));
            System.out.println(game.getTurnStatus());

            String move = inputString("Ingresa tu movimiento >> ");

            if (move.equalsIgnoreCase("X")) {
                String confirm = inputString("Acepta el oponente la rendicion? (y/n) >> ");

                if (confirm.equalsIgnoreCase("y")) {
                    game.surrender();
                }
            } else if (move.equalsIgnoreCase("E")) {
                String confirm = inputString("Acepta el oponente el empate? (y/n) >> ");

                if (confirm.equalsIgnoreCase("y")) {
                    game.draw();
                }
            } else if (move.equalsIgnoreCase("H")) {
                System.out.println(game.getPrintableHistory());
            } else if (move.equalsIgnoreCase("Y")) {
                System.out.println(player1.isPlaying() ? game.getPossibleMoveList(player1) : game.getPossibleMoveList(player2));
            } else if (move.equalsIgnoreCase("R")) {
                rotateGrid = !rotateGrid;
            } else {
                try {
                    game.inputMove(move);

                    // After performing the move check if the game has a winner
                    Player winner = game.hasWinner();
                    if (winner != null) {
                        game.endGame(winner);
                        System.out.println("Ganador: " + winner.getColor() + winner.getAlias() + Game.ANSI_RESET);
                    }
                } catch (Exception e) {
                    System.out.println("Movimiento invalido");
                }
            }
        }
    }
    
    public void ranking() {
        if (!this.system.hasPlayers()) {
            System.out.println("No hay jugadores registrados aún...\n\n");
        }

        for (int i=0; i < this.system.getPlayerList().size(); i++) {
            System.out.println(this.system.getPlayerList().get(i) + "\n");
        }
    }
    
    /**
     * Asks user to input a String
     * @param prompt - The message to show the user prior to the data input
     * @return - The inputted value
     */
    public String inputString(String prompt) {
        Scanner input = new Scanner(System.in);
        String retVal = "";
        
        do {
            System.out.print(prompt);
            retVal = input.nextLine();
        } while (retVal.trim().length() == 0);

        return retVal;
    }
    
    /**
     * Asks user to input a number
     * @param prompt - Message to show to the user prior to the data input
     * @param min - Minimum acceptable value
     * @param max - Maximum acceptable value
     * @return - The inputted value
     */
    public int inputInt(String prompt, int min, int max) {
        Scanner input = new Scanner(System.in);
        int retVal = min - 1;
        
        do {
            System.out.print(prompt);
            
            try {
                retVal = input.nextInt();
            } catch (java.util.InputMismatchException e1) {
                System.out.println("El valor ingresado no es un número entero. Volver a ingresarlo");
            } catch (Exception e2) {
                System.out.println("Ocurrió un error inesperado... Lo sentimos...");
            } finally {
                // Clean the buffer
                input.nextLine();
            }
            
            if (retVal > max || retVal < min) {
                System.out.println("Valor fuera de los limites, intenta nuevamente...");
            }
        } while (retVal > max || retVal < min);

        return retVal;
    }
}
