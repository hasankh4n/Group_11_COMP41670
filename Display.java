package BackgammonTest;

//Name: display
//Author: 
//Version: 1.0
//About: File detailing the logic behind displaying the board to user and text outputs

import java.util.Scanner;

public class Display {
  
  Scanner in;
  Command command;

  Display() {
      in = new Scanner(System.in);
  }

  public void info() {
      
	  System.out.println("\n" + "Welcome to Backgammon");
      System.out.println("\n" + "To play , use 'R' to roll the dice");
      System.out.println("Enter 'Q' to quit!");
      System.out.println("If you want to know the rest of the possible commands, enter 'H' for a hint\n");
	
  }
  
  public void hint() {
	  
	  System.out.println("\n" + "Enter 'R' to roll the dice and see your allowed moves");
      System.out.println("\nIf you want to set the dice faces to specific values, enter R' followed by the desired numbers");
      System.out.println("For example: Entering 'R12' sets the dices to 1 and 2 respectively\n");
      System.out.println("Enter the number value of the move you wish to make i.e '1' for move 1)\n");
      System.out.println("Enter 'P' to see the pip count for both players\n");
      System.out.println("Enter 'D' to offer your opponent a double");
      System.out.println("When offered a double, the player must respond by entering either 'Y' to accept or 'N' to refuse\n");
      System.out.println("Enter 'Q' to quit the game\n");

	  
  }

  public void getPlayerNames (Player p1, Player p2) { 
	  
	  boolean validInput = false;

      String player1Name = "";
      String player2Name = "";

      // Player Name validation
      while (validInput == false) {
    	  
          System.out.print("Enter name of Player 1: ");
          
	          player1Name = in.nextLine();
	
	          if (player1Name.matches("[a-zA-Z]+")) {
	              
	        	  validInput = true;
	        	  
	        	  p1.setPlayerName(player1Name);
	        	  
	          } 
	          
	          else {
	        	  
	              System.out.println("Invalid input. Please enter only letters.");
	              
	          }
      
      }
      
      validInput = false;

      while (validInput == false) {
    	  
          System.out.print("Enter name of Player 2: ");
          
          player2Name = in.nextLine();

          if (player2Name.matches("[a-zA-Z]+")) {
        	  
        	  validInput = true;
        	  
        	  p2.setPlayerName(player2Name);
              
          } 
          
          else {
        	  
              System.out.println("Invalid input. Please enter only letters.");
              
          }
      }

      System.out.println("\n" + ConsoleColors.WHITE_BOLD_BRIGHT + "Player 1's name is: " + p1.getPlayerName());
      System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Player 2's name is: " + p2.getPlayerName() + "\n");		
	
  }
  
  public void getMatchLength(Score s) {
	  
	  boolean validLength = false;
	  
	  int matchLength = 0;
	  
	  do {
		  
		  System.out.print(ConsoleColors.YELLOW_BOLD_BRIGHT + "Enter the length of the match: "); 
		  
		  if (in.hasNextInt()) {
			  
			  validLength = true;
			  
			  matchLength = in.nextInt();
			  
		  }
		  
		  else {
			  
			  System.out.println("Invalid input. Please enter an integer number value.");
			  
		  }
		  
	  } while (!validLength);
	  
	  s.setMatchLength(matchLength);
	  
  }
  
  public void offerDouble(Player currentPlayer) {
	  
	  System.out.println("\n" + currentPlayer.getPlayerName() + " has offered a double. Would you like to accept?\n");
	  
	  System.out.println("\nEnter 'Y' to accept or 'N' to decline: \n");
	  
  }
  
  // Prompt user for input and return validated input command
  public Command getUserInput() {
		
		boolean commandEntered = false;
		
		do {
			System.out.print("Please enter command: ");
			
			
			
			String userInput = in.nextLine();
			
			if (Command.isValid(userInput)) {
				
				command = new Command(userInput);
				commandEntered = true;
				
			} 
			
			else {
				
				System.out.println("Enter a valid input");
				
			}
			
		}
		
		while (!commandEntered);
		
		return command;
	}
  
  	// Get the recent input command
	public Command getCommand () {
		
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