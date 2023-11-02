public class backgammon {
    
    public static void main (String... args){
        Board board = new Board();
        display display = new display();
        display.info();

        command command;
        do{
            // Display the current board state 
            display.getPlayerNames(board);
            boolean commandIn = false;
            do{
                command = display.getUserInput();
                if (command.quit())
                {
                    commandIn = true;
                } else {
                    display.displayCommandNotPossible();
                }

            } while (!commandIn);
        } while (!command.quit()); //&& !board.isGameOver());
        // when game is over display the following 
        //if (board.isGameOver()){
        //    display.displayGameOver();
        //} else
        {
            display.displayQuit();
        }

    }
}
