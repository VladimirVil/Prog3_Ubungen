/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aufgabe4_2_2;

//import Aufgabe4_2.*;
//import Aufgabe4_2Ver_0.*;
//import Aufgabe4_2Ver0_2.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class Erzeuger extends Thread{
    
    public RandomNumbers randomNumbers ;
    private boolean state = false;  // true if there any numbers in the vector, false if it is empty 
    //public Stop stop;
    //public Shutdown shut;
    //String name ;
    Keyboard keyboard;
    
    Erzeuger(RandomNumbers rn, String threadName){
        this.randomNumbers=rn;
        this.setName(threadName);
        this.keyboard= new Keyboard();
        keyboard.start();
        //this.shut=shut;
    } 
    
    public void run(){
        
        int number = (int)((Math.random()*100) % 60);// creating a new integer between 0 and 60
        //System.out.println("The number to add is " + number );
        //while(true) {
        //while(randomNumbers.shutdown!=true)
        while(true)
        {
            //if(shut.shutdown!=true) // only in case the sto flag is off
            
                try {
                    sleep ((int)(Math.random()*1000));
                    //sleep(5000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Erzeuger.class.getName()).log(Level.SEVERE, null, ex);
                }
                while(!keyboard.available())
                    
                {
                    try {
                        sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Erzeuger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                this.randomNumbers.addNumber(number);
                //System.out.println("Producer added " + number + " to the vector of numbers");
                number = (int)((Math.random()*100) % 60);
            
        }
    }
}
    
    
