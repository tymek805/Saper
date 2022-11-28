import java.util.Random;

public class GameLogic {

    static int size_x, size_y, liczba_min;
    static boolean start = true;
    static int[][] board_of_numbers;
    static boolean[][] board_of_mines;

    /**Game logic */
    public static void randomBoardOfMines(){
        Random rand = new Random();

        for (int i = 0; i < liczba_min; i++){
            int n = rand.nextInt(size_x);
            int m = rand.nextInt(size_y);
            if (!board_of_mines[n][m]) {
                board_of_mines[n][m] = true;}
            else {i--;}
        }
    }

    /**Game logic */
    public static void howManyMines(int x, int y){
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

    /**Game logic */
    public static void initializeBoardOfNumbers(){
        for (int i = 0; i < size_x; i++){
            for (int j = 0; j < size_y; j++){
                howManyMines(i, j);
            }
        }
    }

    /**Game logic */
    public static void endGame() {

    }
}

