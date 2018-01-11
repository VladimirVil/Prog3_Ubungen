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
import java.net.SocketException;
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
        //String confirmationAnswer = null;
        boolean confirmationAnswer = false;
        String quit = null;
        final String localHost = "127.0.0.1";
        final int port = 8888;
        String currentTime = null;
        KeyboardServer keyboard = new KeyboardServer();
        keyboard.start();

        ServerSocket listener = new ServerSocket(port);

        while (keyboard.available()) {
            try {
            Socket socket = listener.accept();

            InputStream inStream = socket.getInputStream();
            OutputStream outStream = socket.getOutputStream();
            // attach print stream to send the data
            PrintStream p = new PrintStream(outStream);
            //BufferedReader r = new BufferedReader(new InputStreamReader(inStream));  //The original
            BufferedReader r = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //BufferedReader r = new BufferedReader (new InputStreamReader(System.in));
            cleanInputFromPutty(socket, inStream, outStream, p, r);
            p.println("Connection established succesfully ");
            System.out.println("Connection established succesfully");
            
            while (true) {
                System.out.println("The input is : " + waehrung);
                //waehrung = testReadMethode(socket, inStream, outStream, p, r);
                
                waehrung = currencyChoice(socket, inStream, outStream, p, r);  //check the correctness of the currency choice 
                //p.println("Welche Wert wollen Sie umrechnen?");
                /*amountToExchangeStr = r.readLine();
                amountToExchange = Double.parseDouble(amountToExchangeStr); */
                amountToExchange = amountCheck(socket, inStream, outStream, p, r);
                if (waehrung.equals("EU")) {
                    p.println("The amount in DM is: " + amountToExchange * (EU2DM));
                } else if (waehrung.equals("DM")) {
                    p.println("The amount in EU is: " + amountToExchange * (DM2EU));
                }
                //p.println("Darf es noch eine Umrechnung sein?");
                confirmationAnswer = confirmCheck(socket, inStream, outStream, p, r);
                if (confirmationAnswer){
                    socket.close();
                    break;
                }

            }

          /*  while (true) {
                quit = r.readLine();
                if (quit.equals("quit")) {
                    break;
                } 
 
            }*/
            }
            catch(SocketException ex) {
                System.out.println("Socket closed by client.");
            }
        }
    }

    public static String getCurrency() {
        return null;
    }
    
    public static void cleanInputFromPutty(Socket s, InputStream inStream, OutputStream outStream, PrintStream p, BufferedReader r) throws SocketException
    {
        p.println("Cleaning input from putty....please press enter to continue");
        System.out.println("Cleaning input from putty....please press enter to continue - Debug purposes");
        String input = null;
        
        try {
            input = r.readLine();
        }
        catch (SocketException ex) {
            throw ex;
        }
        catch (IOException ex) {
            Logger.getLogger(ArbServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        p.println("Cleaned ");
        
    }

    public static String currencyChoice(Socket s, InputStream inStream, OutputStream outStream, PrintStream p, BufferedReader r) throws IOException {
        String input = null;
        p.println("Welche Waehrung moechten Sie eingeben?");
        while (true) {
            try {
                input = r.readLine();
                p.println("Input received");
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (input != null) {
                if (input.equals("EU") || (input.equals("DM"))) {
                    break;
                } else {
                    p.println("Falsche Eingabe, bitte geben Sie entweder EU oder DM ein");
                }
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

    public static boolean confirmCheck(Socket s, InputStream inStream, OutputStream outStream, PrintStream p, BufferedReader r) throws IOException {
        String input = null;
        boolean result=false;
        p.println("Darf es noch eine Umrechnung sein?");
        while (true) {
            //p.println("Welche Waehrung moechten Sie eingeben?");
            input = r.readLine();
            if ((input.equals("Ja")) || (input.equals("ja"))) {
                result = false;
                break;
                
            } 
            else if ((input.equals("Nein")) || (input.equals("nein"))) {
                result=true;
                break;
            }
            else {
                p.println("Falsche Eingabe, bitte geben Sie entweder EU oder DM ein");
            }

        }
        return result;
    }

}
