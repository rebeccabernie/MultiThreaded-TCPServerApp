// G00320698 - Rebecca Kane
// Server class - server side functionality
package Server;

import java.io.*;
import java.net.*;

public class Server {

	private ServerSocket serverSock; 
	private static final int PORT = 7777;  
	private volatile boolean keepRunning = true;
	
	public Server()
	{
		try 
		{ 
			serverSock = new ServerSocket(PORT);
			Thread serverThread = new Thread(new Listener(), "Web Server Listener");
			serverThread.setPriority(Thread.MAX_PRIORITY); 
			serverThread.start();
			
			System.out.println("Server started and listening on port " + PORT);
			
		} catch (IOException e) 
		{
			System.out.println("Error - " + e.getMessage());
		}
	} // end private Server method
	
	
	//A main method is required to start a standard Java application
	public static void main(String[] args) {
		//new Server(); //Create an instance of a WebServer. This fires the constructor of WebServer() above on the main stack
		System.out.println("Server running...");
	}
	
	private class Listener implements Runnable {
		
		public void run() { // run() must be implemented
			
			int count = 0; // Amount of requests
			
			while (keepRunning){ 
				try { 
					Socket socket = serverSock.accept();
					new Thread(new HTTPRequest(socket), "T-" + count).start(); 
					count++; // Add to request count
				} catch (IOException e) { 
					System.out.println("Error - " + e.getMessage());
				}
			} // End while
			
		} // End run()
		
	}// End Listener class
	
	private class HTTPRequest implements Runnable{
		private Socket sock; //A specific socket connection - different to socket or serverSock

		private HTTPRequest(Socket request) { 
			this.sock = request; // Value for request is assigned to this instance of sock
		}

		public void run() { // Again, run() must be implemented
            try{
            	//System.out.println("Server connected."); - displays after the prompt to the user, just commented out to avoid any confusion
            	System.out.print("");
            } catch (Exception e) { 
            	System.out.println("Error processing request from " + sock.getRemoteSocketAddress());
            	e.printStackTrace();
            }
        } // End run()
		
	} // End HTTPRequest class
	
} // End Server class
