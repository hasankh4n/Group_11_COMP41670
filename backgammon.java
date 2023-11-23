public class backgammon {
    
    public static void main (String... args){
        Board board = new Board();
        display display = new display();
        Dice dice = new Dice();
        display.info();

        command command;
        do{
            // Display the current board state 
            display.getPlayerNames(board);
            board.printBoard();
            //Board.printBoard();
            boolean commandIn = false;
            board.whosFIrst(dice);
            do{
                command = display.getUserInput();
                if (command.quit())
                {
                    commandIn = true;
                } 
                
                else if (command.roll())
                {
                    dice.roll();
                    dice.showFaces();
                } 
                else if (command.hint()){
                    display.hintInfo();
                }
                else if (command.getpip()){ // trying to add get pip val command
                    board.getPipCount(null);
                }
                else {
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
