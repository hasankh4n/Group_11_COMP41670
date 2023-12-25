import java.util.Stack;

/**
 * Group Number: 11
 * @author James Duke, Hasan Khan
 * GitHub IDs: jamesduke488, hasankh4n
 * @version 1.1
 */

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

		public Counter getSpecCounter(int idx) {
		
		return counters.get(idx);
		
	}
	
}
