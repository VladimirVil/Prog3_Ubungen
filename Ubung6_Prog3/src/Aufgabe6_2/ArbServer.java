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
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class ArbServer {

    public static void main(String[] args) throws IOException {

        //exchange rates 
        final double DM2EU = 1.25;
        final double EU2DM = 0.8;

        String waehrung = null;  // currency,DM oder EU
        double amountToExchange = 0;  // needed sum
        String amountToExchangeStr = null;
        String confirmationAnswer = null;
        String quit = null;
        final String localHost = "127.0.0.1";
        final int port = 8888;
        String currentTime = null;
        Keyboard keyboard = new Keyboard();
        keyboard.start();

        ServerSocket listener = new ServerSocket(port);

        while (keyboard.available()) {
            Socket socket = listener.accept();

            InputStream inStream = socket.getInputStream();
            OutputStream outStream = socket.getOutputStream();
            // attach print stream to send the data
            PrintStream p = new PrintStream(outStream);
            //BufferedReader r = new BufferedReader(new InputStreamReader(inStream));  //The original
            BufferedReader r = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //BufferedReader r = new BufferedReader (new InputStreamReader(System.in));

            while (true) {
                p.println("Connection established succesfully ");
                p.println("Welche Waehrung moechten Sie eingeben?");
                waehrung = r.readLine();
                System.out.println("The input is : " + waehrung);
                waehrung=testReadMethode(socket, inStream, outStream, p, r);
                //waehrung = currencyChoice(socket,inStream, outStream, p, r);  //check the correctness of the currency choice 
                p.println("Welche Wert wollen Sie umrechnen?");
                /*amountToExchangeStr = r.readLine();
                amountToExchange = Double.parseDouble(amountToExchangeStr); */
                amountToExchange = amountCheck(socket, inStream, outStream, p, r);
                if (waehrung.equals("EU")) {
                    p.println("The amount in DM is: " + amountToExchange * (EU2DM));
                } else if (waehrung.equals("DM")) {
                    p.println("The amount in EU is: " + amountToExchange * (DM2EU));
                }
                p.println("Darf es noch eine Umrechnung sein?");
                confirmationAnswer = confirmCheck(socket, inStream, outStream, p, r);
                if (confirmationAnswer.equals("No") || (confirmationAnswer.equals("no"))) {
                    break;
                }

                //InputStream in = socket.getInputStream();
                //creating buffer reader to read data from socket to the client 
                //BufferedReader br = new BufferedReader(new InputStreamReader(in));
                //while ((receivedData = br.readLine()) != null) {
                //    System.out.println(receivedData);
                //}
            }

            while (true) {
                quit = r.readLine();
                if (quit.equals("quit")) {
                    break;
                }

            }
        }
    }

    public static String getCurrency() {
        return null;
    }

    public static String currencyChoice(Socket s, InputStream inStream, OutputStream outStream, PrintStream p, BufferedReader r) throws IOException {
        String input = null;
        p.println("Welche Waehrung moechten Sie eingeben?");
        //while (true) {
            try {
                input = r.readLine();
                p.println("Input received");
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (input.equals("EU") || (input.equals("DM"))) {
                break;
            } else {
                p.println("Falsche Eingabe, bitte geben Sie entweder EU oder DM ein");
            }

        }
        return input;
    }

    public static double amountCheck(Socket s, InputStream inStream, OutputStream outStream, PrintStream p, BufferedReader r) throws IOException {
        double amount = 0;
        String amountStr = null;
        p.println("Welche Wert moechten Sie umrechnen?");
        while (true) {
            try {
                amount = Double.parseDouble(r.readLine());
                break;
            } catch (NumberFormatException ign) {
                System.out.println("Invalid input, please provide a number ");
            }
        }
        return amount;
    }

    public static String confirmCheck(Socket s, InputStream inStream, OutputStream outStream, PrintStream p, BufferedReader r) throws IOException {
        String input = null;
        p.println("Darf es noch eine Umrechnung sein?");
        while (true) {
            //p.println("Welche Waehrung moechten Sie eingeben?");
            input = r.readLine();
            if ((input.equals("Ja")) || (input.equals("ja")) || (input.equals("Nein")) || (input.equals("nein"))) {
                break;
            } else {
                p.println("Falsche Eingabe, bitte geben Sie entweder EU oder DM ein");
            }

        }
        return input;
    }

    public static String testReadMethode(Socket s, InputStream inStream, OutputStream outStream, PrintStream p, BufferedReader r) throws IOException {
        String input = null;
        p.println("Welche Waehrung moechten Sie eingeben?");
        byte[] buffer = new byte[1024];
        int read;
        while ((read = inStream.read(buffer)) != -1) 
        {
            String output = new String(buffer, 0, read);
            System.out.print(output);
            System.out.flush();
        

        if (input.equals("EU") || (input.equals("DM"))) {
            break;
        } else {
            p.println("Falsche Eingabe, bitte geben Sie entweder EU oder DM ein");
        }
        }

    
    return input ;
}

}
