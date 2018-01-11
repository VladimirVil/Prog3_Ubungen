/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examples;

/**
 *
 * @author Admin
 */
import java.net.*;
import java.io.*;

public class ServerExample {
 public static void main(String[] args) throws IOException {

  ServerSocket serverSocket = null;
  try {
    serverSocket = new ServerSocket(1234);
} catch (IOException e) {
    System.err.println("Could not listen on port: 1234.");
    System.exit(1);
}

Socket clientSocket = null;
try {
    clientSocket = serverSocket.accept();
} catch (IOException e) {
    System.err.println("Accept failed.");
}
 System.out.println("Connected");


PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

String textFromClient =null;
String textToClient =null;
textFromClient = in.readLine(); // read the text from client
if( textFromClient.equals("A")){
   textToClient = "1111";
}else if ( textFromClient.equals("B")){
   textToClient = "2222\r\n3333";
}


out.print(textToClient + "\r\n");  // send the response to client
 out.flush();
 out.close();
 in.close();
 clientSocket.close();
 serverSocket.close();
 }
}