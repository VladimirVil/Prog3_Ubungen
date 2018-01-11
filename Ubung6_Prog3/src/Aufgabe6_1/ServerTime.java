/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aufgabe6_1;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
* The ServerTime class implements a server, that responses with its current time 
* for every connection from a client 
* 
*
* @author  Vladimir Vilenchik
* @version 1.0
* @since   03.01.2017 
*/
public class ServerTime {
    public static void main (String[] args) throws IOException {
        final String localHost = "127.0.0.1";
        final int port = 7777;
        boolean keepRunning = true;
        String currentTime=null;
        
        ServerSocket listener = new ServerSocket(port);
        
        try {
            while (keepRunning)
            {
                Socket socket = listener.accept();
                try {
                    System.out.println("Connection with client system  established successfully");
                    // attach output stream to the server socket which is used to send Data to the client
                    OutputStream outStream = socket.getOutputStream();
                    // attach print stream to send the data
                    PrintStream p = new PrintStream(outStream);
                    p.println("Transfering the system's time...");
                    currentTime = getSystemTime();
                    p.println(currentTime);
                    p.close();
                    
                    
                } finally {
                    socket.close();
                }
                
            }
        }
        finally {
            listener.close();
        }
        
    }
    
    
    private static String getSystemTime() {
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        Date dateobj = new Date();
        return df.format(dateobj);
    }
    
}
