import java.util.Random;

public class GameLogic{
    private final int size_x, size_y, numberOfMines;
    private final int[][] board_of_numbers;
    private final boolean[][] board_of_mines;

    public GameLogic(int size_x, int size_y, int numberOfMines){
        this.size_x = size_x;
        this.size_y = size_y;
        this.numberOfMines = numberOfMines;

        board_of_mines = new boolean[size_x][size_y];
        board_of_numbers = new int[size_x][size_y];
    }

    public void randomBoardOfMines(){
        Random rand = new Random();

        for (int i = 0; i < numberOfMines; i++){
            int n = rand.nextInt(size_x);
            int m = rand.nextInt(size_y);
            if (!board_of_mines[n][m]) {
                board_of_mines[n][m] = true;}
            else {i--;}
        }
    }

    public void initializeBoardOfNumbers(){
        for (int i = 0; i < size_x; i++){
            for (int j = 0; j < size_y; j++){
                howManyMines(i, j);
            }
        }
    }

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

    public int getCellValue(int x, int y) {
        return board_of_numbers[x][y];
    }

    public boolean getIsMine(int x, int y) {
        return board_of_mines[x][y];
    }
}

