public class Minesweeper {

private int[][] matrixVisible = new int[10][10]; //Matrix visible to the player. Contains data visible to the player.
private int[][] matrixHidden = new int[10][10]; //Matrix invisible to the player. Contains numbers, mines, and empty cells.

    public static void main(String[] args)
    {
        Minesweeper M = new Minesweeper();
        M.startGame();
    }
}

public void startGame()
    {
        System.out.println("\n________________________ MINESWEEPER ________________________\n");
        newField(); //Method for generating a random minefield

        boolean flag = true;
        while(flag)
        {
            displayVisibleMatrix();
            flag = playerTurn();
            if(checkWin())
            {
                displayHiddenMatrix();
                System.out.println("\n________________________ WINNER! ________________________");
                break;
            }
        }
    }

//public void newField()
//public void displayVisibleMatrix()
//public void playerTurn()
//public void checkWin()
//public void displayHiddenMatrix ()
