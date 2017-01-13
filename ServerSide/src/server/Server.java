package server;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server{
	// Basic behind the scenes variables
	ServerSocket providerSocket;
	Socket connection = null;
	ObjectOutputStream out;
	ObjectInputStream in;
	Scanner input;
	String message;
	String console;
	
	
	// UI based variables
	String name, address;
	String username, password;
	int accountNo;
	int choice, loggedInChoice;
	float balance = 100, transactionAmt;
	
	
	Server()
	{
		input = new Scanner(System.in);
	}
	void listener()
	{
		try{
			//1. creating a server socket
			providerSocket = new ServerSocket(2010, 10);
			//2. Wait for connection
			System.out.println("Waiting for connection");
			
			connection = providerSocket.accept();
			
			System.out.println("Connection received from " + connection.getInetAddress().getHostName());
			//3. get Input and Output streams
			out = new ObjectOutputStream(connection.getOutputStream());
			out.flush();
			in = new ObjectInputStream(connection.getInputStream());
			
			//4. The two parts communicate via the input and output streams
			do{
				try{
					/*
					sendMessage("Your balance is: " + balance);
					message = (String)in.readObject();*/

					sendMessage("Would you like to...\n1. Make a Withdrawal\n2. Make A Lodgement");
					message = (String)in.readObject();
					choice = new Integer(message);
			
					if(choice == 1){
						sendMessage("How much would you like to withdraw?");
						message = (String)in.readObject();
						transactionAmt = new Float(message);
						
						if ((balance-transactionAmt) > -1000){
							balance = balance - transactionAmt;
							sendMessage("Your balance is now: " + balance);
						}
						else{
							sendMessage("Oops! You have too much of an overdraft...");
							sendMessage("Your balance is: " + balance);
						}
						
					}
					
					else if(choice==2){
						sendMessage("How much would you like to lodge?");
						message = (String)in.readObject();
						transactionAmt = new Float(message);
						
						balance = balance + transactionAmt;
						sendMessage("Your balance is now: " + balance);
						
						sendMessage("Would you like to...\n1. Make a Withdrawal\n2. Make A Lodgement");
						message = (String)in.readObject();
						choice = new Integer(message);
							
					}
					
					else{
						sendMessage("Oops! Please enter a valid option...");
						message = (String)in.readObject();
						choice = new Integer(message);
					}
					
					message=(String)in.readObject();
					
					if(message.compareTo("Withdraw")==0)
						sendMessage("Withdraw");
				}
				catch(ClassNotFoundException classnot){
					System.err.println("Data received in unknown format");
				}
			}while(!message.equals("Thank You!"));
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
		finally{
			//4: Closing connection
			try{
				in.close();
				out.close();
				providerSocket.close();
			}
			catch(IOException ioException){
				ioException.printStackTrace();
			}
		}
	}
	void sendMessage(String msg)
	{
		try{
			out.writeObject(msg);
			out.flush();
			System.out.println("server>" + msg);
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	public static void main(String args[])
	{
		Server server = new Server();
		while(true)
		{
			server.listener();
		}
	}
}


/* Bypassing log in for now...
sendMessage("1. Register\n2.Log In");
message = (String)in.readObject();
choice = new Integer(message);

if(choice == 1){
	sendMessage("Name: ");
	message = (String)in.readObject();
	name = new String(message);
	
	sendMessage("Address: ");
	message = (String)in.readObject();
	address = new String(message);
	
	sendMessage("A/C Num: ");
	message = (String)in.readObject();
	accountNo = new Integer(message);
	
	sendMessage("Username: ");
	message = (String)in.readObject();
	username = new String(message);
	
	sendMessage("Password: ");
	message = (String)in.readObject();
	password = new String(message);
}

else if(choice == 2){
	sendMessage("Username: ");
	message = (String)in.readObject();
	username = new String(message);
	
	sendMessage("Password: ");
	message = (String)in.readObject();
	password = new String(message);
}

else{
	sendMessage("Oops! Please enter a valid option...");
	message = (String)in.readObject();
	choice = new Integer(message);
}
*/