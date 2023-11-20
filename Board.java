package BackgammonTest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

public class Board {

	public final static int QUAD_SIZE = 6;
	public final static int POINT_SIZE = 5;
	public final static int NUM_POINTS = 24;

	private ArrayList<Point> home1, outer1, home2, outer2;
	public ArrayList<Point> pointIndex;
	private Counter counter1, counter2;
	private Player player1, player2;
	
	
	Board() {
		
		home1 = new ArrayList<Point>(QUAD_SIZE);
		outer1 = new ArrayList<Point>(QUAD_SIZE);
		home2 = new ArrayList<Point>(QUAD_SIZE);
		outer2 = new ArrayList<Point>(QUAD_SIZE);
		
		player1 = new Player(1);
		player2 = new Player(2);

		
		counter1 = new Counter(player1.getCounterType());
		counter2 = new Counter(player2.getCounterType());
		
		for (int j = 0; j < QUAD_SIZE; j++) {
			
			if (j == 0) {
				
				home1.add(new Point(2,counter2));
				home2.add(new Point(5,counter2));
				outer1.add(new Point());
				outer2.add(new Point(5,counter2));
			}
			
			else if (j == 2) {
				
				home1.add(new Point());
				home2.add(new Point());
				outer1.add(new Point(3,counter2));
				outer2.add(new Point());
				
			}
			
			else if (j == 4) {
				
				home1.add(new Point());
				home2.add(new Point());
				outer1.add(new Point());
				outer2.add(new Point(3,counter1));
				
			}
			
			else if (j == 5) {
				
				home1.add(new Point(5,counter1));
				outer1.add(new Point(5,counter1));
				home2.add(new Point(2,counter1));
				outer2.add(new Point());
				
			}
			
			else {
			
				home1.add(new Point());
				outer1.add(new Point());
				home2.add(new Point());
				outer2.add(new Point());
				
			}
			
		}
		
		pointIndex = home1;
		pointIndex.addAll(outer1);
		pointIndex.addAll(outer2);
		pointIndex.addAll(home2);
		
	}
	
	public int getPipNum(Player p, int idx, ArrayList <Point> pointIndex) {
		
		int numCounters = 0;
		int pipVal = idx + 1;
		int pipNum = 0;
		
		for (int j = 0; j < POINT_SIZE; j++) {
				
			if (pointIndex.get(idx).getCounters().size() - 1 >= j) {
								
				if (pointIndex.get(idx).getSpecCounter(j).getType() == p.getCounterType()) {
					
					numCounters++;
						
				}
			}
				
		}
			
		pipNum += numCounters * pipVal;
		
		return pipNum;
		
	}
	
	public int getPipCount() {
		
		return 2;
		
	}
	
