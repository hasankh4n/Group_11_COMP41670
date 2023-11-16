
public class Player {

	private CounterType ctype;
	private String colour;
	
	Player (int playerNumber) {
		
		if (playerNumber == 1) {
			
			ctype = CounterType.PLAYER1;
			colour = ConsoleColors.RED_BACKGROUND;
			
		}
		
		else if (playerNumber == 2) {
			
			ctype = CounterType.PLAYER2;
			colour = ConsoleColors.WHITE_BACKGROUND;
			
		}
		
		else {
			
			System.out.println("Player number invalid");
			
		}
		
		
	}
	
	public CounterType getCounterType() {
		
		return ctype;
		
	}
	
public String getColour() {
		
		return colour;
		
	}
	
}
