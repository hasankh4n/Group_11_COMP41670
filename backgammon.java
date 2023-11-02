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
