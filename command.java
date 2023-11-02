//Name: command
//Author:
//Version: 1.0
//About: This file details how user inputs are taken and checked

public class command {
    
    private enum CommandType {ROLL, QUIT};
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
            default:
                commandtype = CommandType.ROLL;
                break;
        }
    }

       // User Input Check
	public static boolean isValid (String command) 
    {
		String commandFormatted = command.toUpperCase();//valid input regardless of input case
		return  commandFormatted.equals("Q") || commandFormatted.equals("R") ||
				commandFormatted.matches("R[1-6]Q[1-6]");
	}		

    //User Input related methods
    public boolean quit() {
		return commandtype == CommandType.QUIT;
	}

    public boolean roll(){
        return commandtype == CommandType.ROLL;
    }


}
