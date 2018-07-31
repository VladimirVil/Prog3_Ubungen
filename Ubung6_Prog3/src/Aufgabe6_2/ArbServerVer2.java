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

import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.IOException;

public class ArbServerVer2 extends Thread {
    private static boolean running = true;

    public static void main(String[] args) throws IOException {
        ServerSocket socketServer = new ServerSocket(7000);
        new ArbServerVer2().start();
        while (running) {
            Socket socketClient = socketServer.accept();
            System.out.print("\nNew Client will be processed.\n");
            InputStreamReader isr = new InputStreamReader(socketClient.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            PrintWriter pw = new PrintWriter(socketClient.getOutputStream(), true);
            String input = "";
            double betrag = 0.0;
            while ((!input.equals("n")) && (!input.equalsIgnoreCase("quit"))) {
                pw.println("Amount?");
                input = br.readLine();
                try {
                    betrag = Double.parseDouble(input);
                    pw.println("Which currency? DM / EUR");
                    input = br.readLine();
                    if (input.equalsIgnoreCase("dm")) pw.println("Amount in EUR: " + (betrag / 1.95583));
                    if (input.equalsIgnoreCase("eur")) pw.println("Amount in DM: " + (1.95583 * betrag));
                    if (input.equalsIgnoreCase("quit")) break;
                    pw.println("Again? Y / N");
                    input = br.readLine();
                } catch (NumberFormatException e) {}
            }
            socketClient.close();
        }
        socketServer.close();
        System.out.print("\nServer ist shut down.\n");
    }


    @Override
    public void run() {
        System.out.print("\nServer is running.\nTo shut down Server type in: 'SHUTDOWN'.\n\n");
        Scanner scanner = new Scanner(System.in);
        String eingabe = scanner.nextLine();
        if (eingabe.equalsIgnoreCase("shutdown")) {
            scanner.close();
            running = false;
            System.out.print("\nAfter processing next Client, Server will be shut down..\n");


        }
    }
}