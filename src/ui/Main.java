
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import data.MySystem;

/**
 *
 * @author - Darío Dathaguy - Programación 2 - Número de estudiante: 220839 - Universidad ORT 
 * @author - Álvaro Nicoli - Programación 2 - Número de estudiante: 220159 - Universidad ORT
 */
public class Main {
    public static void main(String[] args) {
        MySystem system = new MySystem();
        UserInterface ui = new UserInterface(system);
        int option = -1;
      
        while (option != 0){
            option = ui.inputInt(UserInterface.MENU_PROMPT, 0, 3);
            
            ui.menu(option);
        }
    }
}


