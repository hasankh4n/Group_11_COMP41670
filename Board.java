package BackgammonTest;
import java.util.ArrayList;

public class Board {

	public final static int QUAD_SIZE = 6;
	public final static int POINT_SIZE = 5;
	public final static int NUM_POINTS = 24;
	public final static String LONG_BORDER = "                                                                                                    ";

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
				outer2.add(new Point(5,counter1));
			}
			
			else if (j == 1) {
				
				home1.add(new Point());
				home2.add(new Point());
				outer1.add(new Point(3,counter1));
				outer2.add(new Point());
				
			}
			
			else if (j == 4) {
				
				home1.add(new Point());
				home2.add(new Point());
				outer1.add(new Point());
				outer2.add(new Point(3,counter2));
				
			}
			
			else if (j == 5) {
				
				home1.add(new Point(5,counter1));
				outer1.add(new Point(5,counter2));
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
		
		//Put all points in single ArrayList
		
		pointIndex = home1;
		pointIndex.addAll(outer1);
		pointIndex.addAll(outer2);
		pointIndex.addAll(home2);
		
	}
	
	public int getPipNum(Player p, int idx, ArrayList <Point> pointIndex) {
		
		int pipNum = 0;
		int numCounters = 0;
		int pipVal = 0;
		
		if (p == player1) {
		
			pipVal = idx + 1;
			
		}
		
		else if (p == player2) {
			
			pipVal = NUM_POINTS - idx;
			
		}
				
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
	
	public int getPipCount(Player p) {
		
		int pipCount = 0;
		
		for (int i = 0; i < NUM_POINTS; i++) {
			
			pipCount += getPipNum(p, i, pointIndex);
			
		}
		
		return pipCount;
		
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
	
	public void printTopNums(Player p) {
		
		int pipNum = 0;
		
		if (p == player1) { //PRINT PLAYER2 HOME AND OUTER HEADER
			
			System.out.println(ConsoleColors.RED_BACKGROUND + LONG_BORDER + ConsoleColors.RESET);
			
			//PRINT PIP VALUES

			System.out.print(ConsoleColors.RED_BACKGROUND + "  " + ConsoleColors.RESET);
						
			for (int i = NUM_POINTS/2; i < NUM_POINTS; i++) {
				
				pipNum = getPipNum(p, i, pointIndex);
				
				if (i < NUM_POINTS - 1) {
					
					System.out.print(ConsoleColors.WHITE_BOLD + pipNum + "\t");
					
				}
					
				else {
						
					System.out.print(ConsoleColors.WHITE_BOLD + pipNum);
						
				}
				
				if (i == NUM_POINTS - QUAD_SIZE - 1) {
					
					System.out.print(ConsoleColors.RED_BACKGROUND + "   " + ConsoleColors.RESET + " \t");
					
				}
				
			}
			
			System.out.print(ConsoleColors.RED_BACKGROUND + "  " + ConsoleColors.RESET);
			
			System.out.print("\n");
			
			//PRINT POINT NUMBERS
			
			System.out.print(ConsoleColors.RED_BACKGROUND + "  " + ConsoleColors.RESET);
			
			for (int y = 2*QUAD_SIZE + 1; y <= NUM_POINTS; y++) {
				
				if (y < 24) {
					
					System.out.print(y + "\t");
					
				}
					
				else {
						
					System.out.print("24");
						
				}
				
				if (y == NUM_POINTS - QUAD_SIZE) {
					
					System.out.print(ConsoleColors.RED_BACKGROUND + "   " + ConsoleColors.RESET + " \t");
					
				}
				
			}
			
			System.out.print(ConsoleColors.RED_BACKGROUND + "  " + ConsoleColors.RESET);
			
			System.out.print("\n");
			
			System.out.println(ConsoleColors.RED_BACKGROUND + LONG_BORDER + ConsoleColors.RESET);
			
			
		}
		
		else if (p == player2) { //PRINT PLAYER1 HOME AND OUTER HEADER
			
			System.out.println(ConsoleColors.WHITE_BACKGROUND + LONG_BORDER + ConsoleColors.RESET);
			
			//PRINT PIP NUMBERS
			
			System.out.print(ConsoleColors.WHITE_BACKGROUND + "  " + ConsoleColors.RESET);
			
			for (int i = 2*QUAD_SIZE - 1; i >= 0; i--) {
				
				pipNum = getPipNum(p, i, pointIndex);
				
				if (i > 0) {
				
					System.out.print(ConsoleColors.WHITE_BOLD + pipNum + "\t");
				
				}
				
				else {
					
					System.out.print(ConsoleColors.WHITE_BOLD + pipNum + " ");
					
				}
				
				if (i == QUAD_SIZE) {
					
					System.out.print(ConsoleColors.WHITE_BACKGROUND + "   " + ConsoleColors.RESET + " \t");
					
				}
				
			}
			
			System.out.print(ConsoleColors.WHITE_BACKGROUND + "  " + ConsoleColors.RESET);
			
			System.out.print("\n");
			
			//PRINT POINT NUMBERS
			
			System.out.print(ConsoleColors.WHITE_BACKGROUND + "  " + ConsoleColors.RESET);
			
			for (int y = 2*QUAD_SIZE; y > 0; y--) {
				
				if (y > 1) {
				
					System.out.print(y + "\t");
				
				}
				
				else {
					
					System.out.print("1 ");
					
				}
				
				if (y == QUAD_SIZE + 1) {
					
					System.out.print(ConsoleColors.WHITE_BACKGROUND + "   " + ConsoleColors.RESET + " \t");
					
				}
				
			}
			
			System.out.print(ConsoleColors.WHITE_BACKGROUND + "  " + ConsoleColors.RESET);
				
			System.out.print("\n");
			
			System.out.println(ConsoleColors.WHITE_BACKGROUND + LONG_BORDER + ConsoleColors.RESET);
		}
		
	}
	
	public void printBottomNums(Player p) { 
		
		int pipNum = 0;
		
		if (p == player1) { //PRINT PLAYER1 HOME AND OUTER HEADER
		
			System.out.println(p.getColour() + LONG_BORDER + ConsoleColors.RESET);
			
			//PRINT POINT NUMBERS
			
			System.out.print(p.getColour() + "  " + ConsoleColors.RESET);
			
			for (int y = 2*QUAD_SIZE; y > 0; y--) {
				
				if (y > 1) {
				
					System.out.print(y + "\t");
				
				}
				
				else {
					
					System.out.print("1 ");
					
				}
				
				if (y == QUAD_SIZE + 1) {
					
					System.out.print(p.getColour() + "   " + ConsoleColors.RESET + " \t");
					
				}
				
			}
			
			System.out.print(p.getColour() + "  " + ConsoleColors.RESET);
			
			System.out.print("\n");
			
			//PRINT PIP NUMBERS
			
			System.out.print(p.getColour() + "  " + ConsoleColors.RESET);
			
			for (int i = 2*QUAD_SIZE - 1; i >= 0; i--) {
				
				pipNum = getPipNum(p, i, pointIndex);
				
				if (i > 0) {
				
					System.out.print(ConsoleColors.WHITE_BOLD + pipNum + "\t");
				
				}
				
				else {
					
					System.out.print(ConsoleColors.WHITE_BOLD + pipNum + " ");
					
				}
				
				if (i == QUAD_SIZE) {
					
					System.out.print(p.getColour() + "   " + ConsoleColors.RESET + " \t");
					
				}
				
			}
			
			System.out.print(p.getColour() + "  " + ConsoleColors.RESET);
				
			System.out.print("\n");
			
			System.out.println(p.getColour() + LONG_BORDER + ConsoleColors.RESET);
		
		}
		
		
		else { //PRINT PLAYER2 HOME AND OUTER HEADER
			
			System.out.println(p.getColour() + LONG_BORDER + ConsoleColors.RESET);
			
			//PRINT POINT NUMBERS
			
			System.out.print(p.getColour() + "  " + ConsoleColors.RESET);
			
			for (int y = 2*QUAD_SIZE + 1; y <= NUM_POINTS; y++) {
				
				if (y < 24) {
					
					System.out.print(y + "\t");
					
				}
					
				else {
						
					System.out.print("24");
						
				}
				
				if (y == NUM_POINTS - QUAD_SIZE) {
					
					System.out.print(p.getColour() + "   " + ConsoleColors.RESET + " \t");
					
				}
				
			}
			
			System.out.print(p.getColour() + "  " + ConsoleColors.RESET);
			
			System.out.print("\n");
			
			//PRINT PIP VALUES

			System.out.print(p.getColour() + "  " + ConsoleColors.RESET);
						
			for (int i = NUM_POINTS/2; i < NUM_POINTS; i++) {
				
				pipNum = getPipNum(p, i, pointIndex);
				
				if (i < NUM_POINTS - 1) {
					
					System.out.print(ConsoleColors.WHITE_BOLD + pipNum + "\t");
					
				}
					
				else {
						
					System.out.print(ConsoleColors.WHITE_BOLD + pipNum + " ");
						
				}
				
				if (i == NUM_POINTS - QUAD_SIZE - 1) {
					
					System.out.print(p.getColour() + "   " + ConsoleColors.RESET + " \t");
					
				}
				
			}
			
			System.out.print(p.getColour() + "  " + ConsoleColors.RESET);
			
			System.out.print("\n");
			
			System.out.println(p.getColour() + LONG_BORDER + ConsoleColors.RESET);
			
		}
		
	}
	
	public void printBoard() {
		
		printTopNums(player2);
				
		for (int j = 0; j < POINT_SIZE; j++) {
			
			//PLAYER 2 OUTER PRINT

			System.out.print(ConsoleColors.RED_BACKGROUND + "  " + ConsoleColors.RESET);
			
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
			
			System.out.print(ConsoleColors.RED_BACKGROUND + "  " + ConsoleColors.RESET);
			
			System.out.print("\n");
						
		}
		
		System.out.print(ConsoleColors.BLACK_BACKGROUND_FULL + "                                                                                                    \n" + ConsoleColors.RESET);

		for (int k = 4; k >= 0; k--) {
			
			//PLAYER 1 OUTER PRINT
			
			System.out.print(ConsoleColors.WHITE_BACKGROUND + "  " + ConsoleColors.RESET);
			
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
					
					if (p > 0) {
					
						System.out.print(ConsoleColors.GREEN + "|" + "\t");
					
					}
					
					else {
						
						System.out.print(ConsoleColors.GREEN + "|" + " ");
						
					}
					
				}
				
				else {
				
					if (p > 0) {
						
						System.out.print(home1.get(p).getCounters().get(k) + "\t");
					
					}
					
					else {
						
						System.out.print(home1.get(p).getCounters().get(k) + " ");
						
					}
				
				}
								
			}
			
			System.out.print(ConsoleColors.WHITE_BACKGROUND + "  " + ConsoleColors.RESET);
			
			System.out.print("\n");
						
		}
		
		printBottomNums(player2);
		
		int pipCount = getPipCount(player2);
		
		System.out.println("\n" + pipCount);
		
	}
	
	public static void main(String...args) {
		
		Board b = new Board();
		
		b.printBoard();
		
		
	}
	
	
}
