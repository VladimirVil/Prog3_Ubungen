/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aufgabe6_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class ArbClient {
    public static void main (String[] args) throws IOException
    {
        final String localHost = "127.0.0.1";
        final int port = 8888;
        
        Socket s = new Socket(localHost, port);
        
        //attaching input stream to receive data from Server 
        InputStream in = s.getInputStream();
        //creating buffer reader to read data from socket to the client 
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        
        String receivedData = null;
        
        while ((receivedData=br.readLine()) != null)
        {
            System.out.println(receivedData);
        }  
        
        br.close();
        s.close();
    }
    
}
