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
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    public static final int PORT = 7777;

    public static void main(String[] args) throws IOException {
        boolean exitServer = false;
        while (!exitServer) { // Es wird solange eine Neue Verbindung aufgebaut, bis exitServer auf true gesetzt wird  Aufgabe b.a)
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(PORT);
            } catch (IOException e) { //Fehlermeldung bei fehlgeschlagener Verbindung
                System.err.println("Could not listen on port: " + PORT);
                System.exit(1);
            }
            Socket clientSocket = null;
            try {
                System.out.println("Listening on port " + PORT + " ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine().replaceAll("//s+", " ")) != null) { // Aufgabe c.a) Ersetzt alle Leerezeichen mit einem
                out.println(inputLine);
                if (inputLine.equalsIgnoreCase("bye")) {
                    break;
                } else if (inputLine.equalsIgnoreCase("quit")) {
                    //System.exit(1);
                    exitServer = true;
                    break;
                }
            }
            //Schlieﬂen der Streams
            out.close();
            in.close();
            clientSocket.close();
            serverSocket.close();
            System.out.println("Verbindung zu neuen Client...");
        }
    }
}
