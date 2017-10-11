
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obligatorio1;
import java.lang.System;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author Dario
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      System.out.println("Bienvenido a las damas chinas");
      Sistema sistema = new Sistema();
      int opcion = 0;
      while (opcion == 0){
      switch (ingresoString("\n1- Crear Usuario\n2- Estadisticas de usuario"
                    + "\n3- Jogar"
                   
                    + "\n4- Salir")) {
                case "1":
                    createPlayer(sistema);
                    break;
                case "2":
                    estadisticas(selectPlayer(sistema));
                    break;
                case "3":
                    System.out.println("Seleccione el jugador 1");
                    Player j1 = selectPlayer(sistema);
                    System.out.println("Seleccione el jugador 2");
                    Player j2 = selectPlayer(sistema);
                    
                    Match game = new Match(j1,j2,inputInt("Seleccione el tipo de juego (3 a 5)", 3, 5));
                    game.play();   

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
    
      //Método para definir el ingreso de un String. Se asegura que el string no sea nulo
    public static String ingresoString(String loquepide) {
        Scanner input = new Scanner(System.in);
        String unstring = "";
        do {
            System.out.println(loquepide);
            unstring = input.nextLine();
        } while (unstring.trim().length() == 0);

        return unstring;
    }

    //Método para definir el ingreso de un Int. Se asegura que este se encuentre en un rango dado
    public static int inputInt(String loquepide, int minimo, int maximo) {
        Scanner input = new Scanner(System.in);
        int retVal = minimo - 1;
        do {

            System.out.println(loquepide);
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
        } while (retVal > maximo || retVal < minimo);

        return retVal;
    }
    //Crear jugador
    public static void createPlayer(Sistema elsistema) {
          
        Player elplayer = new Player(ingresoString("Ingrese un nombre"),ingresoString("Ingrese un alias"));
        if(elsistema.getPlayerList().contains(elplayer))
        elplayer.setAlias(ingresoString("Vuelva a ingresar un alias que no exista."));
        elsistema.getPlayerList().add(elplayer);
    }
    //Elejir jugador
    public static Player selectPlayer(Sistema elsistema) {
        ArrayList<Player> playerlist = elsistema.getPlayerList();
        if (!playerlist.isEmpty()) {
            System.out.println("Ingrese el número del artículo");
        }
        for (int i = 0; i < playerlist.size(); i++) {

            System.out.print(i + 1 + "- " + playerlist.get(i).getAlias() + "\n");
        }
        int index = inputInt("", 1, playerlist.size());

        return playerlist.get(index - 1);
    }
}


