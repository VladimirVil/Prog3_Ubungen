/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aufgabe4_2_2;

//import Aufgabe4_2Ver_0.*;
//import Aufgabe4_2.*;
//import Aufgabe4_2Ver0_2.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Console;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

/**
 *
 * @author Admin
 */
public class RandomNumbers {

    //private Vector numbers = new Vector();
    public boolean available = false; // is anything exist in the vector?

    public boolean isPaused = false;

    Scanner sc = new Scanner(System.in);
    public boolean cont = true;  // variable to run the reading from concole 
    final PipedOutputStream output = new PipedOutputStream();
    final PipedInputStream input;

    public RandomNumbers() throws IOException {

        this.input = new PipedInputStream(this.output);
    }

    public int extractNumber() throws IOException {
        int returnNumber = 0;

        //System.out.println("extractNumber: reading..");  //debug only 
        returnNumber = this.input.read();

        for (int i = 0; i < returnNumber; i++) {
            System.out.printf("*");
        }
        System.out.println("\n");

        return returnNumber;
    }

     public void addNumber(int num) {

        try {
            //System.out.println("extractNumber: writing..");  // debug only 
            this.output.write(num);
        } catch (IOException ex) {
            Logger.getLogger(RandomNumbers.class.getName()).log(Level.SEVERE, null, ex);
        }
        available = true;

    }


}
