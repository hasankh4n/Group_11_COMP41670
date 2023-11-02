//Name: display
//Author: 
//Version: 1.0
//About: File detailing the logic behind displaying the board to user and text outputs

import java.util.*;
import java.util.Scanner;


public class display {
    
    Scanner in;
    command command;

    display() {
        in = new Scanner(System.in);
    }

    public void info() {
		System.out.println("\n" + "To play , use 'R' to roll the dice");
        System.out.println("Enter 'Q' to quit!");
	}

    // Display the current state of the game board
	//public void showBoard(board board) {
		
	//	System.out.println("13--+---+---+---+---18 BAR 19--+---+---+---+---24 OFF");
	//	System.out.println("\n");
    //   System.out.println("13--+---+---+---+---18 BAR 19--+---+---+---+---24 OFF");
    //
	//}

    public void getPlayerNames (board board) { 
		boolean validInput = false;

		System.out.print("Enter name of player 1: ");
        String player1Name = in.nextLine();

        System.out.print("Enter Player 2's name: ");
        String player2Name = in.nextLine();

        System.out.println("Player 1's name is: " + player1Name);
        System.out.println("Player 2's name is: " + player2Name);
		
	}

    // Prompt user for input and return validated input command
	public command getUserInput () {
		boolean commandEntered = false;
		do {
			System.out.print("Please enter command: ");
			String userInput = in.nextLine();
			if (command.isValid(userInput)) {
				command = new command(userInput);
				commandEntered = true;
			} else {
				System.out.println("Enter a valid input");
			}
		}
		while (!commandEntered);
		return command;
	}
    	// Get the recentinput command
	public command getCommand () {
		return command;
	}


    // Display a message for an invalid command
	public void displayCommandNotPossible () {
		System.out.println("Please input a valid command!");
	}
    // Display a message when the game is won
	public void displayGameOver () {
		System.out.println("Game over!");
	}
	// Display a message when the user quits the game
	public void displayQuit () {
		System.out.println("Exiting Backgammon...");
	}
}
