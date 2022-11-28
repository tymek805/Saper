// Zespół: 
// Wojciech Krzos <3
// Filip Kubecki <3
// Tymoteusz Lango <3

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

public class GUI implements ActionListener{
    private JFrame frame;
    private JPanel buttonPanel, functionPanel, container;
    private JButton[][] buttons;
    private int size_x, size_y, liczba_min;
    private boolean start = true;
    private int[][] board_of_numbers;
    private boolean[][] board_of_mines;
    private boolean isFlagging = false;

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
        functionPanel.setLayout(new GridLayout(0, 4));

        //Ilość zaznaczonych min
        JLabel mines = new JLabel(Integer.toString(liczba_min));
        mines.setHorizontalAlignment(JLabel.CENTER);
        functionPanel.add(mines);

        //RESET button
        JButton resetBT = new JButton("RESET");
        functionPanel.add(resetBT);

        //FLAGA button
        JButton flagaBT = new JButton("🚩");
        flagaBT.addActionListener(this::flagPerformed);
        functionPanel.add(flagaBT);

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
        String[] idxSplit = event.getSource().toString().substring(21).split(",60x54")[0].strip().split(",");
        int x = (Integer.parseInt(idxSplit[0]) - 3) / 60;
        int y = (Integer.parseInt(idxSplit[1]) - 2) / 54;
        System.out.println(x + " -> " + y);

        if (start){
            randomBoardOfMines();
            // TODO Trzeba dodać sprawdzenie czy wartość początkowa jest miną
            initializeBoardOfNumbers();
            start = false;
        }
        if (board_of_mines[x][y]){
            buttons[x][y].setBackground(Color.RED);
            endGame();
        } else {
            if (board_of_numbers[x][y] == 0){
                buttons[x][y].setBackground(Color.GRAY);
            }else {
                buttons[x][y].setText(String.valueOf(board_of_numbers[x][y]));
            }
        }
    }
    public void flagPerformed(ActionEvent event) {
        isFlagging = !isFlagging;
        System.out.println("Flag -> " + isFlagging);
    }
    public static void main(String[] args){
        GUI gui = new GUI(8, 8, 10);
    }
}
