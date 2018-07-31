/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aufgabe4_2_2;

//import Aufgabe4_2Ver0_2.*;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class Keyboard extends Thread {

    public boolean avail = true;

    public boolean available() {
        return avail;
    }

    @Override
    public void run() {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Keyboard.class.getName()).log(Level.SEVERE, null, ex);
            }
            scanner.nextLine();

            avail = (!avail);  // changes the status of avail.  
            
            
            //byte input = scanner.nextByte();
            
        }
    }

}
