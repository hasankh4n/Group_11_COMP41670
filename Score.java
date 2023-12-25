package BackgammonTest;

public class Score {

	private int p1Score, p2Score;
	private int matchLength;
	private int stake;
	private Player cubeOwner = null;
	
	Score() {
		
		p1Score = 0;
		p2Score = 0;
		matchLength = 0;
		stake = 1;
		
	}
	
	public int[] getScores() {
		
		int[] scores = new int[2];
		
		scores[0] = p1Score;
		scores[1] = p2Score;
		
		return scores;
		
	}
	
	public int getMatchLength() {
		
		return matchLength;
		
	}
	
	public void setMatchLength(int l) {
		
		matchLength = l;
		
	}
	
	public boolean canOfferDouble(Player currentPlayer) {
		
		if (cubeOwner == null) {
			
			cubeOwner = currentPlayer;
			
			return true;
			
		}
		
		else {
			
			if (currentPlayer == cubeOwner) {
				
				return true;
				
			}
			
			else {
							
				return false;
				
			}
			
		}
		
		
		
	}
	
	public void doubleAccepted(Player p1, Player p2) {
			
		System.out.println("The stakes have been doubled!");
					
		stake = stake*2;
				
		
		//Change the doubling cube owner
		
		if (cubeOwner.getPlayerNumber() == 1) {
			
			cubeOwner = p2;
			
		}
		
		else {
			
			cubeOwner = p1;
			
		}
		
	}
	
	public Player getCubeOwner() {
		
		return cubeOwner;
		
	}
	
	public int getStake() {
		
		if (stake == 1) {
			
			return 64;
			
		}
		
		else {
			
			return stake;
			
		}
		
		
	}
	
	public void calcFinalScore(Player winner, Player loser, Board b) {
		
		int points = 1*stake;
		
		
		
		if (winner.getPlayerNumber() == 1) {
			
			if (b.isGammon(winner, loser)) {
				
				p1Score += points*2;
				
			}
			
			else if (b.isBackgammon(winner, loser)) {
				
				p1Score += points*3;
				
			}
			
			else {
				
				p1Score += points;
				
			}
			
		}
		
		else {
			
			if (b.isGammon(winner, loser)) {
				
				p2Score += points*2;
				
			}
			
			else if (b.isBackgammon(winner, loser)) {
				
				p2Score += points*3;
				
			}
			
			else {
				
				p2Score += points;
				
			}
			
			
		}
		
	}
	
}
