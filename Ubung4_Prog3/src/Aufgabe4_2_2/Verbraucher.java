/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aufgabe4_2_2;

//import Aufgabe4_2Ver_0.*;
//import Aufgabe4_2.*;
//import Aufgabe4_2Ver0_2.*;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class Verbraucher extends Thread{
    
    public RandomNumbers randomNumbers ;
    private boolean state = false;  // true if there any numbers in the vector, false if it is empty 
    //String name ;
    //Stop stop;
    //Shutdown shut;
  
    Verbraucher(RandomNumbers rn, String threadName) { 
        this.randomNumbers = rn;
        this.setName(threadName);
        //this.shut=shut;
    }
    
    public void run() {
        

        while(true)
        {
            //if(shut.shutdown!=true)
            
                try {
                    sleep ((int)(Math.random()*1000));
                } catch (InterruptedException ex) {
                    Logger.getLogger(Erzeuger.class.getName()).log(Level.SEVERE, null, ex);
                }
            try {
                //if()
                int number = randomNumbers.extractNumber();
                //System.out.println("The consumer just got " + number + " out of the numbers vector");
            } catch (IOException ex) {
                Logger.getLogger(Verbraucher.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
}
    

