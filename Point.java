
import java.util.Stack;

public class Point {

	protected Stack<Counter> counters;
	private Counter c;
	
	Point() {
		
		counters = new Stack<>();
				
	}
	
	Point(int i, Counter c) {
		
		counters = new Stack<>();
		this.c = c;
		
		for (int x = 0; x < i; x++) {
			
			counters.add(c);
			
		}
		
	}
	
	public boolean canTake(Counter c) {
		
		return false;
		
	}
	
	public Stack<Counter> getCounters() {
		
		return counters;
		
	}
	
}
