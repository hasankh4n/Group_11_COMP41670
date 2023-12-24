package BackgammonTest;
import java.util.ArrayList;
import java.util.Stack;

public class Board {

	public final static int QUAD_SIZE = 6;
	public final static int NUM_POINTS = 24;
	public final static String LONG_BORDER = "                                                                                                    ";

	private ArrayList<Point> home1, outer1, home2, outer2;
	private Point bar1, bar2;
	public ArrayList<Point> pointIndex;
	private Counter counter1, counter2;
	private Score scoreboard;
	
	Board(Player p1, Player p2) {
		
		//Initialise Points for each of the four boards
		
		home1 = new ArrayList<Point>(QUAD_SIZE);
		outer1 = new ArrayList<Point>(QUAD_SIZE);
		home2 = new ArrayList<Point>(QUAD_SIZE);
		outer2 = new ArrayList<Point>(QUAD_SIZE);
		
		//Initialise counter variables for both types
		
		counter1 = new Counter(p1.getCounterType());
		counter2 = new Counter(p2.getCounterType());
		
		scoreboard = new Score();
		
		//Fill each quad with counters accordingly
		
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
		
		//Add "bar" points
		bar1 = new Point();
		bar2 = new Point();
		
	}
	
	public Score getScore() {
		
		return scoreboard;
		
	}
	
	public int getPipNum(Player p, int idx, ArrayList <Point> pointIndex) {
		
		int pipNum = 0;
		int numCounters = 0;
		int pipVal = 0;
		
		int pointSize = maxNumRows();
		
		if (p.getPlayerNumber() == 1) {
		
			pipVal = idx + 1;
			
		}
		
		else if (p.getPlayerNumber() == 2) {
			
			pipVal = NUM_POINTS - idx;
			
		}
				
		for (int j = 0; j < pointSize; j++) {
			
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
	
	public void showPipCounts(Player p1, Player p2) {
		
		System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT +"\nPip Counts\n" + ConsoleColors.RESET);
		
		System.out.println("Player 1: " + getPipCount(p1));
		System.out.println("Player 2: " + getPipCount(p2) + "\n");

		
	}
	
	public boolean canBearOff(Player p) {
		
		int pips = 0;
		boolean allHomeBoard = false;
		
		if (p.getPlayerNumber() == 1) {
			
			for (int i = 6; i < 24; i++) {
				
				pips += getPipNum(p, i, pointIndex);
				
			}
			
			if (pips == 0) {
				
				allHomeBoard = true;
				
			}
			
		}
		
		else {
			
			for (int i = 0; i < 18; i++) {
				
				pips += getPipNum(p, i, pointIndex);
				
			}
			
			if (pips == 0) {
				
				allHomeBoard = true;
				
			}
			
		}
		
		return allHomeBoard;
		
		
	}
	
	public int whosFIrst(Dice dice) {
		
		int first = 0; 
		
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
				
				System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "\nPlayer 1 goes first!\n" + ConsoleColors.RESET);
				
				first = 1;
				
			}
			
			else {
				
				System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "\nPlayer 2 goes first!\n" + ConsoleColors.RESET);
				
