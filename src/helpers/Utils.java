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
package helpers;

import data.Game;

/**
 *
 * @author - Darío Dathaguy - Programación 2 - Número de estudiante: 220839 - Universidad ORT 
 * @author - Álvaro Nicoli - Programación 2 - Número de estudiante: 220159 - Universidad ORT
 */

public class Utils {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    
    /**
     * Transforms the part of the coordinates input as char into a workable int
     * @param c - the letter coordinate
     * @return - The index to use in the array
     */
    public static int charToInt(char c) {
        return ((int) c) - 65;
    }
    
    public static char intToChar(int i) {
        return (char)(i + 65);
    }
    
    public static String makeMoveString(Game game, boolean isGridRotated, int x, int y) {
        if (isGridRotated) {
            x = game.getGridSize() - x - 1;
            y = game.getGridSize() - y - 1;
        }
        
        return String.valueOf(intToChar(y)) + (game.getGridSize() - x);
    }
    
    /**
     * Takes coordinates and makes the move string
     * @param game
     * @param curX - Current X axis position of the token to move
     * @param curY - Current Y axis position of the token to move
     * @param newX - New X axis position of the token to move
     * @param newY - New Y axis position of the token to move
     * @return - The move String like "B1 B2"
     */
    public static String makeMoveString(Game game, int curX, int curY, int newX, int newY) {
        return String.valueOf(intToChar(curY)) + (game.getGridSize() - curX) + " " + String.valueOf(intToChar(newY)) + (game.getGridSize() - newX) + "\n";
    }
    
    /**
     * Removes the color escape characters from a String
     * @param text - The text to remove the escape chars from
     * @return - The clean String
     */
    public static String removeColorFromString(String text) {
        String retVal = text.replace(ANSI_RESET, "");
        retVal = retVal.replace(ANSI_RED, "");
        retVal = retVal.replace(ANSI_BLUE, "");
        retVal = retVal.replace(ANSI_GREEN, "");
        
        return retVal;
    }
}
