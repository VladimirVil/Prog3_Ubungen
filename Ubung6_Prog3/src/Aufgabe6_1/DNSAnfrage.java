/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aufgabe6_1;
import java.net.*;
import java.net.InetAddress;

/**
 *
 * @author Admin
 */
public class DNSAnfrage {
    public static void main (String[] args) throws UnknownHostException {
        try
        {
            InetAddress ip =  InetAddress.getByName(args[0]);
            System.out.println("Angefragter Name:" + args[0]);
            System.out.println("IP Addresse: " + ip.getHostAddress());
            System.out.println("Hot-Name: " + ip.getHostName());
            
        } catch (ArrayIndexOutOfBoundsException aex) {
            System.out.println("Aufruf: java DNS Anfrage <hostname>");
        }
       /* catch (UnknownHostException aex) {
            System.out.println("Kein DNS Antrage fuer " + args[0]);
    } */
        
    }
    
}