				first = 2;
				
			}
		
		} while (firstRoll[0] == firstRoll[1]);
		
		return first;
		
	}
	
	public String[] LegalMoves(Dice dice, Player p) {
				
		//Initialise array to store allowed moves
		String[] movesAllowed = new String[99];
		int nextIdx = 0; 
		
		Player currentPlayer = p;
		
		int toPlay[] = new int[2]; //Array to store dice faces
		
		int numMoves = 2;
		
		//Boolean checks for at least one possible move with either both dice or just one
		boolean firstPossible = false; 
		boolean secondPossible = false;
		
		dice.roll();
		
		toPlay = dice.getFaces();
		
		int largerVal = toPlay[0];
		int smallerVal = toPlay[1];
		
		if (p.getPlayerNumber() == 2) {
			
			largerVal = 0 - toPlay[0];
			smallerVal = 0 - toPlay[1];
			
		}
		
		System.out.println(largerVal);
		
		String firstMoveLarger = null;
		String firstMoveSmaller = null;
		String secondMove = null;
		String newMove = null;
		
		//Check to see if player 1 has had any counters hit
	
		
		if (p.getPlayerNumber() == 1 && !bar1.getCounters().isEmpty()) {
								
			int pointToIdx = largerVal + 17; //Check if counter can be entered onto the opponents homeboard using larger dice value first
				
			Point moveTo1 = pointIndex.get(pointToIdx);
				
			if (moveTo1.getCounters().isEmpty() || moveTo1.getCounters().peek().getType() == currentPlayer.getCounterType() || moveTo1.getCounters().peek().getType() != currentPlayer.getCounterType() && moveTo1.getCounters().size() == 1) {
					
					
					
			}
				
		}
		
		//Check to see if dice values are equal
		if (toPlay[0] == toPlay[1]) {
			
			int maxMoves = 0;
			
			
			for (int i = 0; i < 24; i++) {
				
				Counter topCounter = null;
				
				if (!pointIndex.get(i).getCounters().isEmpty()) {
				
					topCounter = pointIndex.get(i).getCounters().peek();
				
					int pointFromIdx = i;
					int pointToIdx = i - toPlay[0];
					
					Point moveTo = new Point();
					
					Point moveFrom = new Point();
					
					if (topCounter.getType() == p.getCounterType()) {
						
						moveFrom = pointIndex.get(pointFromIdx);
						
						if (canBearOff(p) && pointToIdx < 0) {
							
							Counter replaceCounter = new Counter(p.getCounterType());
							
							firstPossible = true;
							
							moveFrom.getCounters().pop();
							
							firstMoveLarger = pointFromIdx + 1 + "-OFF";
							
							moveFrom.getCounters().push(replaceCounter);
							
						}
						
						else {
							
							moveTo = pointIndex.get(pointToIdx);
								
							if (moveTo.getCounters().isEmpty() || moveTo.getCounters().peek().getType() == currentPlayer.getCounterType() || moveTo.getCounters().peek().getType() != currentPlayer.getCounterType() && moveTo.getCounters().size() == 1) {
													
								firstPossible = true;
								
								moveTo.getCounters().push(moveFrom.getCounters().pop());
															
								firstMoveLarger = pointFromIdx + 1 + "-" + (pointToIdx + 1);
															
								moveFrom.getCounters().push(pointIndex.get(pointToIdx).getCounters().pop());
								
							}
							
						}
						
					}
				
				}
				
			}
			
		}
		
		//Check which dice value is larger
		else if (toPlay[1] > toPlay[0]) {
			
			largerVal = toPlay[1];
			smallerVal = toPlay[0];
		}
				
		//First check for larger value moved first
		
		for (int i = 0; i < 24; i++) {
						
			Counter topCounter = null;
			
			if (!pointIndex.get(i).getCounters().isEmpty()) {
			
				topCounter = pointIndex.get(i).getCounters().peek();
			
				int pointFromIdx = i;
				int pointToIdx = i - largerVal;
				
				Point moveTo = new Point();
				
				Point moveFrom = new Point();
				
				if (topCounter.getType() == p.getCounterType()) {
					
					moveFrom = pointIndex.get(pointFromIdx);
					
					if (canBearOff(p) && (pointToIdx < 0 || pointToIdx > NUM_POINTS - 1)) {
						
						Counter replaceCounter = new Counter(p.getCounterType());
						
						firstPossible = true;
						
						moveFrom.getCounters().pop();
						
						firstMoveLarger = pointFromIdx + 1 + "-OFF";
								
						//Check possible moves with second dice value after first has been moved
						for (int j = 0; j < 24; j++) {
							
							if (!pointIndex.get(j).getCounters().isEmpty()) {
								
								topCounter = pointIndex.get(j).getCounters().peek();
							
								pointFromIdx = j;
								pointToIdx = j - smallerVal;
														
								if (topCounter.getType() == p.getCounterType()) {
									
									if (canBearOff(p) && (pointToIdx < 0 || pointToIdx > NUM_POINTS - 1)) {
										
										secondPossible = true;
																				
										secondMove = pointFromIdx + 1 + "-OFF";
										
										newMove = firstMoveLarger + " " + secondMove;
										
										movesAllowed[nextIdx] = newMove;
										
										nextIdx++;
																		
									}
									
									else if (!(pointToIdx < 0) && !(pointToIdx > NUM_POINTS - 1)){
										
										moveTo = pointIndex.get(pointToIdx);
										
										if (moveTo.getCounters().isEmpty() || moveTo.getCounters().peek().getType() == currentPlayer.getCounterType() || moveTo.getCounters().peek().getType() != currentPlayer.getCounterType() && moveTo.getCounters().size() == 1) {
										
											secondPossible = true;
											
											secondMove = pointFromIdx + 1 + "-" + (pointToIdx + 1);
											
											newMove = firstMoveLarger + " " + secondMove;
											
											movesAllowed[nextIdx] = newMove;
											
											nextIdx++;
																		
										}
										
									}
									
								}
							
							}
							
						}
						
						moveFrom.getCounters().push(replaceCounter);
						
					}
					
					else if (!(pointToIdx < 0) && !(pointToIdx > NUM_POINTS - 1)){
						
						moveTo = pointIndex.get(pointToIdx);
							
						if (moveTo.getCounters().isEmpty() || moveTo.getCounters().peek().getType() == currentPlayer.getCounterType() || moveTo.getCounters().peek().getType() != currentPlayer.getCounterType() && moveTo.getCounters().size() == 1) {
												
							firstPossible = true;
							
							moveTo.getCounters().push(moveFrom.getCounters().pop());
														
							firstMoveLarger = pointFromIdx + 1 + "-" + (pointToIdx + 1);
								
							//Check possible moves with second dice value after first has been moved
							for (int j = 0; j < 24; j++) {
								
								if (!pointIndex.get(j).getCounters().isEmpty()) {
									
									topCounter = pointIndex.get(j).getCounters().peek();
								
									pointFromIdx = j;
									pointToIdx = j - smallerVal;
															
									if (topCounter.getType() == p.getCounterType()) {
										
										if (canBearOff(p) && (pointToIdx < 0 || pointToIdx > NUM_POINTS - 1)) {
											
											secondPossible = true;
																						
											secondMove = pointFromIdx + 1 + "-OFF";
											
											newMove = firstMoveLarger + " " + secondMove;
											
											movesAllowed[nextIdx] = newMove;
											
											nextIdx++;
																				
										}
										
										else if (!(pointToIdx < 0) && !(pointToIdx > NUM_POINTS - 1)){
											
											moveTo = pointIndex.get(pointToIdx);
			
											if (moveTo.getCounters().isEmpty() || moveTo.getCounters().peek().getType() == currentPlayer.getCounterType() || moveTo.getCounters().peek().getType() != currentPlayer.getCounterType() && moveTo.getCounters().size() == 1) {
																			
											secondPossible = true;
											
											secondMove = pointFromIdx + 1 + "-" + (pointToIdx + 1);
											
											newMove = firstMoveLarger + " " + secondMove;
											
											movesAllowed[nextIdx] = newMove;
											
											nextIdx++;
																				
											}
											
										}
										
									}
								
								}
								
							}
														
							moveFrom.getCounters().push(pointIndex.get(i - largerVal).getCounters().pop());
							
						}
						
					}
					
				}
			
			}
			
		}
		
		//Check for smaller value moved first
		
		for (int i = 0; i < 24; i++) {
						
			Counter topCounter = null;
			
			if (!pointIndex.get(i).getCounters().isEmpty()) {
			
				topCounter = pointIndex.get(i).getCounters().peek();
			
				int pointFromIdx = i;
				int pointToIdx = i - smallerVal;
				
				Point moveTo = new Point();
				
				Point moveFrom = new Point();
				
				if (topCounter.getType() == p.getCounterType()) {
					
					moveFrom = pointIndex.get(pointFromIdx);
					
					if (canBearOff(p) && (pointToIdx < 0 || pointToIdx > NUM_POINTS - 1)) {
						
						Counter replaceCounter = new Counter(p.getCounterType());
						
						firstPossible = true;
						
						moveFrom.getCounters().pop();
						
						firstMoveSmaller = pointFromIdx + 1 + "-OFF";
						
						
						//Check possible moves with second dice value after first has been moved
						
						for (int j = 0; j < 24; j++) {
							
							if (!pointIndex.get(j).getCounters().isEmpty()) {
								
								topCounter = pointIndex.get(j).getCounters().peek();
							
								pointFromIdx = j;
								pointToIdx = j - largerVal;
														
								if (topCounter.getType() == p.getCounterType()) {
																		
									if (canBearOff(p) && (pointToIdx < 0 || pointToIdx > NUM_POINTS - 1)) {
										
										secondPossible = true;
																		
										secondMove = pointFromIdx + 1 + "-OFF";
										
										newMove = firstMoveSmaller + " " + secondMove;
										
										movesAllowed[nextIdx] = newMove;
										
										nextIdx++;
										
									}
									
									else if (!(pointToIdx < 0) && !(pointToIdx > NUM_POINTS - 1)){
										
										moveTo = pointIndex.get(pointToIdx);
										
										if (moveTo.getCounters().isEmpty() || moveTo.getCounters().peek().getType() == currentPlayer.getCounterType() || moveTo.getCounters().peek().getType() != currentPlayer.getCounterType() && moveTo.getCounters().size() == 1) {
										
											secondPossible = true;
											
											secondMove = pointFromIdx + 1 + "-" + (pointToIdx + 1);
											
											newMove = firstMoveSmaller + " " + secondMove;
											
											movesAllowed[nextIdx] = newMove;
											
											nextIdx++;
										
										}
										
									}
									
								}
							
							}
							
						}
						
						moveFrom.getCounters().push(replaceCounter);
						
					}
					
					else if (!(pointToIdx < 0) && !(pointToIdx > NUM_POINTS - 1)){
						
						moveTo = pointIndex.get(pointToIdx);
						
						if (moveTo.getCounters().isEmpty() || moveTo.getCounters().peek().getType() == currentPlayer.getCounterType() || moveTo.getCounters().peek().getType() != currentPlayer.getCounterType() && moveTo.getCounters().size() == 1) {
						
							firstPossible = true;
							
							moveTo.getCounters().push(moveFrom.getCounters().pop());
							
							firstMoveSmaller = pointFromIdx + 1 + "-" + (pointToIdx + 1);
						
							//Check possible moves with second dice value after first has been moved
							
							for (int j = 0; j < 24; j++) {
								
								if (!pointIndex.get(j).getCounters().isEmpty()) {
									
									topCounter = pointIndex.get(j).getCounters().peek();
								
									pointFromIdx = j;
									pointToIdx = j - largerVal;
																
									if (topCounter.getType() == p.getCounterType()) {
										
										if (canBearOff(p) && (pointToIdx < 0 || pointToIdx > NUM_POINTS - 1)) {
											
											secondPossible = true;
																				
											secondMove = pointFromIdx + 1 + "-OFF";
											
											newMove = firstMoveSmaller + " " + secondMove;
											
											movesAllowed[nextIdx] = newMove;
											
											nextIdx++;
											
										}
										
										else if (!(pointToIdx < 0) && !(pointToIdx > NUM_POINTS - 1)){
											
											moveTo = pointIndex.get(pointToIdx);
											
											if (moveTo.getCounters().isEmpty() || moveTo.getCounters().peek().getType() == currentPlayer.getCounterType() || moveTo.getCounters().peek().getType() != currentPlayer.getCounterType() && moveTo.getCounters().size() == 1) {
																				
												secondPossible = true;
												
												secondMove = pointFromIdx + 1 + "-" + (pointToIdx + 1);
												
												newMove = firstMoveSmaller + " " + secondMove;
												
												movesAllowed[nextIdx] = newMove;
												
												nextIdx++;
											
											}
											
										}
										
									}
								
								}
								
							}
							
							moveFrom.getCounters().push(pointIndex.get(i - smallerVal).getCounters().pop());	
							
						}	
						
					}
					
				}
			
			}
			
		}
		
		if (secondPossible == false) {
			
			newMove = firstMoveLarger;
			
			movesAllowed[nextIdx] = newMove;
			
		}
		
		else if(firstPossible == false) {
			
			movesAllowed[nextIdx] = "No possible moves.";
						
		}
		
		return movesAllowed;
		
	}
	
	public void printHeader(Player p) {
		
		int pipNum = 0;
		
		int scoreP1 = scoreboard.getScores()[0];
		int scoreP2 = scoreboard.getScores()[1];
		
		if (p.getPlayerNumber() == 1) { //PRINT PLAYER2 HOME AND OUTER HEADER
						
			System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "My Score: " + scoreP1 + ConsoleColors.RED_BOLD_BRIGHT + "   Opponent Score: " + scoreP2 + ConsoleColors.YELLOW_BOLD_BRIGHT + "                                                      " + "Match Length: " + scoreboard.getMatchLength());
			
			System.out.println(ConsoleColors.RED_BACKGROUND + LONG_BORDER + ConsoleColors.RESET);
			
			//PRINT PIP VALUES

			System.out.print(ConsoleColors.RED_BACKGROUND + "  " + ConsoleColors.RESET);
						
			for (int i = NUM_POINTS/2; i < NUM_POINTS; i++) {
				
				pipNum = getPipNum(p, i, pointIndex);
				
				if (i < NUM_POINTS - 1) {
					
					System.out.print(ConsoleColors.WHITE_BOLD + pipNum + "\t");
					
				}
					
				else {
						
					if (pipNum < 10) {
						
						System.out.print(ConsoleColors.WHITE_BOLD + pipNum + " ");
						
					}
					
					else {
						
						System.out.print(ConsoleColors.WHITE_BOLD + pipNum);
						
					}
						
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
		
		else if (p.getPlayerNumber() == 2) { //PRINT PLAYER1 HOME AND OUTER HEADER
			
			System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "My Score: " + scoreP1 + ConsoleColors.WHITE_BOLD_BRIGHT + "   Opponent Score: " + scoreP2 + ConsoleColors.YELLOW_BOLD_BRIGHT + "                                                      " + "Match Length: " + scoreboard.getMatchLength());
			
			System.out.println(ConsoleColors.WHITE_BACKGROUND + LONG_BORDER + ConsoleColors.RESET);
			
			//PRINT PIP NUMBERS
			
			System.out.print(ConsoleColors.WHITE_BACKGROUND + "  " + ConsoleColors.RESET);
			
			for (int i = 2*QUAD_SIZE - 1; i >= 0; i--) {
				
				pipNum = getPipNum(p, i, pointIndex);
				
				if (i > 0) {
				
					System.out.print(ConsoleColors.WHITE_BOLD + pipNum + "\t");
				
				}
				
				else {
					
					if (pipNum < 10) {
					
						System.out.print(ConsoleColors.WHITE_BOLD + pipNum + " ");
						
					}
					
					else {
						
						System.out.print(ConsoleColors.WHITE_BOLD + pipNum);
						
					}
					
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
	
	public void printQuad(ArrayList<Point> quad, int j) {
		
		String borderColour = null;
		
		if (quad == outer1 || quad == home1) {
			
			borderColour = ConsoleColors.WHITE_BACKGROUND;
			
		}
		
		else {
			
			borderColour = ConsoleColors.RED_BACKGROUND;
			
		}
		
		if (quad == outer1) {
			
			//PLAYER 2 OUTER PRINT

			System.out.print(borderColour + "  " + ConsoleColors.RESET);
			
			for (int n = QUAD_SIZE -1; n >= 0; n--) {
				
				if ((quad.get(n).getCounters().size()-1) < j) {
					
					System.out.print(ConsoleColors.GREEN + "|" + "\t");
					
				}
				
				else {
				
					System.out.print(quad.get(n).getCounters().get(j) + "\t");
				
				}
				
			}

			System.out.print(borderColour + "   " + ConsoleColors.RESET + " \t");
		
		}
		
		else if (quad == outer2) {
							
				//PLAYER 2 OUTER PRINT

				System.out.print(borderColour + "  " + ConsoleColors.RESET);
				
				for (int n = 0; n < QUAD_SIZE; n++) {
					
					if ((quad.get(n).getCounters().size()-1) < j) {
						
						System.out.print(ConsoleColors.GREEN + "|" + "\t");
						
					}
					
					else {
					
						System.out.print(quad.get(n).getCounters().get(j) + "\t");
					
					}
					
				}

				System.out.print(borderColour + "   " + ConsoleColors.RESET + " \t");
				
		}
		
		else if (quad == home2){
						
				for (int k = 0; k < QUAD_SIZE; k++) {
					
					if ((quad.get(k).getCounters().size()-1) < j) {
						
						if (k < QUAD_SIZE - 1) {
						
							System.out.print(ConsoleColors.GREEN + "|" + "\t");
						
						}
						
						else {
							
							System.out.print(ConsoleColors.GREEN + "|" + " ");
							
						}
					}
					
					else {
					
						if (k < QUAD_SIZE - 1) {
							
							System.out.print(quad.get(k).getCounters().get(j) + "\t");
						
						}
						
						else {
							
							System.out.print(quad.get(k).getCounters().get(j) + " ");
							
						}
						
					
					}
					
				}
				
				System.out.print(borderColour + "  " + ConsoleColors.RESET);
				
				System.out.print("\n");
				
		}
		
		else {
			
			for (int k = QUAD_SIZE -1; k >= 0; k--) {
				
				if ((quad.get(k).getCounters().size()-1) < j) {
					
					if (k > 0) {
					
						System.out.print(ConsoleColors.GREEN + "|" + "\t");
					
					}
					
					else {
						
						System.out.print(ConsoleColors.GREEN + "|" + " ");
						
					}
				}
				
				else {
				
					if (k > 0) {
						
						System.out.print(quad.get(k).getCounters().get(j) + "\t");
					
					}
					
					else {
						
						System.out.print(quad.get(k).getCounters().get(j) + " ");
						
					}
					
				
				}
				
			}
			
			System.out.print(borderColour + "  " + ConsoleColors.RESET);
			
			System.out.print("\n");
		
		}
		
	}
	
	public int maxNumRows() {
		
		int max = 0;
		
		for (Point p : pointIndex) {
			
			if (p.getCounters().size() > max) {
				
				max = p.getCounters().size();
				
			}
			
		}
		
		return max;
		
	}
	
	public void printQuads(Player p) {
		
		int pointSize = maxNumRows();
		
		if (p.getPlayerNumber() == 1) {
		
			for (int j = 0; j < pointSize; j++) {
				
				//PLAYER 2 OUTER PRINT
				
				printQuad(outer2, j);
				
				//PLAYER 2 HOME PRINT
				
				printQuad(home2, j);
				
			}
		
			System.out.print(ConsoleColors.BLACK_BRIGHT_BACKGROUND + LONG_BORDER + "\n" + ConsoleColors.RESET);
			
			for (int k = pointSize - 1; k >= 0; k--) {
				
				//PLAYER 1 OUTER PRINT
				
				printQuad(outer1, k);
				
				//PLAYER 1 HOME PRINT
				
				printQuad(home1,k);
						
			}
			
		}
		
		else {
			
			for (int k = 0; k < pointSize; k++) {
				
				//PLAYER 1 OUTER PRINT
				
				printQuad(outer1, k);
				
				//PLAYER 1 HOME PRINT
				
				printQuad(home1,k);
						
			}
			
			System.out.print(ConsoleColors.BLACK_BRIGHT_BACKGROUND + LONG_BORDER + "\n" + ConsoleColors.RESET);

			for (int j = 4; j >= 0; j--) {
				
				//PLAYER 2 OUTER PRINT
				
				printQuad(outer2, j);
				
				//PLAYER 2 HOME PRINT
				
				printQuad(home2, j);
				
			}
			
		}
		
	}
	
	public void printFooter(Player p) { 
		
		int pipNum = 0;
		
		if (p.getPlayerNumber() == 1) { //PRINT PLAYER1 HOME AND OUTER HEADER
		
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
					
					if (pipNum < 10) {
						
						System.out.print(ConsoleColors.WHITE_BOLD + pipNum + " ");
						
					}
					
					else {
						
						System.out.print(ConsoleColors.WHITE_BOLD + pipNum);
						
					}
					
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
						
					if (pipNum < 10) {
						
						System.out.print(ConsoleColors.WHITE_BOLD + pipNum + " ");
						
					}
					
					else {
						
						System.out.print(ConsoleColors.WHITE_BOLD + pipNum);
						
					}
						
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
	
	public void printBoard(Player currentPlayer) {
				
		printHeader(currentPlayer);
		
		printQuads(currentPlayer);
		
		printFooter(currentPlayer);
		
		if (currentPlayer.getPlayerNumber() == 1) {
			
			System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + "Current Player: " + ConsoleColors.WHITE_BOLD_BRIGHT + currentPlayer.getPlayerName() + "\n" + ConsoleColors.RESET);
			
		}
		
		else {
			
			System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + "Current Player: " + ConsoleColors.RED_BOLD_BRIGHT + currentPlayer.getPlayerName() + "\n" + ConsoleColors.RESET);

			
		}
		
		
	}
	
	public void showLegalMoves(Dice dice, Player p) {
						
		String[] legal = LegalMoves(dice, p);
		
		for (int i = 0; i < legal.length; i++) {
			
			if (legal[i] == null) {
				
				break;
				
			}
			
			else {
				
				System.out.println(i + 1 + ") " + legal[i]);
				
			}
			
			
			
		}
		
		System.out.print("\n");
		
	}
	
	public void Move(Command c) {
		
		
		
		
	}
	
	public boolean isGammon(Player winner, Player loser) {
		
		int numCounters = 0;
		
		int pointSize = maxNumRows();
		
		for (int i = 0; i < NUM_POINTS; i++) {
			
			Stack<Counter> point = pointIndex.get(i).getCounters();
			
			for (int j = 0; j < pointSize; j++) {
				
				if (point.size() - 1 >= j) {
					
					if (pointIndex.get(i).getSpecCounter(j).getType() == loser.getCounterType()) {
								
						numCounters++;
									
					}
				}
				
			}
			
		}
		
		if (numCounters < 15) {
			
			return false;
			
		}
		
		else {
			
			return true;
			
		}
		
	}
	
	public boolean isBackgammon(Player winner, Player loser) {
				
		Point bar = bar1;
		
		int numCounters = 0;
		
		int pointSize = maxNumRows();
		
		if (loser.getPlayerNumber() == 2) {
			
			for (int i = 0; i < QUAD_SIZE; i++) {
				
				Stack<Counter> point = pointIndex.get(i).getCounters();
				
				for (int j = 0; j < pointSize; j++) {
					
					if (point.size() - 1 >= j) {
						
						if (pointIndex.get(i).getSpecCounter(j).getType() == loser.getCounterType()) {
									
							numCounters++;
										
						}
					}
					
				}
				
			}
			
		}
		
		else {
			
			for (int i = 18; i < NUM_POINTS; i++) {
				
				Stack<Counter> point = pointIndex.get(i).getCounters();
				
				for (int j = 0; j < pointSize; j++) {
					
					if (point.size() - 1 >= j) {
						
						if (pointIndex.get(i).getSpecCounter(j).getType() == loser.getCounterType()) {
									
							numCounters++;
										
						}
					}
					
				}
				
			}
			
		}
		
		if ((numCounters > 0 || !bar.getCounters().isEmpty()) && isGammon(winner, loser)) {
			
			return true;
			
		}
		
		else {
			
			return false;
			
		}
		
	}
	
	public boolean isGameOver(Player p1, Player p2) {
		
		boolean isOver = false;
				
		if (getPipCount(p1) == 0 || getPipCount(p2) == 0) {
			
			isOver = true;
			
		}
		
		return isOver;
		
	}
	
	public static void main(String[] args) {
		
		Player p = new Player(1);
		Player p2 = new Player(2);
		
		Board b = new Board(p, p2);
		
		b.printBoard(p2);		
		
	}
	
	
}
