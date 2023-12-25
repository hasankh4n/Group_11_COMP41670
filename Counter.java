package backgammonGroup11;

/**
 * Group Number: 11
 * @author James Duke, Hasan Khan
 * GitHub IDs: jamesduke488, hasankh4n
 * @version 1.1
 */

public class Counter {

	private CounterType c;
	
	Counter(CounterType c) {
		
		this.c = c;
		
	}
	
	public CounterType getType() {
		
		return c;
		
	}
	
	public String toString() {
		
		return c.toString();
		
	}
	
}
