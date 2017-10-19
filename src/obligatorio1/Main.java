
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obligatorio1;
import java.util.Scanner;

/**
 *
 * @author - Darío Dathaguy - Programación 2 - Número de estudiante: 220839 - Universidad ORT 
 * @author - Álvaro Nicoli - Programación 2 - Número de estudiante: 220159 - Universidad ORT
 */
public class Main {
    public static void main(String[] args) {
      System.out.println("Bienvenido. Baila como el Papu");
      MySystem system = new MySystem();
      int option = 0;
      
      while (option == 0){
        switch (inputString("\n\n1- Registrar jugador"
                      + "\n2- Jugar"
                      + "\n3- Ranking"
                      + "\n4- Salir\n\n")) {
            case "1":
                createPlayer(system);
                break;
            case "3":
                if(system.getPlayerList().isEmpty()) {
                    System.out.println("No hay jugadores registrados aún...\n\n");
                }
                
                for(int i=0; i < system.getPlayerList().size(); i++){
                    System.out.println(system.getPlayerList().get(i) + "\n");
                }
                
                break;
            case "2":
                if (system.getPlayerList().size() < 2) {
                    System.out.println("No hay suficientes jugadores registrados aún...\n\n");
                    break;
                }
                
                System.out.println("Seleccione el jugador 1");
                Player j1 = selectPlayer(system, -1);
                System.out.println("Seleccione el jugador 2");
                Player j2 = null;
                
                do {
                    j2 = selectPlayer(system, system.getPlayerList().indexOf(j1));
                    
                    if (j1.equals(j2)) {
                        System.out.println("No pueden ser ambos el mismo jugador!");
                    }
                } while (j1.equals(j2));

                int gridSize = 2;
                
                do {
                    gridSize = inputInt("Seleccione el tamano de tablero (3 o 5) >> ", 3, 5);
                    
                    if (gridSize != 3 && gridSize != 5) {
                        System.out.println("Error, intente nuevamente");
                    }
                } while (gridSize != 3 && gridSize != 5);
                
                Game game = new Game(j1, j2, gridSize);
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
                        System.out.println(j1.isPlaying() ? game.getPossibleMoveList(j1) : game.getPossibleMoveList(j2));
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

                break;
            case "4":
                option = 1;
                break;
            default:
                System.out.println("Opción inválida... Intenta nuevamente...");
                break;
        }
      }
    }
    
    /**
     * Lets the user create a Player and stores it in the list
     * @param system - The instance of MySystem that contains the list of players
     */
    public static void createPlayer(MySystem system) {
        Player player = new Player(inputString("Como te llamas? >> "), inputString("Elige un alias >> "), inputInt("Ingresa tu edad >> ", 1, 120));
        
        while (system.getPlayerList().contains(player)) {
            player.setAlias(inputString("Tu alias ya está en uso... Elige otro >> "));
        }
        
        system.getPlayerList().add(player);
    }

    /**
     * Lets the user choose a player from the list to play as
     * @param system - The instance of MySystem that contains the list of players
     * @param alreadyChosen - The index of the player that was chosen as player1 so that player2 is not the same - Use -1 if choosing player1
     * @return - The player chosen by the user
     */
    public static Player selectPlayer(MySystem system, int alreadyChosen) {
        for (int i = 0; i < system.getPlayerList().size(); i++) {
            if (i != alreadyChosen) {
                System.out.print((i + 1) + " - " + system.getPlayerList().get(i).getAlias() + "\n");
            }
        }
        
        int selected = inputInt("Selecciona un jugador >> ", 1, system.getPlayerList().size());
        
        return system.getPlayerList().get(selected - 1);
    }

    /**
     * Asks user to input a String
     * @param prompt - The message to show the user prior to the data input
     * @return - The inputted value
     */
    public static String inputString(String prompt) {
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
    public static int inputInt(String prompt, int min, int max) {
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


