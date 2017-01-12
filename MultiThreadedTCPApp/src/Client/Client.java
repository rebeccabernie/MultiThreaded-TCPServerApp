// G00320698 Rebecca Kane
// Client Class based on "Requester" file on moodle, lecturer Martin Hynes / Operating Systems 1

package Client;

import java.io.*;
import java.net.*;
import java.util.Scanner;
public class Client{
	Socket requestSocket;
	ObjectOutputStream out;
 	ObjectInputStream in;
 	String message;
 	Scanner user_input;
	Client()
	{
		user_input = new Scanner(System.in);
	}
	void run()
	{
		try{
			//1. creating a socket to connect to the server
			
			requestSocket = new Socket("127.0.0.1", 2010);
			System.out.println("Connected to localhost in port 2004");
			//2. get Input and Output streams
			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(requestSocket.getInputStream());
			//3: Communicating with the server
			do{
				try{
					message = (String)in.readObject();
					System.out.println(message);
					message = user_input.next();
					sendMessage(message);
					
					message = (String)in.readObject();
					System.out.println(message);
					message = user_input.next();
					sendMessage(message);
					
					message = (String)in.readObject();
					System.out.println(message);
					message = user_input.next();
					sendMessage(message);
			
					message = (String)in.readObject();
					System.out.println("Result: "+message);
					
					System.out.println("Would you like to do another calulation.... Enter Yes or No");
					
					message = user_input.next();
					if(message.compareTo("No")==0)
					{
						sendMessage("Thank You!");
					
						message = (String)in.readObject();
					
					}
					
					else
					{
						sendMessage("Continue");
					}
					
					
				}
				catch(ClassNotFoundException classNot){
					System.err.println("data received in unknown format");
				}
			}while(!message.equals("Thank You!"));
		}
		catch(UnknownHostException unknownHost){
			System.err.println("You are trying to connect to an unknown host!");
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
		finally{
			//4: Closing connection
			try{
				in.close();
				out.close();
				requestSocket.close();
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
			System.out.println("client>" + msg);
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	public static void main(String args[])
	{
		Client client = new Client();
		client.run();
	}
}