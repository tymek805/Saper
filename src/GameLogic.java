import java.util.Random;

public class GameLogic {

    int size_x, size_y, min_number;
    boolean start = true;
    int[][] board_of_numbers;
    boolean[][] board_of_mines;

    /**Generate a random board of mines */
    public void randomBoardOfMines(){
        Random rand = new Random();

        for (int i = 0; i < min_number; i++){
            int n = rand.nextInt(size_x);
            int m = rand.nextInt(size_y);
            if (!board_of_mines[n][m]) {
                board_of_mines[n][m] = true;}
            else {i--;}
        }
    }

    /**Set how many mines */
    public void howManyMines(int x, int y){
        int suma = 0;
        for (int k = -1; k < 2; k++) {
            for (int q = -1; q < 2; q++) {
                boolean test1 = k == 0 && q == 0;
                boolean test2 = x + k < 0 || y + q < 0 || x + k == size_x || y + q == size_y;
                if (!test1 && !test2 && board_of_mines[x + k][y + q]){
                    suma++;
                }
            }
        }
        board_of_numbers[x][y] = suma;
    }

    /**Initialise the board of numbers */
    public void initializeBoardOfNumbers(){
        for (int i = 0; i < size_x; i++){
            for (int j = 0; j < size_y; j++){
                howManyMines(i, j);
            }
        }
    }


    /**See how many mines surround a cell at inputRow and inputColumn */
    private int getSurroundingMines(int inputRow, int inputColumn) {

        int numMines = 0;

        for (int row = inputRow - 1; row <= inputRow + 1; row++) {

            for (int col = inputColumn - 1; col <= inputColumn + 1; col++) {

                if (row >= 0 && row < size_x && col >= 0 && col < size_y) {

                    if (board_of_numbers[row][col].isMine) numMines++;  ////TODO how to know if a cell is a mine?
                }
            }
        }
        return numMines;
    }

    /**Reveals all neigbouring cells without mines */
    private void revealCell(int inputRow, int inputColumn) {

        int numMines = getSurroundingMines(inputRow, inputColumn);

        if (numMines == 0) {

        for (int row = inputRow - 1; row <= inputRow + 1; row++) {

            for (int col = inputColumn - 1; col <= inputColumn + 1; col++) {

                if (row >= 0 && row < size_x && col >= 0 && col < size_y) {

                    if (!board_of_numbers[row][col].isRevealed) revealCell(row, col); //TODO how to know if a cell is revealed?
                }
            }
        }
        }
    }

    /**Game over conditions */
    public void endGame() {

    }
}

