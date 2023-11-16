import java.util.ArrayList;
import java.util.Stack;

public class Board {

	public final static int QUAD_SIZE = 6;
	
	private ArrayList<Point> home1, outer1, home2, outer2;
	private Stack<Point> initialCounters;
	private Counter x, o;
	
	
	Board() {
		
		home1 = new ArrayList<Point>(QUAD_SIZE);
		outer1 = new ArrayList<Point>(QUAD_SIZE);
		home2 = new ArrayList<Point>(QUAD_SIZE);
		outer2 = new ArrayList<Point>(QUAD_SIZE);
		
		x = new Counter(CounterType.PLAYER1);
		o = new Counter(CounterType.PLAYER2);
		
		for (int j = 0; j < QUAD_SIZE; j++) {
			
			if (j == 0) {
				
				home1.add(new Point(5,x));
				outer1.add(new Point(5,x));
				home2.add(new Point(5,o));
				outer2.add(new Point(5,o));
			}
			
			else if (j == 4) {
				
				home1.add(new Point());
				home2.add(new Point());
				outer1.add(new Point(3,o));
				outer2.add(new Point(3,x));
				
			}
			
			else if (j == 5) {
				
				home1.add(new Point(2,o));
				home2.add(new Point(2,x));
				outer1.add(new Point());
				outer2.add(new Point());
				
			}
			
			else {
			
				home1.add(new Point());
				outer1.add(new Point());
				home2.add(new Point());
				outer2.add(new Point());
				
			}
			
		}
		
		
	}
	
	public void whosFIrst(Dice dice) {
		
		System.out.println(ConsoleColors.RESET + "Roll to see who's first: \n");

		int firstRoll[] = new int[2];
		
		do {
			
			dice.roll();
			
			firstRoll = dice.getFaces();
			
			dice.showFaces();
		
			if (firstRoll[0] == firstRoll[1]) {
				
				System.out.println(ConsoleColors.RESET + "Need to roll again\n" + ConsoleColors.RESET);
				
			}
			
			else if (firstRoll[0] > firstRoll[1]) {
				
				System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Player 1 goes first!\n" + ConsoleColors.RESET);
				
			}
			
			else {
				
				System.out.println(ConsoleColors.RED + "Player 2 goes first!\n" + ConsoleColors.RESET);
				
			}
		
		} while (firstRoll[0] == firstRoll[1]);
		
		
	}
	
	public void printBoard() {
		
		System.out.println(ConsoleColors.YELLOW_BACKGROUND_FULL + "-------------------------------------------------------------------------------------------------" + ConsoleColors.RESET);

		//System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

		for (int y = 13; y < 19; y++) {
			
			System.out.print(y + "\t");
			
		}
		
		System.out.print(ConsoleColors.YELLOW_BACKGROUND + "   " + ConsoleColors.RESET + " \t");		
		for (int y = 19; y < 25; y++) {
			
			System.out.print(y + "\t");
			
		}
		
		System.out.print(ConsoleColors.YELLOW_BACKGROUND_FULL + "\n-------------------------------------------------------------------------------------------------\n" + ConsoleColors.RESET);
		
		for (int j = 0; j < 5; j++) {
			
			//PLAYER 2 OUTER PRINT
			
			int n = 0;

			while (n < QUAD_SIZE) {
				
				if ((outer2.get(n).getCounters().size()-1) < j) {
					
					System.out.print(ConsoleColors.GREEN + "|" + "\t");
					
				}
				
				else {
				
					System.out.print(outer2.get(n).getCounters().get(j) + "\t");
				
				}
				
				n++;
				
			}

			System.out.print(ConsoleColors.YELLOW_BACKGROUND + "   " + ConsoleColors.RESET + " \t");			
			//PLAYER 2 OUTER PRINT
			
			int p = 0;

			while (p < QUAD_SIZE) {
				
				if ((home2.get(p).getCounters().size()-1) < j) {
					
					System.out.print(ConsoleColors.GREEN + "|" + "\t");
					
				}
				
				else {
				
					System.out.print(home2.get(p).getCounters().get(j) + "\t");
				
				}
				
				p++;
				
			}
			
			System.out.print("\n");
						
		}
		
		System.out.print(ConsoleColors.YELLOW_BACKGROUND_FULL + "-------------------------------------------------------------------------------------------------\n" + ConsoleColors.RESET);

		for (int k = 4; k >= 0; k--) {
			
			//PLAYER 2 OUTER PRINT
			
			int n = 0;

			while (n < QUAD_SIZE) {
				
				if ((outer1.get(n).getCounters().size()-1) < k) {
					
					System.out.print(ConsoleColors.GREEN + "|" + "\t");
					
				}
				
				else {
				
					System.out.print(outer1.get(n).getCounters().get(k) + "\t");
				
				}
				
				n++;
				
			}
			
			System.out.print(ConsoleColors.YELLOW_BACKGROUND + "   " + ConsoleColors.RESET + " \t");
			
			//PLAYER 2 OUTER PRINT
			
			int p = 0;

			while (p < QUAD_SIZE) {
				
				if ((home1.get(p).getCounters().size()-1) < k) {
					
					System.out.print(ConsoleColors.GREEN + "|" + "\t");
					
				}
				
				else {
				
					System.out.print(home1.get(p).getCounters().get(k) + "\t");
				
				}
				
				p++;
				
			}
			
			System.out.print("\n");
						
		}
		
		System.out.println(ConsoleColors.YELLOW_BACKGROUND_FULL + "-------------------------------------------------------------------------------------------------" + ConsoleColors.RESET);

		//System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

		for (int y = 12; y > 6; y--) {
			
			System.out.print(y + "\t");
			
		}
		
		System.out.print(ConsoleColors.YELLOW_BACKGROUND + "   " + ConsoleColors.RESET + " \t");
		
		for (int y = 6; y > 0; y--) {
			
			System.out.print(y + "\t");
			
		}
		
		System.out.print(ConsoleColors.YELLOW_BACKGROUND_FULL + "\n-------------------------------------------------------------------------------------------------\n");

	}
	
	/*public static void main(String...args) {
		
		Board b = new Board();
		
		b.printBoard();
		
		
	}*/
	
	
}
