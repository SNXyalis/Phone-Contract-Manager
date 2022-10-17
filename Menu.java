package it21966;

import java.util.Scanner;

/**  Menu is a class that consists of message methods
 * And the main menu method which is responsible for the desired functions
 * of the program. The implementations of these functions can be found in 
 * the Following classes: CustomerDatabase
 * 
 * @author it21966
 */

public class Menu {
	private boolean status;
	private JavaPhoneDatabase javaphoneDatabase = new JavaPhoneDatabase();
	private Scanner input = new Scanner(System.in);
	
	//Menu Class constructor that initializes the status value
	public Menu() {
		status = true;
	}
	
	/**
	 * Initialize the program and takes action depending on the choice
	 * 
	 */
	public void mainMenu() {
		this.greet();
		
		javaphoneDatabase.createExamples();
		
		int choice = 0;
		
		while(status == true) {
			
			this.printOptions();
			
			do {
				System.out.println("Type a number: ");
                                if(input.hasNextInt()) {
                                    choice = input.nextInt();
                                }else {
                                    System.out.println("Invalid data type.");
                                    System.out.println("Shutting down.");
                                    return;
                                }
			}while(choice < 1 && choice > 5);
			
			this.processChoice(choice);
		}
		input.close(); //Close the scanner before ending the program
	}
	
	//Prints a greeting message
	public void greet() {
		System.out.println();
		System.out.println("---Welcome to Javaphone---");
		System.out.println();
	}
	
	//Prints an exit message 
	public void exitMessage() {
		System.out.println();
		System.out.println("Program closed successfully.");
	}
	
	//Prints menu options
	public void printOptions() {
		System.out.println("Options: ");
		System.out.println();
		System.out.println("1. Enter a customer profile.");
		System.out.println("2. Enter a new contract.");
		System.out.println("3. Delete a contract.");
		System.out.println("4. Show active contracts and statistics.");
		System.out.println("5. Exit.");
		System.out.println();
	}
	
	//Process the choice
	public void processChoice(int choice) {
		switch(choice) {
		case 1:
			javaphoneDatabase.createCustomer();
			break;
		case 2:
			javaphoneDatabase.newContract();
			break;
		case 3:
			javaphoneDatabase.deleteContract();
			break;
		case 4:
			javaphoneDatabase.printActiveContractsAndStatistics();
			break;
		case 5:
			this.exitMessage();
			status = false;
			break;
		default:
			this.errorChoice();
		}
	}
	
	//Prints an error message in case the user enters an invalid choice
	public void errorChoice() {
		System.out.println("Invalid option.");
		System.out.println("Please enter a number between 1 and 5");
	}
	
}
