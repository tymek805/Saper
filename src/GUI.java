// Zespol:
// Wojciech Krzos ?
// Filip Kubecki
// Tymoteusz Lango

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

public class GUI implements ActionListener{
    JFrame frame;
    JPanel buttonPanel, functionPanel, container;
    JButton[][] buttons;
    int size_x, size_y, liczba_min;
    boolean start = true;
    int[][] board_of_numbers;
    boolean[][] board_of_mines;

    public GUI(int size_x, int size_y, int liczba_min){
        this.size_x = size_x;
        this.size_y = size_y;
        this.liczba_min = liczba_min;
        this.board_of_mines = new boolean[size_x][size_y];
        this.board_of_numbers = new int[size_x][size_y];
        startgui();
    }

    private void startgui(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());

        container = new JPanel();
        container.setLayout(new BorderLayout());

        functionPanel = new JPanel();
        functionPanel.setVisible(true);
        functionPanel.setLayout(new GridLayout(0, 3));

        //Ilość zaznaczonych min
        JLabel mines = new JLabel(Integer.toString(liczba_min));
        mines.setHorizontalAlignment(JLabel.CENTER);
        functionPanel.add(mines);

        //RESET button
        JButton resetBT = new JButton("RESET");
        functionPanel.add(resetBT);

        //Licznik czasu
        JLabel time = new JLabel("0");
        time.setHorizontalAlignment(JLabel.CENTER);
        functionPanel.add(time);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(size_x, size_y));

        buttons = new JButton[size_x][size_y];
        for (int i = 0; i < size_x; i++) {
            for (int j = 0; j < size_y; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setBackground(Color.WHITE);
                buttons[i][j].addActionListener(this::actionPerformed);
                buttonPanel.add(buttons[i][j]);
            }
        }
        container.add(buttonPanel);
        frame.setSize(500,500);
        frame.add(container, BorderLayout.CENTER);
        frame.add(functionPanel, BorderLayout.PAGE_START);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.revalidate();
        frame.repaint();
    }

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

    private void initializeBoardOfNumbers(){
        for (int i = 0; i < size_x; i++){
            for (int j = 0; j < size_y; j++){
                howManyMines(i, j);
            }
        }
    }

    private void endGame() {
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        for (int i = 0; i < size_x; i++) {
            for (int j = 0; j < size_y; j++) {
                if (event.getSource() == buttons[i][j]){
                    if (start){
                        randomBoardOfMines();
                        while (board_of_mines[i][j]){
                            randomBoardOfMines();}
                        initializeBoardOfNumbers();
                        start = false;
                    }
                    if (board_of_mines[i][j]){
                        buttons[i][j].setBackground(Color.RED);
                        endGame();
                    } else {
                        buttons[i][j].setText(String.valueOf(board_of_numbers[i][j]));
                    }
                }
            }
        }
    }

    public static void main(String[] args){
        GUI gui = new GUI(8, 8, 10);
    }
}