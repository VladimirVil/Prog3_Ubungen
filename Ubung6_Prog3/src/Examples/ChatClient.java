/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examples;

import java.io.*;
import java.net.*;

/**
 *
 * @author Admin
 */
public class ChatClient {
    public static void main (String[] args)
    {
        try{
            Socket s = new Socket("127.0.0.1", 1201);
            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
            String msgin = "";
            String msgout = "";
            while(!msgin.equals("end"))
            {
                msgout=br.readLine();
                dout.writeUTF(msgout);
                msgin=din.readUTF();
                System.out.println(msgin);  // printing server message 
            }
        } catch (Exception e)
        {
            
        }
    }
    
}
