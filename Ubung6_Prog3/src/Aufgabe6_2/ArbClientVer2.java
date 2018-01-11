/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aufgabe6_2;

/**
 *
 * @author Admin
 */

import java.net.Socket;
import java.net.SocketTimeoutException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.IOException;

public class ArbClientVer2 {
    public static void main(String[] args) throws IOException {
        Socket socketClient = new Socket("localhost", 7000);
        socketClient.setSoTimeout(100);
        System.out.print("\nClient is running -  to close it, type 'QUIT'.\n\n");
        InputStreamReader isr = new InputStreamReader(socketClient.getInputStream());
        BufferedReader br = new BufferedReader(isr);
        PrintWriter pw = new PrintWriter(socketClient.getOutputStream(), true);
        Scanner scanner = new Scanner(System.in);
        String input = "";
        String stream = null;

        while (true) {
            try {
                while ((stream = br.readLine()) != null) {
                    System.out.println(stream);
                }
            } catch (SocketTimeoutException e) {}
            stream = null;
            System.out.print("Input: ");
            input = scanner.nextLine();
            pw.println(input);
            if (input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("n")) {
                break;
            }
        }

        scanner.close();
        pw.close();
        br.close();
        isr.close();
        socketClient.close();
    }
}

