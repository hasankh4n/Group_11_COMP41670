

public enum CounterType {

	PLAYER1("X"),
	PLAYER2("O");
	
	private final String s;
	
	CounterType(String s) {
		
		this.s = s;
		
	}
	
	public String toString() {
		
		return s;
		
	}
	
}
