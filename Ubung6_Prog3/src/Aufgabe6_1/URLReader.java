/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aufgabe6_1;

import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.io.BufferedReader;

/**
 *
 * @author Admin
 */
public class URLReader {
    public static void main (String[] args) throws Exception  {
        
        URL url = new URL("http://www.google.com");
        
        try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String inputLine;
            
            while((inputLine= in.readLine()) != null)
                   System.out.println(inputLine); 
        }
        
    }
    
        
    
    
}
