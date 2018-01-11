/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aufgabe6_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import static java.lang.Thread.sleep;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class KeyboardClient extends Thread {

    public boolean avail = true;

    Socket socket;

    public KeyboardClient(Socket s) {
        this.socket = s;
    }

    public boolean available() {
        return avail;
    }

    @Override
    public void run() {

        try {
            BufferedWriter bw;
            OutputStream out = this.socket.getOutputStream();
            OutputStreamWriter outWriter = new OutputStreamWriter(out);
            bw = new BufferedWriter(outWriter);
            sendDataToServer(bw);
        } catch (IOException ex) {
            Logger.getLogger(KeyboardClient.class.getName()).log(Level.SEVERE, null, ex);
        }


        
    }

    public static void sendDataToServer(BufferedWriter bw) {

        System.out.println("Keyboard thread is running");
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            try {
                sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(KeyboardServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            input = scanner.nextLine();
            System.out.println("Before sending data to server . input is :" + input);
            try {
                bw.write(input + "\n");
                bw.flush();
                System.out.println("JUst sent data to server");
            } catch (IOException ex) {
                Logger.getLogger(KeyboardClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
