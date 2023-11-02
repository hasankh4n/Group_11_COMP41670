

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
