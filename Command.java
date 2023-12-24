package BackgammonTest;

public class Command {

	 private enum CommandType {ROLL, QUIT, PIP, HINT, DOUBLE};
	    private CommandType commandtype;

	    Command (String command) {
	        
	    	String commandFormatted = command.toUpperCase();

	        switch(commandFormatted){
	            case "Q":
	                commandtype = CommandType.QUIT;
	                break;
	            case "R":
	                commandtype = CommandType.ROLL;
	                break;
	            case "P":
	            	commandtype = CommandType.PIP;
	            	break;
	            case "H":
	            	commandtype = CommandType.HINT;
	            	break;
	            case "D":
	            	commandtype = CommandType.DOUBLE;
	            	break;
	            default:
	                commandtype = CommandType.ROLL;
	                break;
	        }
	    }

	       // User Input Check
		public static boolean isValid (String command) {
			
			String commandFormatted = command.toUpperCase();//valid input regardless of input case
			
			return  commandFormatted.equals("Q") || commandFormatted.equals("R") ||
					commandFormatted.equals("P") || commandFormatted.matches("R[1-6]Q[1-6]") || commandFormatted.equals("H") 
					|| commandFormatted.equals("D");
		}		

	    //User Input related methods
	    public boolean quit() {
			
	    	return commandtype == CommandType.QUIT;
	    	
		}

	    public boolean roll() {
	    	
	        return commandtype == CommandType.ROLL;
	        
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
