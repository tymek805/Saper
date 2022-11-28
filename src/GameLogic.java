public class GameLogic {
    /**Game logic */
    private void randomBoardOfMines(){
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
    private void howManyMines(int x, int y){
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
    private void initializeBoardOfNumbers(){
        for (int i = 0; i < size_x; i++){
            for (int j = 0; j < size_y; j++){
                howManyMines(i, j);
            }
        }
    }

    /**Game logic */
    private void endGame() {
    }


    /**Game logic */
    @Override
    public void actionPerformed(ActionEvent event) {
        for (int i = 0; i < size_x; i++) {
            for (int j = 0; j < size_y; j++) {
                if (event.getSource() == buttons[i][j]){
                    if (start){
                        randomBoardOfMines();
                        // Nie działa :C
                        while (board_of_mines[i][j]){
                            randomBoardOfMines();}
                        initializeBoardOfNumbers();
                        start = false;
                    }
                    if (board_of_mines[i][j]){
                        buttons[i][j].setBackground(Color.RED);
                        endGame();
                    } else {
                        if (board_of_numbers[i][j] == 0){
                            buttons[i][j].setBackground(Color.GRAY);
                        }else {
                            buttons[i][j].setText(String.valueOf(board_of_numbers[i][j]));
                        }
                    }
                }
            }
        }
    }
}

