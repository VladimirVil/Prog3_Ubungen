/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EchoBeispielModified;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Client part of a socket-based network echoing application.
 * The client is configured to connect to port 7777 or to a port provided in args manually
 * Will close the connection by the words bye or quit 
 */
public class EchoClient {
    public static final int PORT = 7777;
    public static void main(String[] args) throws IOException {
        //WeMosTCP_Server
        String hostname = "localhost";
        //int port = Integer.parseInt(args[0]);  // getting the port number thru arguments 
        
        //try (Socket echoSocket = new Socket(hostname, port); // client connects to a server on a  
                // (known) hostname and port 
        try (Socket echoSocket = new Socket(hostname, PORT);
                
                PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);  // out is used to
                //print on the other side of the socket d.h. on the server 
                // it is connected to the outputStream of the socket 
                BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream())); //in 
                // is connected to the socket - it reads everything that comes from the socket d.h.
                // everything from the server 
                
                //stdIn receives the  input from the console on the client's side,
                // and transfer it using "out" thru the socket to the server'S side 
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {
            String userInput = null;
            String tempInput = null;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput); 
                System.out.println("echo: " + tempInput);
                if (userInput.equalsIgnoreCase("bye"))
                    break;
            }
            
            out.close();
            in.close();
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + hostname);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for " + "the connection to: " + hostname);
            System.exit(1);
        }
    }
}
