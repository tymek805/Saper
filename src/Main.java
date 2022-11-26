import java.awt.*;        //awt layout manager
import java.awt.event.*;
import javax.swing.*;     //swing containers and components

public class Main extends JFrame {
   private static final long serialVersionUID = 1L;  // to prevent serial warning

   // private variables
   MineFieldSettings board = new MineFieldSettings();
   JButton btnNewGame = new JButton("New Game");

   // Constructor to set up all the UI and game components
   public Main() {
      Container cp = this.getContentPane();           // JFrame's content-pane
      cp.setLayout(new BorderLayout()); // in 10x10 GridLayout

      cp.add(board, BorderLayout.CENTER);

      // Add btnNewGame to the south to re-start the game
      // ......
      cp.add(btnNewGame, BorderLayout.SOUTH);
      btnNewGame.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            board.newGame();
         }
      });

      board.newGame();

      pack();  // Pack the UI components, instead of setSize()
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // handle window-close button
      setTitle("Mineswepper");
      setVisible(true);   // show it
   }

   // The entry main() method
   public static void main(String[] args) {
      // [TODO 1] Check Swing program template on how to run the constructor
      new Main();
   }
}