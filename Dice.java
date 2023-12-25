package BackgammonTest;
import java.util.Random;

/**
 * Group Number: 11
 * @author James Duke, Hasan Khan
 * GitHub IDs: jamesduke488, hasankh4n
 * @version 1.1
 */

public class Dice {

	public static final int NUM_FACES = 6;
	public static final int DOUBLE = 4;
	
	private int faces[] = new int[2];
	private int numMoves;
	private Random rand;
	
	Dice () {
		
		rand = new Random();
		numMoves = 1;
		
		faces[0] = rand.nextInt(1,7);
		faces[1] = rand.nextInt(1,7);
		
		if (faces[0] == faces[1]) {
			
			numMoves = DOUBLE;
			
		}
		
	}
	
	public void roll() {
		
		faces[0] = rand.nextInt(1,7);
		faces[1] = rand.nextInt(1,7);
		
		if (faces[0] == faces[1]) {
			
			numMoves = DOUBLE;
			
		}
		
		else {
			
			numMoves = 1;
			
		}
	
	}
	
	public int[] getFaces() {
		
		return faces;
		
	}
	
	public void setFaces(int face1, int face2) {
		
		faces[0] = face1;
		faces[1] = face2;
		
	}
	
	public void showFaces() {
		
		System.out.println("Dice 1: " + faces[0]);
		
		System.out.println("Dice 2: " + faces[1]);
		
	}
	
	public int getMoves() {
		
		return numMoves;
		
	}
	
}

