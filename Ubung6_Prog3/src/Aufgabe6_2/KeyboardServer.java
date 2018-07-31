/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aufgabe6_2;

import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class KeyboardServer extends Thread {

    public boolean avail = true;

    public boolean available() {
        return avail;
    }

    @Override
    public void run() {
        //System.out.println("Keyboard thread is running");
        Scanner scanner = new Scanner(System.in);
        String input = null;
        //while (true) {
        while (avail) {
            try {
                sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(KeyboardServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            //if (scanner.nextLine() == "SHUTDOWN\n") {
            input = scanner.nextLine();
            //System.out.println("the received input is:" + input);
            if (input.equals("SHUTDOWN")) {
                //System.out.println(scanner);
                System.out.print("\nAfter processing next Client, Server will be shut down..\n");
                avail = false;
            }

            //avail = (!avail);  // changes the status of avail.  
            //byte input = scanner.nextByte();
        }
    }

}
