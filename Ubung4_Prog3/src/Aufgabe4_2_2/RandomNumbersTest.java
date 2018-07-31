/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aufgabe4_2_2;

//import Aufgabe4_2.*;
//import Aufgabe4_2Ver_0.*;
//import Aufgabe4_2Ver0_2.*;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class RandomNumbersTest {
    public static void main (String[] args) throws IOException {
        
        //RandomNumbers rn = new RandomNumbers(); 
        //Stop stop = new Stop(false);
        //Shutdown shut = new Shutdown("controller");
        RandomNumbers rn = new RandomNumbers();
        Erzeuger erz1 = new Erzeuger(rn,"first Producer");
        Verbraucher ver1 = new Verbraucher(rn, "first Consumer");
        
        erz1.start();
        ver1.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(RandomNumbersTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    } 
}
        
    
    

