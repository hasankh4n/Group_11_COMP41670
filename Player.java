package BackgammonTest;

/**
 * Group Number: 11
 * @author James Duke, Hasan Khan
 * GitHub IDs: jamesduke488, hasankh4n
 * @version 1.1
 */

public class Player {

	private CounterType ctype;
	private String colour;
	private int number;
	private String name;
	
	Player (int playerNumber) {
		
		if (playerNumber == 1) {
			
			number = 1;
			ctype = CounterType.PLAYER1;
			colour = ConsoleColors.WHITE_BACKGROUND;
			name = null;
			
		}
		
		else if (playerNumber == 2) {
			
			number = 2;
			ctype = CounterType.PLAYER2;
			colour = ConsoleColors.RED_BACKGROUND;
			name = null;
			
		}
		
		else {
			
			System.out.println("Player number invalid");
			
		}
		
		
	}
	
	public int getPlayerNumber() {
		
		return number;
		
	}
	
	public CounterType getCounterType() {
		
		return ctype;
		
	}
	
	public String getColour() {
		
		return colour;
		
	}
	
	public String getPlayerName() {
		
		return name;
		
	}
	
	public void setPlayerName(String n) {
		
		name = n;
		
	}
	
}
