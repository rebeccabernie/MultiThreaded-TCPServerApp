package Client;

import java.util.Scanner;

public class Runner {

	static Scanner console = new Scanner(System.in);
	private static int choice = 0;
	private static int loggedInChoice = 0;
	
	public static void main(String[] args) {
			
		System.out.println("1. Register");
		System.out.println("2. Log In");
		System.out.println("\nSelect option 1 or 2 please: ");
		choice = console.nextInt();
		
		if(choice == 1){
			//register
				//name address acnum username password
		} // End choice 1			
	
		else if(choice == 2){
			//log in username password
			
			// If username password = correct do this
				System.out.println("What would you like to do?");
				System.out.println("1. Change Details");
				System.out.println("2. Make Lodgement");			System.out.println("1. Register");
				System.out.println("3. Make Withdrawal");
				System.out.println("4. View Last 10 Transactions");
				System.out.println("Select an option: ");
				loggedInChoice = console.nextInt();
				
				//if else statements here
			
			// if username password = wrong
				System.out.println("Error, wrong details provided...");


		} // End choice 2
		
		else { // just in case user tries an invalid option
			System.out.println("Please enter a valid option...");
			choice = console.nextInt(); // Allow user to enter an option again	
		}
		
	} // end main
}