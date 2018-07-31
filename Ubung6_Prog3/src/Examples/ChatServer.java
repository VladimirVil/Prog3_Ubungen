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
public class ChatServer {
    public static void main (String[] args) {
        try{
            ServerSocket ss = new ServerSocket(1201);
            Socket s = ss.accept();
            
            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
            String msgin = "";
            String msgout= "";
            
            while (!msgin.equals("end"))
            {
                msgin=din.readUTF();
                System.out.println(msgin);  // printing client message 
                msgout=br.readLine();
                dout.writeUTF(msgout);
                dout.flush();
            }
            s.close();
            
        }catch (Exception e )
        {
            
        }
    }
    
}
