
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obligatorio1;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author - Darío Dathaguy - Programación 2 - Número de estudiante: 220839 - Universidad ORT 
 * @author - Álvaro Nicoli - Programación 2 - Número de estudiante: 220159 - Universidad ORT
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      System.out.println("Bienvenido. Baila como el Papu");
      MySystem system = new MySystem();
      int opcion = 0;
      while (opcion == 0){
        switch (inputString("\n\n1- Crear Usuario"
                      + "\n2- Estadisticas de usuario"
                      + "\n3- Jugar"
                      + "\n4- Salir\n\n")) {
            case "1":
                createPlayer(system);
                break;
            case "2":
                // estadisticas(selectPlayer(sistema));
                break;
            case "3":
                System.out.println("Seleccione el jugador 1");
                // Player j1 = selectPlayer(system);
                Player j1 = new Player("Joselito", "joselito_patata22");
                System.out.println("Seleccione el jugador 2");
                Player j2 = new Player("Nadia", "nadia_love24");
                // Player j2 = selectPlayer(system);

                Game game = new Game(j1,j2,inputInt("Seleccione el tamano de tablero (3 o 5) >> ", 3, 5));

                while (game.isPlaying()) {
                    System.out.println("\n\n");
                    System.out.println(game.getPrintableGrid());
                    System.out.println(game.getTurnStatus());

                    String move = inputString("Ingresa tu movimiento >> ");

                    if (move.equalsIgnoreCase("X")) {
                        String confirm = inputString("Acepta el oponente la rendicion? (y/n) >> ");

                        if (confirm.equalsIgnoreCase("y")) {
                            game.surrender();
                        }
                    } else {
                        try {
                            game.inputMove(move);
                        } catch (Exception e) {
                            System.out.println("Movimiento invalido");
                        }
                    }
                }

                // game.play();

                break;
            case "4":
               opcion = 1;
                break;

            default:
                System.out.println("Ingrese algo correcto");
                break;

        }
      }
    }
    
    //Crear jugador
    public static void createPlayer(MySystem system) {
        Player player = new Player(inputString("Ingrese un nombre"), inputString("Ingrese un alias"));
        
        if (system.getPlayerList().contains(player)) {
            player.setAlias(inputString("Vuelva a ingresar un alias que no exista."));
        }
        
        system.getPlayerList().add(player);
    }
    //Elejir jugador
    public static Player selectPlayer(MySystem system) {
        ArrayList<Player> playerlist = system.getPlayerList();
        
        if (!playerlist.isEmpty()) {
            System.out.println("No se registro ni el Cholo");
        }
        
        for (int i = 0; i < playerlist.size(); i++) {
            System.out.print((i + 1) + " - " + playerlist.get(i).getAlias() + "\n");
        }
        
        int selected = inputInt("Selecciona tu jugador >> ", 1, playerlist.size());
        return playerlist.get(selected - 1);
    }
    
      //Método para definir el ingreso de un String. Se asegura que el string no sea nulo
    public static String inputString(String prompt) {
        Scanner input = new Scanner(System.in);
        String retVal = "";
        do {
            System.out.print(prompt);
            retVal = input.nextLine();
        } while (retVal.trim().length() == 0);

        return retVal;
    }

    //Método para definir el ingreso de un Int. Se asegura que este se encuentre en un rango dado
    public static int inputInt(String prompt, int min, int max) {
        Scanner input = new Scanner(System.in);
        int retVal = min - 1;
        do {

            System.out.print(prompt);
            //Try catch que controla excepciones
            try {
                retVal = input.nextInt();
            } catch (java.util.InputMismatchException e1) {
                System.out.println("El valor ingresado no es un número entero. Volver a ingresarlo");
            } catch (Exception e2) {
                System.out.println("Ocurrió una excepción" + e2.getMessage());
            } finally {
                //Descarta el valor almacenado anteriormente en el Scanner(Limpia el buffer)
                input.nextLine();
            }
        } while (retVal > max || retVal < min);

        return retVal;
    }
}


