
import java.util.Random;


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
	
	public void showFaces() {
		
		System.out.println("Dice 1:" + faces[0]);
		
		System.out.println("Dice 2:" + faces[1]);
		
	}
	
	public int getMoves() {
		
		return numMoves;
		
	}
	
}

