package backgammonGroup11;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Group Number: 11
 * @author James Duke, Hasan Khan
 * GitHub IDs: jamesduke488, hasankh4n
 * @version 1.1
 */

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
				
		//Initialise string arrays to store allowed moves for player is able to move 1-4 times, used for printing
		String[] movesAllowed = new String[99];
		movesAllowed = null;
		String[] oneMoveAllowed = new String[99];
		String[] twoMovesAllowed = new String[99];
		String[] threeMovesAllowed = new String[99];
		String[] fourMovesAllowed = new String[99];

		//Initialise int arrays to store same moves in integer format so player can make move
		/*int[] oneMoveAllowedInt = new int[99];
		int[] twoMovesAllowedInt = new int[99];
		int[] threeMovesAllowedInt = new int[99];
		int[] fourMovesAllowedInt = new int[99];*/
		
		//Initialise indexes to store moves
		int firstIdx = 0;
		int secondIdx = 0;
		int thirdIdx = 0;
		int fourthIdx = 0;
		
		Player currentPlayer = p;
		
		int toPlay[] = new int[2]; //Array to store dice faces
				
		//Boolean checks for at least one possible move with either both dice or just one
		boolean firstPossible = false; 
		boolean secondPossible = false;
		boolean thirdPossible = false;
		boolean fourthPossible = false;
		
		dice.roll();
		
		toPlay = dice.getFaces();
		
		int largerVal = toPlay[0];
		int smallerVal = toPlay[1];
				
		String firstMoveLarger = null;
		String firstMoveSmaller = null;
		String secondMove = null;
		String thirdMove = null;
		String fourthMove = null;
		String newMove = null;
		String newTwoMove = null;
		String newThreeMove = null;
		
		//Check to see if player 1 has had any counters hit (Not finished)
	
		
		if (p.getPlayerNumber() == 1 && !bar1.getCounters().isEmpty()) {
								
			int pointToIdx = largerVal + 17; //Check if counter can be entered onto the opponents homeboard using larger dice value first
				
			Point moveTo1 = pointIndex.get(pointToIdx);
				
			if (moveTo1.getCounters().isEmpty() || moveTo1.getCounters().peek().getType() == currentPlayer.getCounterType() || moveTo1.getCounters().peek().getType() != currentPlayer.getCounterType() && moveTo1.getCounters().size() == 1) {
					
					
					
			}
				
		}
		
		//Check to see if dice values are equal
		if (toPlay[0] == toPlay[1]) {
			
			ArrayList<Point> ogPointIndex = (ArrayList<Point>) pointIndex.clone();
			
			int moveVal = toPlay[0];
			
			if (p.getPlayerNumber() == 2) {
				
				moveVal = 0 - moveVal;
				
			}

			/*1**************************************************************************/
			
			//Check possible moves with first dice
			for (int i = 0; i < 24; i++) {
				
				Counter topCounter = null;
				
				if (!pointIndex.get(i).getCounters().isEmpty()) {
				
					topCounter = pointIndex.get(i).getCounters().peek();
				
					int pointFromIdx = i;
					int pointToIdx = i - moveVal;
					
					Point moveTo = new Point();
					
					Point moveFrom = new Point();
					
					if (topCounter.getType() == p.getCounterType()) {
						
						moveFrom = pointIndex.get(pointFromIdx);
						
						if (canBearOff(p) && pointToIdx < 0) {
														
							firstPossible = true;
							
							moveFrom.getCounters().pop();
							
							firstMoveLarger = pointFromIdx + 1 + "-OFF";
							
							oneMoveAllowed[firstIdx] = firstMoveLarger;
							
							firstIdx++;
										
							/*2**************************************************************************/
							
							//Check possible moves with second dice
							for (int j = 0; j < 24; j++) {
								
								if (!pointIndex.get(j).getCounters().isEmpty()) {
									
									topCounter = pointIndex.get(j).getCounters().peek();
								
									pointFromIdx = j;
									pointToIdx = j - smallerVal;
															
									if (topCounter.getType() == p.getCounterType()) {
										
										moveFrom = pointIndex.get(pointFromIdx);
										
										if (canBearOff(p) && (pointToIdx < 0 || pointToIdx > NUM_POINTS - 1)) {
											
											secondPossible = true;
												
											moveFrom.getCounters().pop();
											
											secondMove = pointFromIdx + 1 + "-OFF";
											
											newTwoMove = firstMoveLarger + " " + secondMove;
											
											twoMovesAllowed[secondIdx] = newTwoMove;
											
											secondIdx++;
												
											/*3**************************************************************************/
											
											//Check possible moves with third dice
											for (int k = 0; k < 24; k++) {
												
												if (!pointIndex.get(k).getCounters().isEmpty()) {
													
													topCounter = pointIndex.get(k).getCounters().peek();
												
													pointFromIdx = k;
													pointToIdx = k - smallerVal;
																			
													if (topCounter.getType() == p.getCounterType()) {
														
														moveFrom = pointIndex.get(pointFromIdx);
														
														if (canBearOff(p) && (pointToIdx < 0 || pointToIdx > NUM_POINTS - 1)) {
															
															thirdPossible = true;
																
															moveFrom.getCounters().pop();
															
															thirdMove = pointFromIdx + 1 + "-OFF";
															
															newThreeMove = newTwoMove + " " + thirdMove;
															
															threeMovesAllowed[thirdIdx] = newThreeMove;
															
															thirdIdx++;
															
															/*4**************************************************************************/
															
															//Check possible moves with fourth dice
															for (int l = 0; l < 24; l++) {
																
																if (!pointIndex.get(l).getCounters().isEmpty()) {
																	
																	topCounter = pointIndex.get(l).getCounters().peek();
																
																	pointFromIdx = l;
																	pointToIdx = l - smallerVal;
																							
																	if (topCounter.getType() == p.getCounterType()) {
																		
																		moveFrom = pointIndex.get(pointFromIdx);
																		
																		if (canBearOff(p) && (pointToIdx < 0 || pointToIdx > NUM_POINTS - 1)) {
																			
																			fourthPossible = true;
																																										
																			fourthMove = pointFromIdx + 1 + "-OFF";
																			
																			newMove = newThreeMove + " " + fourthMove;
																			
																			fourMovesAllowed[fourthIdx] = newMove;
																			
																			fourthIdx++;
																											
																		}
																		
																		else if (!(pointToIdx < 0) && !(pointToIdx > NUM_POINTS - 1)){
																			
																			moveTo = pointIndex.get(pointToIdx);
																			
																			if (moveTo.getCounters().isEmpty() || moveTo.getCounters().peek().getType() == currentPlayer.getCounterType() || moveTo.getCounters().peek().getType() != currentPlayer.getCounterType() && moveTo.getCounters().size() == 1) {
																			
																				fourthPossible = true;
																																											
																				fourthMove = pointFromIdx + 1 + "-" + (pointToIdx + 1);
																				
																				newMove = newThreeMove + " " + fourthMove;
																				
																				fourMovesAllowed[thirdIdx] = newMove;
																				
																				fourthIdx++;
																											
																			}
																			
																		}
																		
																	}
																
																}
																
															}
															
															/*4**************************************************************************/
																							
														}
														
														else if (!(pointToIdx < 0) && !(pointToIdx > NUM_POINTS - 1)){
															
															moveTo = pointIndex.get(pointToIdx);
															
															if (moveTo.getCounters().isEmpty() || moveTo.getCounters().peek().getType() == currentPlayer.getCounterType() || moveTo.getCounters().peek().getType() != currentPlayer.getCounterType() && moveTo.getCounters().size() == 1) {
															
																thirdPossible = true;
																
																moveTo.getCounters().push(moveFrom.getCounters().pop());
																
																thirdMove = pointFromIdx + 1 + "-" + (pointToIdx + 1);
																
																newThreeMove = newTwoMove + " " + thirdMove;
																
																threeMovesAllowed[thirdIdx] = newThreeMove;
																
																thirdIdx++;
																
																/*4**************************************************************************/
																
																//Check possible moves with fourth dice
																for (int l = 0; l < 24; l++) {
																	
																	if (!pointIndex.get(l).getCounters().isEmpty()) {
																		
																		topCounter = pointIndex.get(l).getCounters().peek();
																	
																		pointFromIdx = l;
																		pointToIdx = l - smallerVal;
																								
																		if (topCounter.getType() == p.getCounterType()) {
																			
																			moveFrom = pointIndex.get(pointFromIdx);
																			
																			if (canBearOff(p) && (pointToIdx < 0 || pointToIdx > NUM_POINTS - 1)) {
																				
																				fourthPossible = true;
																																											
																				fourthMove = pointFromIdx + 1 + "-OFF";
																				
																				newMove = newThreeMove + " " + fourthMove;
																				
																				fourMovesAllowed[fourthIdx] = newMove;
																				
																				fourthIdx++;
																												
																			}
																			
																			else if (!(pointToIdx < 0) && !(pointToIdx > NUM_POINTS - 1)){
																				
																				moveTo = pointIndex.get(pointToIdx);
																				
																				if (moveTo.getCounters().isEmpty() || moveTo.getCounters().peek().getType() == currentPlayer.getCounterType() || moveTo.getCounters().peek().getType() != currentPlayer.getCounterType() && moveTo.getCounters().size() == 1) {
																				
																					fourthPossible = true;
																																												
																					fourthMove = pointFromIdx + 1 + "-" + (pointToIdx + 1);
																					
																					newMove = newThreeMove + " " + fourthMove;
																					
																					fourMovesAllowed[thirdIdx] = newMove;
																					
																					fourthIdx++;
																												
																				}
																				
																			}
																			
																		}
																	
																	}
																	
																}
																
																/*4**************************************************************************/
																							
															}
															
														}
														
													}
												
												}
												
											}
											
											/*3**************************************************************************/
											
										}
										
										/*2**************************************************************************/
										
										else if (!(pointToIdx < 0) && !(pointToIdx > NUM_POINTS - 1)){
											
											moveTo = pointIndex.get(pointToIdx);
											
											if (moveTo.getCounters().isEmpty() || moveTo.getCounters().peek().getType() == currentPlayer.getCounterType() || moveTo.getCounters().peek().getType() != currentPlayer.getCounterType() && moveTo.getCounters().size() == 1) {
											
												secondPossible = true;
												
												moveTo.getCounters().push(moveFrom.getCounters().pop());
												
												secondMove = pointFromIdx + 1 + "-" + (pointToIdx + 1);
												
												newTwoMove = firstMoveLarger + " " + secondMove;
												
												twoMovesAllowed[secondIdx] = newTwoMove;
												
												secondIdx++;
												
												/*3**************************************************************************/
												
												//Check possible moves with third dice
												for (int k = 0; k < 24; k++) {
													
													if (!pointIndex.get(k).getCounters().isEmpty()) {
														
														topCounter = pointIndex.get(k).getCounters().peek();
													
														pointFromIdx = k;
														pointToIdx = k - smallerVal;
																				
														if (topCounter.getType() == p.getCounterType()) {
															
															moveFrom = pointIndex.get(pointFromIdx);
															
															if (canBearOff(p) && (pointToIdx < 0 || pointToIdx > NUM_POINTS - 1)) {
																
																thirdPossible = true;
																	
																moveFrom.getCounters().pop();
																
																thirdMove = pointFromIdx + 1 + "-OFF";
																
																newThreeMove = newTwoMove + " " + thirdMove;
																
																threeMovesAllowed[thirdIdx] = newThreeMove;
																
																thirdIdx++;
																
																/*4**************************************************************************/
																
																//Check possible moves with fourth dice
																for (int l = 0; l < 24; l++) {
																	
																	if (!pointIndex.get(l).getCounters().isEmpty()) {
																		
																		topCounter = pointIndex.get(l).getCounters().peek();
																	
																		pointFromIdx = l;
																		pointToIdx = l - smallerVal;
																								
																		if (topCounter.getType() == p.getCounterType()) {
																			
																			moveFrom = pointIndex.get(pointFromIdx);
																			
																			if (canBearOff(p) && (pointToIdx < 0 || pointToIdx > NUM_POINTS - 1)) {
																				
																				fourthPossible = true;
																																											
																				fourthMove = pointFromIdx + 1 + "-OFF";
																				
																				newMove = newThreeMove + " " + fourthMove;
																				
																				fourMovesAllowed[fourthIdx] = newMove;
																				
																				fourthIdx++;
																												
																			}
																			
																			else if (!(pointToIdx < 0) && !(pointToIdx > NUM_POINTS - 1)){
																				
																				moveTo = pointIndex.get(pointToIdx);
																				
																				if (moveTo.getCounters().isEmpty() || moveTo.getCounters().peek().getType() == currentPlayer.getCounterType() || moveTo.getCounters().peek().getType() != currentPlayer.getCounterType() && moveTo.getCounters().size() == 1) {
																				
																					fourthPossible = true;
																																												
																					fourthMove = pointFromIdx + 1 + "-" + (pointToIdx + 1);
																					
																					newMove = newThreeMove + " " + fourthMove;
																					
																					fourMovesAllowed[thirdIdx] = newMove;
																					
																					fourthIdx++;
																												
																				}
																				
																			}
																			
																		}
																	
																	}
																	
																}
																
																/*4**************************************************************************/
																								
															}
															
															else if (!(pointToIdx < 0) && !(pointToIdx > NUM_POINTS - 1)){
																
																moveTo = pointIndex.get(pointToIdx);
																
																if (moveTo.getCounters().isEmpty() || moveTo.getCounters().peek().getType() == currentPlayer.getCounterType() || moveTo.getCounters().peek().getType() != currentPlayer.getCounterType() && moveTo.getCounters().size() == 1) {
																
																	thirdPossible = true;
																	
																	moveTo.getCounters().push(moveFrom.getCounters().pop());
																	
																	thirdMove = pointFromIdx + 1 + "-" + (pointToIdx + 1);
																	
																	newThreeMove = newTwoMove + " " + thirdMove;
																	
																	threeMovesAllowed[thirdIdx] = newThreeMove;
																	
																	thirdIdx++;
																	
																	/*4**************************************************************************/
																	
																	//Check possible moves with fourth dice
																	for (int l = 0; l < 24; l++) {
																		
																		if (!pointIndex.get(l).getCounters().isEmpty()) {
																			
																			topCounter = pointIndex.get(l).getCounters().peek();
																		
																			pointFromIdx = l;
																			pointToIdx = l - smallerVal;
																									
																			if (topCounter.getType() == p.getCounterType()) {
																				
																				moveFrom = pointIndex.get(pointFromIdx);
																				
																				if (canBearOff(p) && (pointToIdx < 0 || pointToIdx > NUM_POINTS - 1)) {
																					
																					fourthPossible = true;
																																												
																					fourthMove = pointFromIdx + 1 + "-OFF";
																					
																					newMove = newThreeMove + " " + fourthMove;
																					
																					fourMovesAllowed[fourthIdx] = newMove;
																					
																					fourthIdx++;
																													
																				}
																				
																				else if (!(pointToIdx < 0) && !(pointToIdx > NUM_POINTS - 1)){
																					
																					moveTo = pointIndex.get(pointToIdx);
																					
																					if (moveTo.getCounters().isEmpty() || moveTo.getCounters().peek().getType() == currentPlayer.getCounterType() || moveTo.getCounters().peek().getType() != currentPlayer.getCounterType() && moveTo.getCounters().size() == 1) {
																					
																						fourthPossible = true;
																																													
																						fourthMove = pointFromIdx + 1 + "-" + (pointToIdx + 1);
																						
																						newMove = newThreeMove + " " + fourthMove;
																						
																						fourMovesAllowed[thirdIdx] = newMove;
																						
																						fourthIdx++;
																													
																					}
																					
																				}
																				
																			}
																		
																		}
																		
																	}
																	
																	/*4**************************************************************************/
																								
																}
																
															}
															
														}
													
													}
													
												}
												
												/*3**************************************************************************/
																			
											}
											
										}
										
										/*2**************************************************************************/
										
									}
								
								}
								
							}
							
						}
						
						/*1**************************************************************************/
						
						else if (!(pointToIdx < 0) && !(pointToIdx > NUM_POINTS - 1)) {
							
							moveTo = pointIndex.get(pointToIdx);
								
							if (moveTo.getCounters().isEmpty() || moveTo.getCounters().peek().getType() == currentPlayer.getCounterType() || moveTo.getCounters().peek().getType() != currentPlayer.getCounterType() && moveTo.getCounters().size() == 1) {
													
								firstPossible = true;
								
								moveTo.getCounters().push(moveFrom.getCounters().pop());
															
								firstMoveLarger = pointFromIdx + 1 + "-" + (pointToIdx + 1);
																							
								/*2**************************************************************************/
								
								//Check possible moves with second dice
								for (int j = 0; j < 24; j++) {
									
									if (!pointIndex.get(j).getCounters().isEmpty()) {
										
										topCounter = pointIndex.get(j).getCounters().peek();
									
										pointFromIdx = j;
										pointToIdx = j - smallerVal;
																
										if (topCounter.getType() == p.getCounterType()) {
											
											moveFrom = pointIndex.get(pointFromIdx);
											
											if (canBearOff(p) && (pointToIdx < 0 || pointToIdx > NUM_POINTS - 1)) {
												
												secondPossible = true;
													
												moveFrom.getCounters().pop();
												
												secondMove = pointFromIdx + 1 + "-OFF";
												
												newMove = firstMoveLarger + " " + secondMove;
												
												twoMovesAllowed[secondIdx] = newMove;
												
												secondIdx++;
												
												/*3**************************************************************************/
												
												//Check possible moves with third dice
												for (int k = 0; k < 24; k++) {
													
													if (!pointIndex.get(k).getCounters().isEmpty()) {
														
														topCounter = pointIndex.get(k).getCounters().peek();
													
														pointFromIdx = k;
														pointToIdx = k - smallerVal;
																				
														if (topCounter.getType() == p.getCounterType()) {
															
															moveFrom = pointIndex.get(pointFromIdx);
															
															if (canBearOff(p) && (pointToIdx < 0 || pointToIdx > NUM_POINTS - 1)) {
																
																thirdPossible = true;
																	
																moveFrom.getCounters().pop();
																
																thirdMove = pointFromIdx + 1 + "-OFF";
																
																newThreeMove = newTwoMove + " " + thirdMove;
																
																threeMovesAllowed[thirdIdx] = newThreeMove;
																
																thirdIdx++;
																
																/*4**************************************************************************/
																
																//Check possible moves with fourth dice
																for (int l = 0; l < 24; l++) {
																	
																	if (!pointIndex.get(l).getCounters().isEmpty()) {
																		
																		topCounter = pointIndex.get(l).getCounters().peek();
																	
																		pointFromIdx = l;
																		pointToIdx = l - smallerVal;
																								
																		if (topCounter.getType() == p.getCounterType()) {
																			
																			moveFrom = pointIndex.get(pointFromIdx);
																			
																			if (canBearOff(p) && (pointToIdx < 0 || pointToIdx > NUM_POINTS - 1)) {
																				
																				fourthPossible = true;
																																											
																				fourthMove = pointFromIdx + 1 + "-OFF";
																				
																				newMove = newThreeMove + " " + fourthMove;
																				
																				fourMovesAllowed[fourthIdx] = newMove;
																				
																				fourthIdx++;
																												
																			}
																			
																			else if (!(pointToIdx < 0) && !(pointToIdx > NUM_POINTS - 1)){
																				
																				moveTo = pointIndex.get(pointToIdx);
																				
																				if (moveTo.getCounters().isEmpty() || moveTo.getCounters().peek().getType() == currentPlayer.getCounterType() || moveTo.getCounters().peek().getType() != currentPlayer.getCounterType() && moveTo.getCounters().size() == 1) {
																				
																					fourthPossible = true;
																																												
																					fourthMove = pointFromIdx + 1 + "-" + (pointToIdx + 1);
																					
																					newMove = newThreeMove + " " + fourthMove;
																					
																					fourMovesAllowed[thirdIdx] = newMove;
																					
																					fourthIdx++;
																												
																				}
																				
																			}
																			
																		}
																	
																	}
																	
																}
																
																/*4**************************************************************************/
																								
															}
															
															else if (!(pointToIdx < 0) && !(pointToIdx > NUM_POINTS - 1)){
																
																moveTo = pointIndex.get(pointToIdx);
																
																if (moveTo.getCounters().isEmpty() || moveTo.getCounters().peek().getType() == currentPlayer.getCounterType() || moveTo.getCounters().peek().getType() != currentPlayer.getCounterType() && moveTo.getCounters().size() == 1) {
																
																	thirdPossible = true;
																	
																	moveTo.getCounters().push(moveFrom.getCounters().pop());
																	
																	thirdMove = pointFromIdx + 1 + "-" + (pointToIdx + 1);
																	
																	newThreeMove = newTwoMove + " " + thirdMove;
																	
																	threeMovesAllowed[thirdIdx] = newThreeMove;
																	
																	thirdIdx++;
																	
																	/*4**************************************************************************/
																	
																	//Check possible moves with fourth dice
																	for (int l = 0; l < 24; l++) {
																		
																		if (!pointIndex.get(l).getCounters().isEmpty()) {
																			
																			topCounter = pointIndex.get(l).getCounters().peek();
																		
																			pointFromIdx = l;
																			pointToIdx = l - smallerVal;
																									
																			if (topCounter.getType() == p.getCounterType()) {
																				
																				moveFrom = pointIndex.get(pointFromIdx);
																				
																				if (canBearOff(p) && (pointToIdx < 0 || pointToIdx > NUM_POINTS - 1)) {
																					
																					fourthPossible = true;
																																												
																					fourthMove = pointFromIdx + 1 + "-OFF";
																					
																					newMove = newThreeMove + " " + fourthMove;
																					
																					fourMovesAllowed[fourthIdx] = newMove;
																					
																					fourthIdx++;
																													
																				}
																				
																				else if (!(pointToIdx < 0) && !(pointToIdx > NUM_POINTS - 1)){
																					
																					moveTo = pointIndex.get(pointToIdx);
																					
																					if (moveTo.getCounters().isEmpty() || moveTo.getCounters().peek().getType() == currentPlayer.getCounterType() || moveTo.getCounters().peek().getType() != currentPlayer.getCounterType() && moveTo.getCounters().size() == 1) {
																					
																						fourthPossible = true;
																																													
																						fourthMove = pointFromIdx + 1 + "-" + (pointToIdx + 1);
																						
																						newMove = newThreeMove + " " + fourthMove;
																						
																						fourMovesAllowed[thirdIdx] = newMove;
																						
																						fourthIdx++;
																													
																					}
																					
																				}
																				
																			}
																		
																		}
																		
																	}
																	
																	/*4**************************************************************************/
																								
																}
																
															}
															
														}
													
													}
													
												}
												
												/*3**************************************************************************/
																				
											}
											
											else if (!(pointToIdx < 0) && !(pointToIdx > NUM_POINTS - 1)){
												
												moveTo = pointIndex.get(pointToIdx);
												
												if (moveTo.getCounters().isEmpty() || moveTo.getCounters().peek().getType() == currentPlayer.getCounterType() || moveTo.getCounters().peek().getType() != currentPlayer.getCounterType() && moveTo.getCounters().size() == 1) {
												
													secondPossible = true;
													
													moveTo.getCounters().push(moveFrom.getCounters().pop());
													
													secondMove = pointFromIdx + 1 + "-" + (pointToIdx + 1);
													
													newMove = firstMoveLarger + " " + secondMove;
													
													twoMovesAllowed[secondIdx] = newMove;
													
													secondIdx++;
													
													/*3**************************************************************************/
													
													//Check possible moves with third dice
													for (int k = 0; k < 24; k++) {
														
														if (!pointIndex.get(k).getCounters().isEmpty()) {
															
															topCounter = pointIndex.get(k).getCounters().peek();
														
															pointFromIdx = k;
															pointToIdx = k - smallerVal;
																					
															if (topCounter.getType() == p.getCounterType()) {
																
																moveFrom = pointIndex.get(pointFromIdx);
																
																if (canBearOff(p) && (pointToIdx < 0 || pointToIdx > NUM_POINTS - 1)) {
																	
																	thirdPossible = true;
																		
																	moveFrom.getCounters().pop();
																	
																	thirdMove = pointFromIdx + 1 + "-OFF";
																	
																	newThreeMove = newTwoMove + " " + thirdMove;
																	
																	threeMovesAllowed[thirdIdx] = newThreeMove;
																	
																	thirdIdx++;
																	
																	/*4**************************************************************************/
																	
																	//Check possible moves with fourth dice
																	for (int l = 0; l < 24; l++) {
																		
																		if (!pointIndex.get(l).getCounters().isEmpty()) {
																			
																			topCounter = pointIndex.get(l).getCounters().peek();
																		
																			pointFromIdx = l;
																			pointToIdx = l - smallerVal;
																									
																			if (topCounter.getType() == p.getCounterType()) {
																				
																				moveFrom = pointIndex.get(pointFromIdx);
																				
																				if (canBearOff(p) && (pointToIdx < 0 || pointToIdx > NUM_POINTS - 1)) {
																					
																					fourthPossible = true;
																																												
																					fourthMove = pointFromIdx + 1 + "-OFF";
																					
																					newMove = newThreeMove + " " + fourthMove;
																					
																					fourMovesAllowed[fourthIdx] = newMove;
																					
																					fourthIdx++;
																													
																				}
																				
																				else if (!(pointToIdx < 0) && !(pointToIdx > NUM_POINTS - 1)){
																					
																					moveTo = pointIndex.get(pointToIdx);
																					
																					if (moveTo.getCounters().isEmpty() || moveTo.getCounters().peek().getType() == currentPlayer.getCounterType() || moveTo.getCounters().peek().getType() != currentPlayer.getCounterType() && moveTo.getCounters().size() == 1) {
																					
																						fourthPossible = true;
																																													
																						fourthMove = pointFromIdx + 1 + "-" + (pointToIdx + 1);
																						
																						newMove = newThreeMove + " " + fourthMove;
																						
																						fourMovesAllowed[thirdIdx] = newMove;
																						
																						fourthIdx++;
																													
																					}
																					
																				}
																				
																			}
																		
																		}
																		
																	}
																	
																	/*4**************************************************************************/
																									
																}
																
																else if (!(pointToIdx < 0) && !(pointToIdx > NUM_POINTS - 1)){
																	
																	moveTo = pointIndex.get(pointToIdx);
																	
																	if (moveTo.getCounters().isEmpty() || moveTo.getCounters().peek().getType() == currentPlayer.getCounterType() || moveTo.getCounters().peek().getType() != currentPlayer.getCounterType() && moveTo.getCounters().size() == 1) {
																	
																		thirdPossible = true;
																		
																		moveTo.getCounters().push(moveFrom.getCounters().pop());
																		
																		thirdMove = pointFromIdx + 1 + "-" + (pointToIdx + 1);
																		
																		newThreeMove = newTwoMove + " " + thirdMove;
																		
																		threeMovesAllowed[thirdIdx] = newThreeMove;
																		
																		thirdIdx++;
																		
																		/*4**************************************************************************/
																		
																		//Check possible moves with fourth dice
																		for (int l = 0; l < 24; l++) {
																			
																			if (!pointIndex.get(l).getCounters().isEmpty()) {
																				
																				topCounter = pointIndex.get(l).getCounters().peek();
																			
																				pointFromIdx = l;
																				pointToIdx = l - smallerVal;
																										
																				if (topCounter.getType() == p.getCounterType()) {
																					
																					moveFrom = pointIndex.get(pointFromIdx);
																					
																					if (canBearOff(p) && (pointToIdx < 0 || pointToIdx > NUM_POINTS - 1)) {
																						
																						fourthPossible = true;
																																													
																						fourthMove = pointFromIdx + 1 + "-OFF";
																						
																						newMove = newThreeMove + " " + fourthMove;
																						
																						fourMovesAllowed[fourthIdx] = newMove;
																						
																						fourthIdx++;
																														
																					}
																					
																					else if (!(pointToIdx < 0) && !(pointToIdx > NUM_POINTS - 1)){
																						
																						moveTo = pointIndex.get(pointToIdx);
																						
																						if (moveTo.getCounters().isEmpty() || moveTo.getCounters().peek().getType() == currentPlayer.getCounterType() || moveTo.getCounters().peek().getType() != currentPlayer.getCounterType() && moveTo.getCounters().size() == 1) {
																						
																							fourthPossible = true;
																																														
																							fourthMove = pointFromIdx + 1 + "-" + (pointToIdx + 1);
																							
																							newMove = newThreeMove + " " + fourthMove;
																							
																							fourMovesAllowed[thirdIdx] = newMove;
																							
																							fourthIdx++;
																														
																						}
																						
																					}
																					
																				}
																			
																			}
																			
																		}
																		
																		/*4**************************************************************************/
																									
																	}
																	
																}
																
															}
														
														}
														
													}
													
													/*3**************************************************************************/
																				
												}
												
											}
											
										}
									
									}
									
								}
								
								/*2**************************************************************************/
								
							}
							
						}
						
					}
				
				}
				
			}
			
			pointIndex = ogPointIndex;
			
		}
		
		//Check which dice value is larger
		else if (toPlay[1] != toPlay[0]) {
			
			if (toPlay[1] > toPlay[0]) {
				
				largerVal = toPlay[1];
				smallerVal = toPlay[0];
				
			}
			
			if (p.getPlayerNumber() == 2) {
				
				largerVal = 0 - largerVal;
				smallerVal = 0 - smallerVal;
				
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
							
							oneMoveAllowed[firstIdx] = firstMoveLarger;
							
							firstIdx++;
							
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
											
											twoMovesAllowed[secondIdx] = newMove;
											
											secondIdx++;
																			
										}
										
										else if (!(pointToIdx < 0) && !(pointToIdx > NUM_POINTS - 1)){
											
											moveTo = pointIndex.get(pointToIdx);
											
											if (moveTo.getCounters().isEmpty() || moveTo.getCounters().peek().getType() == currentPlayer.getCounterType() || moveTo.getCounters().peek().getType() != currentPlayer.getCounterType() && moveTo.getCounters().size() == 1) {
											
												secondPossible = true;
												
												secondMove = pointFromIdx + 1 + "-" + (pointToIdx + 1);
												
												newMove = firstMoveLarger + " " + secondMove;
												
												twoMovesAllowed[secondIdx] = newMove;
												
												secondIdx++;
																			
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
									
								oneMoveAllowed[firstIdx] = firstMoveLarger;
								
								firstIdx++;
								
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
												
												twoMovesAllowed[secondIdx] = newMove;
												
												secondIdx++;
																					
											}
											
											else if (!(pointToIdx < 0) && !(pointToIdx > NUM_POINTS - 1)){
												
												moveTo = pointIndex.get(pointToIdx);
				
												if (moveTo.getCounters().isEmpty() || moveTo.getCounters().peek().getType() == currentPlayer.getCounterType() || moveTo.getCounters().peek().getType() != currentPlayer.getCounterType() && moveTo.getCounters().size() == 1) {
																				
												secondPossible = true;
												
												secondMove = pointFromIdx + 1 + "-" + (pointToIdx + 1);
												
												newMove = firstMoveLarger + " " + secondMove;
												
												twoMovesAllowed[secondIdx] = newMove;
												
												secondIdx++;
																					
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
							
							oneMoveAllowed[firstIdx] = firstMoveSmaller;
							
							firstIdx++;
							
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
											
											twoMovesAllowed[secondIdx] = newMove;
											
											secondIdx++;
											
										}
										
										else if (!(pointToIdx < 0) && !(pointToIdx > NUM_POINTS - 1)){
											
											moveTo = pointIndex.get(pointToIdx);
											
											if (moveTo.getCounters().isEmpty() || moveTo.getCounters().peek().getType() == currentPlayer.getCounterType() || moveTo.getCounters().peek().getType() != currentPlayer.getCounterType() && moveTo.getCounters().size() == 1) {
											
												secondPossible = true;
												
												secondMove = pointFromIdx + 1 + "-" + (pointToIdx + 1);
												
												newMove = firstMoveSmaller + " " + secondMove;
												
												twoMovesAllowed[secondIdx] = newMove;
												
												secondIdx++;
											
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
								
								oneMoveAllowed[firstIdx] = firstMoveSmaller;
								
								firstIdx++;
							
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
												
												twoMovesAllowed[secondIdx] = newMove;
												
												secondIdx++;
												
											}
											
											else if (!(pointToIdx < 0) && !(pointToIdx > NUM_POINTS - 1)){
												
												moveTo = pointIndex.get(pointToIdx);
												
												if (moveTo.getCounters().isEmpty() || moveTo.getCounters().peek().getType() == currentPlayer.getCounterType() || moveTo.getCounters().peek().getType() != currentPlayer.getCounterType() && moveTo.getCounters().size() == 1) {
																					
													secondPossible = true;
													
													secondMove = pointFromIdx + 1 + "-" + (pointToIdx + 1);
													
													newMove = firstMoveSmaller + " " + secondMove;
													
													twoMovesAllowed[secondIdx] = newMove;
													
													secondIdx++;
												
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
		
		}
		
		if (toPlay[0] != toPlay[1]) {
			
			if (secondPossible == false) {
								
				movesAllowed = oneMoveAllowed;
						
			}
			
			else if(firstPossible == false) {
				
				System.out.print("No possible moves. Lose your turn.");
					
				
			}
			
			else {
				
				movesAllowed = twoMovesAllowed;

				
			}
			
		}
		
		else {
			
			if (fourthPossible == true) {
				
				movesAllowed = fourMovesAllowed;
				
			}
			
			else if (fourthPossible == false && thirdPossible == true) {
				
				movesAllowed = threeMovesAllowed;
				
			}
			
			else if (fourthPossible == false && thirdPossible == false && secondPossible == true) {
				
				movesAllowed = twoMovesAllowed;
				
			}
			
			else if (fourthPossible == false && thirdPossible == false && secondPossible == false && firstPossible == true) {
								
				movesAllowed = oneMoveAllowed;
						
			}
			
			else  {
				
				System.out.print("No possible moves. Lose your turn.");
					
				movesAllowed = oneMoveAllowed;
				
			}
			
		}
		
		return movesAllowed;
		
	}
	
	public void makeMove(int moveNum, String[] allowedMoves) {
		
		Point moveFrom = new Point();
		Point moveTo = new Point();
		
		String moveSelected = allowedMoves[moveNum];
		
		String[] movesToPlay = new String[99];
		
		if (!(moveSelected == null)) {
		
			movesToPlay = moveSelected.split(" ");
			
			String[] moveIdx = new String[2];
			
			for (int i = 0; i < movesToPlay.length; i++) {
				
				moveIdx = movesToPlay[i].split("-");
				
				if (moveIdx[1] == "OFF") {
					
					moveFrom = pointIndex.get(Integer.valueOf(moveIdx[0]));
					
					moveFrom.getCounters().pop();
					
				}
				
				else {
					
					moveFrom = pointIndex.get(Integer.valueOf(moveIdx[0]));
					moveTo = pointIndex.get(Integer.valueOf(moveIdx[1]));
					
					moveTo.getCounters().push(moveFrom.getCounters().pop());
					
				}
				
			}
			
		}
		
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
			
			System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + "\nCurrent Player: " + ConsoleColors.WHITE_BOLD_BRIGHT + currentPlayer.getPlayerName() + ConsoleColors.RESET);
			
		}
		
		else {
			
			System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + "\nCurrent Player: " + ConsoleColors.RED_BOLD_BRIGHT + currentPlayer.getPlayerName() + ConsoleColors.RESET);

			
		}
				
		displayCube();
		
		
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
	
	public void displayCube() {
		
		Player owner = scoreboard.getCubeOwner();
		
		String color = ConsoleColors.YELLOW;
		
		if (owner == null) {
			
			System.out.print(color + "---\n" + ConsoleColors.RESET);
			System.out.println(color + "|" + scoreboard.getStake() + "|" + ConsoleColors.PURPLE_BOLD_BRIGHT + " Cube owner: " + color + "No one" + ConsoleColors.RESET);
			System.out.println(color + "---" + ConsoleColors.RESET);
			
		}
		
		else if (owner.getPlayerNumber() == 1) {
			
			color = ConsoleColors.WHITE_BOLD_BRIGHT;
			
			System.out.print(color + "---\n" + ConsoleColors.RESET);
			System.out.println(color + "|" + scoreboard.getStake() + "|" + ConsoleColors.PURPLE_BOLD_BRIGHT + " Cube owner: " + color + owner.getPlayerName() + ConsoleColors.RESET);
			System.out.println(color + "---" + ConsoleColors.RESET);
			
		}
		
		else if (owner.getPlayerNumber() == 2) {
			
			color = ConsoleColors.RED_BOLD_BRIGHT;
			
			System.out.print(color + "---\n" + ConsoleColors.RESET);
			System.out.println(color + "|" + scoreboard.getStake() + "|" + ConsoleColors.PURPLE_BOLD_BRIGHT + " Cube owner: " + color + owner.getPlayerName() + ConsoleColors.RESET);
			System.out.println(color + "---" + ConsoleColors.RESET);
			
		}
		
		
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
