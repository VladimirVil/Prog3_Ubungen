/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Admin
 */
public class ClientExample {

    public static void main(String[] args) throws IOException {

        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        try {
            socket = new Socket("localhost", 1234);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host");
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection");
        }
        System.out.println("Connected");

        String textToServer;

        while ((textToServer = read.readLine()) != null) {
            out.print(textToServer + "\r\n");  // send to server
            out.flush();

            String messageFromServer = null;
            while ((messageFromServer = textToServer = in.readLine()) != null) {
                System.out.println(messageFromServer);
            }
        }
        out.close();
        in.close();
        read.close();
        socket.close();
    }

    private static void debug(String msg) {
        System.out.println("Client: " + msg);
    }
}
