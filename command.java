//Name: command
//Author:
//Version: 1.0
//About: This file details how user inputs are taken and checked

public class command {
    
    private enum CommandType {ROLL, QUIT, HINT,GETPIP}; //added getpip 
    private CommandType commandtype;

    command(String command)
    {
        String commandFormatted = command.toUpperCase();

        switch(commandFormatted){
            case "Q":
                commandtype = CommandType.QUIT;
                break;
            case "R":
                commandtype = CommandType.ROLL;
                break;
            case "H":
                commandtype = CommandType.HINT;
                break;
            case "P":
                commandtype = CommandType.GETPIP; // added command type
                break;
            default:
                commandtype = CommandType.ROLL;
                break;
        }
    }

       // User Input Check
	public static boolean isValid (String command) 
    {
		String commandFormatted = command.toUpperCase();//valid input regardless of input case
		return  commandFormatted.equals("Q") || commandFormatted.equals("R") || commandFormatted.equals("H") ||
        commandFormatted.equals("P") ||commandFormatted.matches("R[1-6]Q[1-6]H[1-6]");
	}		

    //User Input related methods
    public boolean quit() {
		return commandtype == CommandType.QUIT;
	}

    public boolean roll(){
        return commandtype == CommandType.ROLL;
    }

    public boolean hint(){
        return commandtype == CommandType.HINT;
    }

    public boolean getpip(){
        return commandtype == CommandType.GETPIP;
    }


}