	public void whosFIrst(Dice dice) {
		
		System.out.println(ConsoleColors.RESET + "\nRoll to see who's first: \n");

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
		
		int pipNum = 0;
		
		System.out.println(ConsoleColors.RED_BACKGROUND_FULL + "-------------------------------------------------------------------------------------------------" + ConsoleColors.RESET);
		
		for (int i = 12; i < 18; i++) {
			
			pipNum = getPipNum(player1, i, pointIndex);
			
			System.out.print(ConsoleColors.WHITE_BOLD + pipNum + "\t");
			
		}
		
		System.out.print(ConsoleColors.RED_BACKGROUND + "   " + ConsoleColors.RESET + " \t");			
		
		for (int i = 18; i < 24; i++) {
			
			pipNum = getPipNum(player1, i, pointIndex);
			
			System.out.print(ConsoleColors.WHITE_BOLD + pipNum + "\t");
			
		}
		
		System.out.print("\n");
		
		for (int y = 2*QUAD_SIZE + 1; y <= NUM_POINTS - QUAD_SIZE; y++) {
			
			System.out.print(y + "\t");
			
		}
		
		System.out.print(ConsoleColors.RED_BACKGROUND + "   " + ConsoleColors.RESET + " \t");		
		for (int y = (NUM_POINTS - QUAD_SIZE) + 1; y <= NUM_POINTS; y++) {
			
			System.out.print(y + "\t");
			
		}
		
		System.out.print(ConsoleColors.RED_BACKGROUND_FULL + "\n-------------------------------------------------------------------------------------------------\n" + ConsoleColors.RESET);
		
		for (int j = 0; j < POINT_SIZE; j++) {
			
			//PLAYER 2 OUTER PRINT

			for (int n = 0; n < QUAD_SIZE; n++) {
				
				if ((outer2.get(n).getCounters().size()-1) < j) {
					
					System.out.print(ConsoleColors.GREEN + "|" + "\t");
					
				}
				
				else {
				
					System.out.print(outer2.get(n).getCounters().get(j) + "\t");
				
				}
				
			}

			System.out.print(ConsoleColors.RED_BACKGROUND + "   " + ConsoleColors.RESET + " \t");			
			//PLAYER 2 OUTER PRINT
			
			for (int p = 0; p < QUAD_SIZE; p++) {
				
				if ((home2.get(p).getCounters().size()-1) < j) {
					
					System.out.print(ConsoleColors.GREEN + "|" + "\t");
					
				}
				
				else {
				
					System.out.print(home2.get(p).getCounters().get(j) + "\t");
				
				}
				
			}
			
			System.out.print("\n");
						
		}
		
		System.out.print(ConsoleColors.BLACK_BACKGROUND_FULL + "-------------------------------------------------------------------------------------------------\n" + ConsoleColors.RESET);

		for (int k = 4; k >= 0; k--) {
			
			//PLAYER 1 OUTER PRINT
			
			for (int n = QUAD_SIZE - 1;n >= 0; n--) {
				
				if ((outer1.get(n).getCounters().size()-1) < k) {
					
					System.out.print(ConsoleColors.GREEN + "|" + "\t");
					
				}
				
				else {
				
					System.out.print(outer1.get(n).getCounters().get(k) + "\t");
				
				}
							
			}
			
			System.out.print(ConsoleColors.WHITE_BACKGROUND + "   " + ConsoleColors.RESET + " \t");
			
			//PLAYER 2 HOME PRINT
			
			for (int p = QUAD_SIZE - 1;p >= 0; p--) {
				
				if ((home1.get(p).getCounters().size()-1) < k) {
					
					System.out.print(ConsoleColors.GREEN + "|" + "\t");
					
				}
				
				else {
				
					System.out.print(home1.get(p).getCounters().get(k) + "\t");
				
				}
								
			}
			
			System.out.print("\n");
						
		}
		
		System.out.println(ConsoleColors.WHITE_BACKGROUND_FULL + "-------------------------------------------------------------------------------------------------" + ConsoleColors.RESET);

		for (int y = 12; y > 6; y--) {
			
			System.out.print(y + "\t");
			
		}
		
		System.out.print(ConsoleColors.WHITE_BACKGROUND + "   " + ConsoleColors.RESET + " \t");
		
		for (int y = 6; y > 0; y--) {
			
			System.out.print(y + "\t");
			
		}
		
		System.out.print("\n");
		
		for (int i = 11; i > 5; i--) {
			
			pipNum = getPipNum(player1, i, pointIndex);
			
			System.out.print(ConsoleColors.WHITE_BOLD + pipNum + "\t");
			
		}
		
		System.out.print(ConsoleColors.WHITE_BACKGROUND + "   " + ConsoleColors.RESET + " \t");
		
		
		for (int i = 5; i >= 0; i--) {
			
			pipNum = getPipNum(player1, i, pointIndex);
			
			System.out.print(ConsoleColors.WHITE_BOLD + pipNum + "\t");
			
		}
		
		System.out.print(ConsoleColors.WHITE_BACKGROUND_FULL + "\n-------------------------------------------------------------------------------------------------\n" + ConsoleColors.RESET);
		
		
	}
	
	public static void main(String...args) {
		
		Board b = new Board();
		
		b.printBoard();
		
		
	}
	
	
}
