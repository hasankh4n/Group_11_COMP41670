package BackgammonTest;

public class Command {

	 private enum CommandType {ROLL, ROLLSPEC, MOVE, QUIT, PIP, HINT, DOUBLE};
	    private CommandType commandtype;
	    private String moveNum = new String();
	    private String[] rollNums = new String[2];

	    Command (String command) {
	        
	    	String commandFormatted = command.toUpperCase();
	        
	        if (commandFormatted.equals("Q")) {
	        
	        	commandtype = CommandType.QUIT;
	        	
	        }
	        
	        else if (commandFormatted.equals("R")) {
		        
	        	commandtype = CommandType.ROLL;
	        	
	        }
	        	
	        else if (commandFormatted.matches("R[1-6][1-6]")) {
	        	
	        	commandtype = CommandType.ROLLSPEC;
                rollNums[0] = commandFormatted.substring(1, 2);
                rollNums[1] = commandFormatted.substring(2, 3);
	        	
	        }
	        
	        else if (commandFormatted.matches("^[1-9][0-9]*$")) {
	        	
	        	commandtype = CommandType.MOVE;
	        	
	        	if (commandFormatted.length() == 1) {
	        		
	                moveNum = commandFormatted.substring(0, 1);
	        		
	        	}
	        	
	        	else if (commandFormatted.length() == 2) {
	        		
	                moveNum = commandFormatted.substring(0, 2);
	        		
	        	}
	        	
	        	
	        }
	        
	        else if (commandFormatted.equals("P")) {
		        
	        	commandtype = CommandType.PIP;
	        	
	        }
	        
	        else if (commandFormatted.equals("H")) {
		        
	        	commandtype = CommandType.HINT;
	        	
	        }
	        
	        else if (commandFormatted.equals("D")) {
		        
	        	commandtype = CommandType.DOUBLE;
	        	
	        }
	        
	    }

	       // User Input Check
		public static boolean isValid (String command) {
			
			String commandFormatted = command.toUpperCase();//valid input regardless of input case
			
			return  commandFormatted.equals("Q") || commandFormatted.equals("R") ||
					commandFormatted.equals("P") || commandFormatted.matches("R[1-6][1-6]") || commandFormatted.equals("H") 
					|| commandFormatted.equals("D") || commandFormatted.matches("^[1-9][0-9]*$");
		}	
		
		public String[] getRollNums() {
			
			return rollNums;
			
		}
		
		public int getMoveNum() {
			
			int number = Integer.valueOf(moveNum);
			
			return number;
			
		}

	    //User Input related methods
	    public boolean quit() {
			
	    	return commandtype == CommandType.QUIT;
	    	
		}

	    public boolean roll() {
	    	
	        return commandtype == CommandType.ROLL;
	        
	    }
	    
	    public boolean rollspec() {
	    	
	    	return commandtype == CommandType.ROLLSPEC;
	    	
	    }
	    
	    public boolean move() {
	    	
	    	return commandtype == CommandType.MOVE;
	    	
	    }
	    
	    public boolean pip() {
	       
	    	return commandtype == CommandType.PIP;
	    	
	    }
	    
	    public boolean hint() {
	    	
	    	return commandtype == CommandType.HINT;
	    	
	    }
	    
	    public boolean offerdouble() {
	    	
	    	return commandtype == CommandType.DOUBLE;
	    	
	    }


	
	
}
