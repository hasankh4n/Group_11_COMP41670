package backgammonGroup11;

/**
 * Group Number: 11
 * @author James Duke, Hasan Khan
 * GitHub IDs: jamesduke488, hasankh4n
 * @version 1.1
 */

public enum CounterType {
	
	PLAYER1(ConsoleColors.WHITE + "X"),
	PLAYER2(ConsoleColors.RED + "O");
	
	private final String s;
	
	CounterType(String s) {
		
		this.s = s;
		
	}
	
	public String toString() {
		
		return s;
		
	}
	
}
