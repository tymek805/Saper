import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    @Override
    public void actionPerformed(ActionEvent event) {

        GameLogic gameLogic = new GameLogic();

        String[] idxSplit = event.getSource().toString().substring(21).split(",60x54")[0].strip().split(",");
        int x = (Integer.parseInt(idxSplit[1]) - 2) / 54;
        int y = (Integer.parseInt(idxSplit[0]) - 3) / 60;
        System.out.println(x + " -> " + y);

        if (start){
            gameLogic.randomBoardOfMines();
            // TODO Trzeba dodać sprawdzenie czy wartość początkowa jest miną
            gameLogic.initializeBoardOfNumbers();
            start = false;
        }
        if (board_of_mines[x][y]){
            buttons[x][y].setBackground(Color.RED);
            gameLogic.endGame();
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
}
