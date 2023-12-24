package BackgammonTest;

import java.util.Scanner;

public class Backgammon {

	 public static void main (String... args){
	        
		 boolean playMore = false; //Boolean to check if players want to play another match at end
		 
		 	//Create new match
		 
			 do {
			 
			 	Player player1 = new Player(1);
			 	Player player2 = new Player(2);
			 	Player currentPlayer = player1;
			 	Board board = new Board(player1, player2);
		        Display display = new Display();
		        Dice dice = new Dice();
		        Command command;
		        
		        display.info(); //Display welcome message with info on initial commands
	
	            display.getPlayerNames(player1, player2); //Get players to select names
	            
	            display.getMatchLength(board.getScore()); //Get players to choose a match length
		        
		        boolean isMatchOver = false; //Check to see if someone has won the match
	            
	            do {
	            
		            int first = board.whosFIrst(dice); //Initial role to see who goes first
		
		            //Set winner of roll as current player
		            
		        	if (first == 1) {
			            	
		        		currentPlayer = player1;
			            	
			        }
			            
			        else if (first == 2) {
			            	
				      currentPlayer = player2;
			            	
			        }
		        	
			        do {
			            	        	
			        	// Display the current board state 
			            board.printBoard(currentPlayer);
			            
			            boolean commandIn = false; //boolean to check if a turn ending command has been input (player quits or makes a move)
			  
			            do{
			                
			            	command = display.getUserInput();
			                
			            	if (command.quit()) {
			                	
			                    commandIn = true;
			                    
			                } 
			                
			                else if (command.roll())
			                {
			                    
			                	dice.roll();
			                	
			                	board.showLegalMoves(dice, currentPlayer);
			                    
			                } 
			                
			                else if (command.pip()) {
			                	
			                	board.showPipCounts(player1, player2);
			                	
			                }
			            	
			                else if (command.hint()) {
			                	
			                	display.hint();
			                	
			                }
			            	
			                else if (command.offerdouble()) {
			                	
			                	if (board.getScore().canOfferDouble(currentPlayer)) {
			                		
			                		if (display.offerDouble(currentPlayer)) {
			                			
			                			board.getScore().doubleAccepted(player1, player2);
			                			
			                		}
			                		
			                		else {
			                			
			                			commandIn = true;
			                			
			                		}
			                		
			                	}
			                	
			                }
			        
			                else {
			                	
			                    display.displayCommandNotPossible();
			                    
			                }
		
			            } while (!commandIn);
			            
			            //Once turn is finished, switch players
			            
			            if (currentPlayer.getPlayerNumber() == 1) {
			            	
			        		currentPlayer = player2;
				            	
				        }
				            
				        else if (currentPlayer.getPlayerNumber() == 2) {
				            	
					      currentPlayer = player1;
				            	
				        }
			            
			        } while (!command.quit() && !board.isGameOver(player1, player2) && (!command.offerdouble() && !display.offerDouble(currentPlayer))); //Game continues with players making turns until either someone quits or the game is over and someone wins
			        
			        //Decide winner
			        Player winner = new Player(1);
			        Player loser = new Player(2);
			        
			        if (currentPlayer == player1) {
			        	
			        	winner = player2;
			        	loser = player1;
			        }
			        
			        else {
			        	
			        	winner = player1;
			        	loser = player2;
			        }
			        			        
			        // when game is over display the following 
			        if (board.isGameOver(player1, player2)){
			            
			        	Score score = board.getScore();
			        	
			        	score.calcFinalScore(winner, loser, board);
			        	
			        	int[] playerScores = score.getScores();
			        	
			        	if (winner == player1 && playerScores[0] >= score.getMatchLength()) {
			        		
			        		System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "\n" + winner.getPlayerName() + "wins the match!");
							
							System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "\nYou scored " + playerScores[0] + " points");
							
							System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "\nHard luck, " + loser.getPlayerName() + ". You scored" + playerScores[1] + " points");
			        		
							isMatchOver = true;
							
			        	}
			        	
			        	else if (winner == player1) {
			        		
			        		System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "\n" + winner.getPlayerName() + "wins the game!");
							
							System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "\nYou now lead by " + playerScores[0] + "-" + playerScores[1] + "points");
							
							System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "\nLet's go again!");
			        		
			        	}
			        	
			        	if (winner == player2 && playerScores[1] >= score.getMatchLength()) {
			        		
			        		System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "\n" + winner.getPlayerName() + "wins the match!");
							
							System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "\nYou scored " + playerScores[1] + " points");
							
							System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "\nHard luck, " + loser.getPlayerName() + ". You scored" + playerScores[0] + " points");
			        		
							isMatchOver = true;
							
			        	}
			        	
			        	else if (winner == player2) {
			        		
			        		System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "\n" + winner.getPlayerName() + "wins the game!");
							
							System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "\nYou now lead by " + playerScores[1] + "-" + playerScores[0] + "points");
							
							System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "\nLet's go again!");
			        		
			        	}
			        	
			        	
			        	
			        } 
			        
			        else {
			        	
			            display.displayQuit();
			            
			            isMatchOver = true;
			            
			        }
			        
	            } while (!isMatchOver);
		        
		        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "\nThanks for playing!");
				
				System.out.println("Enter 'N' to start a new match, or enter any other input to exit Backgammon:");
				
				Scanner in = new Scanner(System.in);
				
				String playAgain = in.next();
				
				if (playAgain.equals("N") || playAgain.equals("n")) {
					
					playMore = true;
					
				}
				
				else {
					
					playMore = false;
					
				}
				
			 } while (playMore);
		 
		 
	 }
	
}
